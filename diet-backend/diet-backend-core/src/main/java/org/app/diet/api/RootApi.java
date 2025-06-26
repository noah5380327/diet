package org.app.diet.api;

import cn.hutool.core.date.DateUtil;
import org.app.diet.annotation.CoreLog;
import org.app.diet.dto.ApiDto;
import org.app.diet.util.ApiUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootApi {

    @CoreLog
    @GetMapping(value = "/", produces = { "application/json" })
    public ApiDto root() {
        return ApiUtil.success(DateUtil.now());
    }
}
