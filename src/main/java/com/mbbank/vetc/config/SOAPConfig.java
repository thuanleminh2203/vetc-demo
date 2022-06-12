package com.mbbank.vetc.config;

import com.mbbank.vetc.component.SOAPConnector;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

@Configuration
@AllArgsConstructor
public class SOAPConfig {


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        // this package must match the package in the <generatePackage> specified in
//        // pom.xml
//        marshaller.setContextPath("com.mbbank.vetc.generate.imp");
        marshaller.setPackagesToScan("com.mbbank.vetc.genarate.imp");
        return marshaller;
    }

    @Bean
    public SOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
        SOAPConnector client = new SOAPConnector();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        ClientInterceptor[] clientInterceptors = {new SoapClientInterceptor()};
        client.setInterceptors(clientInterceptors);
        return client;
    }


}
