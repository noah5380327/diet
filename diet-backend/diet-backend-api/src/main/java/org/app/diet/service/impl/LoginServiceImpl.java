package org.app.diet.service.impl;

import org.app.diet.dto.LoginBackDto;
import org.app.diet.dto.LoginDto;
import org.app.diet.dto.LoginPersistDto;
import org.app.diet.dto.LoginSuccessDto;
import org.app.diet.entity.UserEntity;
import org.app.diet.repository.UserRepository;
import org.app.diet.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements UserDetailsService, LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    @Override
    public LoginSuccessDto loginSuccess(UserDetails userDetails) {
        UserEntity userEntity = (UserEntity)userDetails;
        String role = userEntity.getRole();
        String userId = userEntity.getId();

        LoginSuccessDto loginSuccessDto = new LoginSuccessDto();

        LoginDto loginDto = new LoginDto();
        loginDto.setUser(userEntity);
        loginDto.setRole(role);

        LoginPersistDto loginPersistDto = new LoginPersistDto();
        loginPersistDto.setUserId(userId);
        loginPersistDto.setRoles(List.of(role));

        LoginBackDto loginBackDto = new LoginBackDto();
        loginBackDto.setObject(loginDto);

        loginSuccessDto.setPersist(loginPersistDto);
        loginSuccessDto.setSubject(userId);
        loginSuccessDto.setBack(loginBackDto);

        return loginSuccessDto;
    }
}