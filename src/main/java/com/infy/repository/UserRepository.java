package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
