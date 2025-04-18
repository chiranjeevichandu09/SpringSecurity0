package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	
      Users findByUserName(String name);
      
      @Transactional
      @Modifying
      @Query("UPDATE Users u set u.password=:newpassword Where u.userName=:userName")
      void updatePassword(String userName,String newpassword);
}
