package org.app.diet.api;

import org.app.diet.annotation.CoreApi;
import org.app.diet.dto.ApiDto;
import org.app.diet.entity.UserEntity;
import org.app.diet.service.UserService;
import org.app.diet.util.ApiUtil;
import org.app.diet.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CoreApi
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ApiDto userRegister(@Validated @RequestBody UserRegisterVo vo) {
        userService.register(vo);
        return ApiUtil.success();
    }

    @GetMapping("/users/current")
    public ApiDto getCurrentUser() {
        UserEntity userEntity = userService.getCurrentUser();
        return ApiUtil.success(userEntity);
    }

    @GetMapping("/users/coaches")
    public ApiDto getAllCoaches() {
        List<UserEntity> coaches = userService.getAllCoaches();
        return ApiUtil.success(coaches);
    }
}
