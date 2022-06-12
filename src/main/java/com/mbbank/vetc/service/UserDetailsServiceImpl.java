package com.mbbank.vetc.service;

import com.mbbank.vetc.config.UserPrincipal;
import com.mbbank.vetc.entity.UserEntity;
import com.mbbank.vetc.exception.BadRequestException;
import com.mbbank.vetc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String cif) {

        UserEntity user = getUserByCif(cif);

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setCif(user.getCif());
        userPrincipal.setEmail(user.getEmail());
        return userPrincipal;
    }

    @SneakyThrows
    private UserEntity getUserByCif(String cif)  {
        return userRepository.findByCif(cif).orElseThrow(() -> new BadRequestException("{account.valid}"));
    }

}
