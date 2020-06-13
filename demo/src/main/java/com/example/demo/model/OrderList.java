package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "orderlist")
public class OrderList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderid;
	private int addtocart;
	private String buyer;
	private int iid;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private int orderstatus;
	private int quantity;
	private double price;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
		System.out.println("orderid =" + orderid);
	}

	public int getAddtocart() {
		return addtocart;
	}

	public void setAddtocart(int addtocart) {
		this.addtocart = addtocart;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	/*
	 * orderid int PRIMARY KEY AUTO_INCREMENT, addtocart int, buyer, iid int,
	 * orderstatus i
	 */
}
