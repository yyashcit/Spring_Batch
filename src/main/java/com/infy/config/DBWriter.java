package com.infy.config;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.infy.model.User;
import com.infy.repository.UserRepository;

public class DBWriter implements ItemWriter<User> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void write(List<? extends User> items) throws Exception {
		
		//userRepository.save(items);
	}
	
	
	
	
}
