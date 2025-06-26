package org.app.diet.annotation;

import org.app.diet.constant.CoreConstant;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
@RestController
@RequestMapping(value = CoreConstant.API_PREFIX, produces = {"application/json"})
public @interface CoreApi {
}
