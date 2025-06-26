package org.app.diet.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.app.diet.dto.LoginPersistDto;
import org.app.diet.dto.LoginPersistRuleDto;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class PredicateUtil {

    public static void generateByRules(String code, List<Predicate> list, Root root, CriteriaBuilder criteriaBuilder) {
        LoginPersistDto loginPersistDto = (LoginPersistDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
        List<LoginPersistRuleDto> ruleDtos = loginPersistDto.getRules();
        if (ObjectUtil.isNotNull(ruleDtos)) {
            for (LoginPersistRuleDto ruleDto : ruleDtos) {
                if (code.equals(ruleDto.getCode())) {
                    Predicate predicate = generatePredicate(ruleDto, root, criteriaBuilder, loginPersistDto);
                    if (ObjectUtil.isNotNull(predicate)) {
                        list.add(predicate);
                    }
                }
            }
        }
    }

    private static Predicate generatePredicate(LoginPersistRuleDto ruleDto, Root root, CriteriaBuilder criteriaBuilder, LoginPersistDto persistDto) {
        String field = ruleDto.getField();
        String expression = ruleDto.getExpression();
        String value = generateValue(ruleDto.getValue(), persistDto);
        Predicate predicate = null;

        switch (expression) {
            case "=":
                predicate = criteriaBuilder.equal(root.get(field), value);
                break;
            case "like":
                predicate = criteriaBuilder.like(root.get(field), "%" + value + "%");
                break;
            case "<":
                predicate = criteriaBuilder.lt(root.get(field), NumberUtil.parseNumber(value));
                break;
            case "<=":
                predicate = criteriaBuilder.le(root.get(field), NumberUtil.parseNumber(value));
                break;
            case ">":
                predicate = criteriaBuilder.gt(root.get(field), NumberUtil.parseNumber(value));
                break;
            case ">=":
                predicate = criteriaBuilder.ge(root.get(field), NumberUtil.parseNumber(value));
                break;
            case "!=":
                predicate = criteriaBuilder.notEqual(root.get(field), value);
                break;
            default:
                break;
        }

        return predicate;
    }

    private static String generateValue(String value, LoginPersistDto persistDto) {
        switch (value) {
            case "{currentUserId}":
                value = persistDto.getUserId();
                break;
            default:
                break;
        }

        return value;
    }
}
