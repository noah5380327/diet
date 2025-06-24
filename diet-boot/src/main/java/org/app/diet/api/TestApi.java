package org.app.diet.api;

import org.app.diet.annotation.CoreApi;
import org.app.diet.annotation.CoreLog;
import org.app.diet.dto.ApiDto;
import org.app.diet.util.ApiUtil;
import org.springframework.web.bind.annotation.GetMapping;

@CoreApi
public class TestApi {

    @CoreLog
    @GetMapping("/test")
    public ApiDto test() {
        return ApiUtil.success("test");
    }
}
