package com.coder.ww.controller;

import com.coder.ww.entity.User;
import com.coder.ww.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "用户管理", description = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/signup")
    public int signup(@RequestBody User user) {
        return this.userService.signup(user);
    }

    @ApiOperation(value = "查询用户权限")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/authorityList")
    public List<String> authorityList() {
        return this.userService.authorityList();
    }
}