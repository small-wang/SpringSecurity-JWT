package com.coder.ww.service.impl;

import com.coder.ww.entity.User;
import com.coder.ww.exception.BusinessException;
import com.coder.ww.mapper.UserMapper;
import com.coder.ww.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int signup(User user) {
        User bizUser = this.userMapper.findByUsername(user.getUsername());
        if (null != bizUser) {
            throw new BusinessException("用户已经存在");
        }
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userMapper.save(user);
    }

    @Override
    public List<String> authorityList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            list.add(grantedAuthority.getAuthority());
        }
        return list;
    }
}
