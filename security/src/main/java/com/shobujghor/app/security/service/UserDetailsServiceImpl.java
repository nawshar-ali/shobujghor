package com.shobujghor.app.security.service;

import com.shobujghor.app.security.dynamo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfoOpt = userRepository.getData(username);

        if (!userInfoOpt.isPresent()) {
            log.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }

        log.info("User Authenticated Successfully..!!!");

        return new CustomUserDetails(userInfoOpt.get());
    }
}