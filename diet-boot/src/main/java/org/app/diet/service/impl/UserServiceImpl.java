package org.app.diet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import jakarta.persistence.criteria.Predicate;
import org.app.diet.entity.UserEntity;
import org.app.diet.exception.CoreException;
import org.app.diet.repository.UserRepository;
import org.app.diet.service.UserService;
import org.app.diet.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserRegisterVo vo) {
        Boolean exist = userRepository.existsByUsername(vo.getUsername());
        if (exist) {
            throw new CoreException("user already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtil.copyProperties(vo, userEntity);

        String enCodePassword = passwordEncoder.encode(vo.getPassword());
        userEntity.setPassword(enCodePassword);
        userEntity.setLocked(false);
        userEntity.setRole(vo.getIsCoach() ? "COACH" : "STUDENT");
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity getCurrentUser() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userId).get();
    }

    @Override
    public List<UserEntity> getAllCoaches() {
        Specification<UserEntity> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("role"), "COACH"));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        };
        Sort sort = Sort.by(Sort.Direction.DESC,"createdTime");
        return userRepository.findAll(specification, sort);
    }
}
