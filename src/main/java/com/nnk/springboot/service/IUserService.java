package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService {
    @Transactional(readOnly = true)
    List<User> findAll();

    @Transactional(isolation = Isolation.SERIALIZABLE)
    User update(Integer id, User user);

    @Transactional(readOnly = true)
    User findById(Integer id);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    User save(User user);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    User delete(Integer id);
}
