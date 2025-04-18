package com.example.demo.ServieImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dtos.PasswordDto;
import com.example.demo.Dtos.UsersDto;

import com.example.demo.Entity.Users;

import com.example.demo.Repository.UsersRepo;
import com.example.demo.Service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepo usersRepo;

	@Override
	public Users saveUser(Users users) {

		return usersRepo.save(users);
	}

}
