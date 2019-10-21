package com.coder.ww.service;

import com.coder.ww.entity.User;

import java.util.List;

public interface UserService {

    int signup(User user);

    List<String> authorityList();
}
