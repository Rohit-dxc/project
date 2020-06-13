package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.OrderDto;
import com.example.demo.intra.Cart;
import com.example.demo.model.ItemList;
import com.example.demo.model.OrderList;
import com.example.demo.repository.ItemListRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Service
public class Cartimpl implements Cart {
	@Autowired
	private ItemListRepository itemListRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void addToCart(OrderDto ordto) {
		OrderList ordt = new OrderList();
		ItemList itlst = new ItemList();

		if (userRepository.checkUser(ordto.getBuyer())) {
			ordt.setOrderid(ordto.getOrderId());
			System.out.println("ordto.getBuyer()" + ordto.getBuyer());
			ordt.setBuyer(ordto.getBuyer());
			System.out.println("inside if of checkuser");
			// ordt.setIid(itemListRepository.itemId(ordto.getBook()));
			itlst = itemListRepository.itemDetail(ordto.getBook());

			if (itlst.getQuantity() > ordto.getQuantity()) {

				ordt.setIid(itlst.getIid());
				itemListRepository.updateQuantity(ordto.getBook(), itlst.getQuantity() - ordto.getQuantity());

				// ordt.setOrderid(ordto.getQuantity());
				ordt.setQuantity(ordto.getQuantity());
				ordt.setAddtocart(1);
				ordt.setOrderstatus(0);
				ordt.setPrice(ordto.getQuantity() * itlst.getCost());
				orderRepository.save(ordt);
			}
		}

	}

}
