package org.app.diet.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorePageVo {

    private Boolean page = false;

    private Integer current = 1;

    private Integer size = 10;
}
