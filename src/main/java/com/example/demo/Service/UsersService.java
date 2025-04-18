package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dtos.PasswordDto;
import com.example.demo.Dtos.UsersDto;

import com.example.demo.Entity.Users;

@Service
public interface UsersService {

	public Users saveUser(Users users);

}
