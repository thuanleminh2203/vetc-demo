package com.mbbank.vetc.service;

import com.mbbank.vetc.config.UserPrincipal;
import com.mbbank.vetc.entity.UserEntity;
import com.mbbank.vetc.feign.PaymentHubClient;
import com.mbbank.vetc.feign.request.VerifyFeignRequest;
import com.mbbank.vetc.payload.request.OauthRequest;
import com.mbbank.vetc.payload.response.OauthResponse;
import com.mbbank.vetc.repository.UserRepository;
import com.mbbank.vetc.util.JwtTokenComponent;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class OauthServiceImpl implements OauthService {

    private final JwtTokenComponent jwtTokenComponent;
    private final PaymentHubClient paymentHubClient;
    private final UserRepository userRepository;

    @Override
    @SneakyThrows
    public OauthResponse login(OauthRequest request) {

        OauthResponse response = new OauthResponse();
        var userFeignResponse = paymentHubClient.verify(new VerifyFeignRequest(request.getToken()));
        UserPrincipal userPrincipal = UserPrincipal.create(userFeignResponse);

        CompletableFuture.runAsync(() -> {
            var currentUser = userRepository.findByCif(userPrincipal.getCif());
            if(currentUser.isEmpty()){
                var user = new UserEntity();
                user.setCif(userPrincipal.getCif());
                user.setDob(userPrincipal.getDob());
                user.setEmail(userPrincipal.getEmail());
                user.setFullname(userPrincipal.getFullname());
                user.setMobile(userPrincipal.getMobile());
                userRepository.save(user);
            }
        });

        response.setToken(jwtTokenComponent.generateToken(userPrincipal));
        return response;
    }




}
