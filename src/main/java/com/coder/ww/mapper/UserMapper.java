package com.coder.ww.mapper;

import com.coder.ww.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserMapper {

    User findByUsername(@Param("username") String username);

    int save(User user);
}
