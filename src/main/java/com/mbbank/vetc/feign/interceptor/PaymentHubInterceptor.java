package com.mbbank.vetc.feign.interceptor;

import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class PaymentHubInterceptor implements ErrorDecoder {
    @Value("${vetc.merchantCode}")
    private String MERCHANT_CODE;

    @Value("${vetc.merchantSecret}")
    private String MERCHANT_SECRET;

    @Value("${proxy.host}")
    private String proxyHost;

    @Value("${proxy.port}")
    private int proxyPort;

    @Value("${proxy.user}")
    private String proxyUser;

    @Value("${proxy.password}")
    private String proxyPassword;

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("MERCHANT_CODE", MERCHANT_CODE);
            requestTemplate.header("MERCHANT_SECRET", MERCHANT_SECRET);
        };
    }

    @Bean
    public feign.Client feignClient() {
        OkHttpClient okHttpClient;
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        okHttpClient = new OkHttpClient.Builder().proxy(proxy).proxyAuthenticator(authenticator()).build();
        return new feign.okhttp.OkHttpClient(okHttpClient);
    }

    private okhttp3.Authenticator authenticator() {
        return (route, response) -> {
            String credential = okhttp3.Credentials.basic(proxyUser, proxyPassword);
            return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();
        };
    }

    @Override
    public Exception decode(String s, Response response) {
        Reader reader = null;
        String result = null;
        try {
            switch (HttpStatus.valueOf(response.status())) {
                case BAD_REQUEST:
                    if (Objects.nonNull(response.body())) {
                        reader = response.body().asReader(StandardCharsets.UTF_8);
                        result = IOUtils.toString(reader);
                    }
                    if (result == null)
                        return new Exception(response.reason());
                    else {
                        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "{\"error\":\"Bad Request\",\"status\": 400}");
                    }

                case UNAUTHORIZED:
                case FORBIDDEN:
                    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "{\"error\":\"Unauthorized\",\"status\": 401}");
                default:
                    return new Exception(response.reason());

            }

        } catch (IOException ex) {
            log.error(ex.getMessage());
        } finally {
            try {
                if (reader != null)
                    reader.close();

            } catch (IOException e) {
                log.error("[FeignErrorDecoder] close reader error {}", e.getMessage(), e);
            }
        }
        return new RuntimeException(response.reason());
    }
}
