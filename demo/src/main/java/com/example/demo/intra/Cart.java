package com.example.demo.intra;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.OrderDto;

public interface Cart {
	public void addToCart(@RequestBody OrderDto ordto);

}
