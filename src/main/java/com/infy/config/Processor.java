package com.infy.config;

import org.springframework.batch.item.ItemProcessor;

import com.infy.model.User;

public class Processor implements ItemProcessor<User, User>{

	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

	
	
}
