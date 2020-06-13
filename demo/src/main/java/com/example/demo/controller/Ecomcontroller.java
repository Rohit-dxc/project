package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.UserDto;
import com.example.demo.impl.StripeService;
import com.example.demo.intra.Cart;
import com.example.demo.intra.UserService;
import com.example.demo.model.ChargeRequest;
import com.example.demo.model.ChargeRequest.Currency;
import com.example.demo.model.User;
import com.stripe.model.Charge;

@RestController
@RequestMapping("/ecom")
public class Ecomcontroller {
	@Autowired
	private UserService userService;
	@Autowired
	private Cart cart;

	@PostMapping("/register")
	public User addUser(@RequestBody User ur) {
		return userService.addUser(ur);
	}

	// @GetMapping("/login/{uid}/{pass}")
	@PostMapping("/login")

	public String login(@RequestBody UserDto ur) throws Exception {
		String str = "";
		// System.out.println("LONG id" + uid);
		// boolean userFlag=false;
		boolean us = false;
		// st = repository.findById(id).get();
		// us = userService.logId(ur);
		us = userService.logId(ur);
		if (us == true) {
			str = "Welcome";
		} else {
			str = "invalid credentials";
		}
		// System.out.println("login details= " + us);
		return str;
	}

	@PostMapping("/addtocart")
	public void addToCart(@RequestBody OrderDto ordto) throws Exception {
		cart.addToCart(ordto);

	}

	@Value("${STRIPE_PUBLIC_KEY}")
	private String stripePublicKey;

	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("amount", 50 * 100); // in cents
		model.addAttribute("stripePublicKey", stripePublicKey);
		model.addAttribute("currency", ChargeRequest.Currency.RS);
		return "checkout";
	}

	@Autowired
	private StripeService paymentsService;

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model) throws Exception {
		chargeRequest.setDescription("Example charge");
		chargeRequest.setCurrency(Currency.RS);
		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());
		return "result";
	}

	@ExceptionHandler(Exception.class)
	public String handleError(Model model, Exception ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}

}
