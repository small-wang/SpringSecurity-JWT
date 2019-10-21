package com.coder.ww.service.impl;

import com.coder.ww.entity.Role;
import com.coder.ww.entity.User;
import com.coder.ww.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (null != user.getRoles() || 0 != user.getRoles().size()) {
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
