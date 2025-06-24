package org.app.diet.service;

import org.app.diet.entity.UserEntity;
import org.app.diet.vo.UserRegisterVo;

import java.util.List;

public interface UserService {

    void register(UserRegisterVo vo);

    UserEntity getCurrentUser();

    List<UserEntity> getAllCoaches();
}
