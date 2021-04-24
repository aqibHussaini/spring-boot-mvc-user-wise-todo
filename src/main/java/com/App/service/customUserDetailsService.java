package com.App.service;

import com.App.Entity.User;
import com.App.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailsService implements UserDetailsService {
    @Autowired
    userRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User user= this.repository.findByEmail(s);
       customUserDetails customUserDetails=new customUserDetails(user);
       return customUserDetails;
    }
}
