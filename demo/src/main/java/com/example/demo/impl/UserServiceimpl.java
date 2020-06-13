package com.example.demo.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.intra.UserService;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addUser(User ur) {

		User usr = new User();
		String otp = getOtp();
		String mobno = usr.getMobno();

		return userRepository.save(ur);

	}

	public String getOtp() {
		String numbers = "0123456789";

		// Using random method
		Random rndm_method = new Random();

		String otp = "";

		for (int i = 0; i < 4; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otp = otp + numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		return otp;
	}

	@Override
	public boolean logId(UserDto ur) throws Exception {
		User us;

		boolean b = false;
		int flag;
		System.out.println("email in serservice  =" + ur.getEmail());
		// String pass = ur.getPass();
		// us = userRepository.logId(ur);
		us = userRepository.logId(ur);
		System.out.println("user entity pass =" + us.getPass() + "         " + "userdto pass" + ur.getPass());
		if (us.getPass() == ur.getPass())
		{
			if (us != null) {
				b = true;
			} else {
				b = false;
			}
		
			System.out.println("user entity pass =" + us.getPass() + "      " + "userdto pass" + ur.getPass());
	}
//		if(flag>0)
//		{
//			b=true;
//		}
//		else
//		{
//			b=false;
//		}
//		if (pass == us.getPass()) {
//			b = true;
//		} else {
//			b = false;
//		}
//
//		// System.out.println(us);
//		return b;
//
//	}

		return b;
	}

}
