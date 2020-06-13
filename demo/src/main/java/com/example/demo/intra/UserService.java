package com.example.demo.intra;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

public interface UserService {
	public User addUser(@RequestBody User ur);

	public boolean logId(@RequestBody UserDto ur) throws Exception;

}
