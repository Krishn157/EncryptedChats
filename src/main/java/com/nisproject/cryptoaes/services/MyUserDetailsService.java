package com.nisproject.cryptoaes.services;

import java.util.ArrayList;

import com.nisproject.cryptoaes.models.UserEntity;
import com.nisproject.cryptoaes.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userReo;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity userEntity = userReo.findByUserId(userId);
        if (userEntity == null)
            throw new UsernameNotFoundException(userId);
        return new User(userEntity.getUserId(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
