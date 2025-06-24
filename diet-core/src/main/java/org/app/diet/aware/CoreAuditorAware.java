package org.app.diet.aware;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class CoreAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(ObjectUtil.isNotNull(authentication)) {
            return Optional.ofNullable(authentication.getName());
        }
        return Optional.empty();
    }
}
