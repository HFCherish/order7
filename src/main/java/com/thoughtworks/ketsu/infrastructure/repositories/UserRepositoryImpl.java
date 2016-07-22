package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

//    @Inject
//    UserMapper userMapper;

    @Override
    public User save(Map info) {
        return null;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(new User());
    }
}
