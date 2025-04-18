package com.example.demo.ServieImpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UsersRepo;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	
	@Autowired
	public UsersRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u=userRepo.findByUserName(username);
		return new User(u.getUserName(),u.getPassword(),Collections.EMPTY_LIST);
	}

}
