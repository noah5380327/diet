package org.app.diet.service;

import org.app.diet.dto.LoginSuccessDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {

    LoginSuccessDto loginSuccess(UserDetails userDetails);
}
