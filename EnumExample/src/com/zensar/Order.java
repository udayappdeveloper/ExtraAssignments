package com.zensar;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Status status;
	private int orderId;
	private String orderName;
	private int quantity;
	private double price;

	public Order(Status status, int orderId, String orderName, int quantity, double price) {
		super();
		this.status = status;
		this.orderId = orderId;
		this.orderName = orderName;
		this.quantity = quantity;
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

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

	public static void main(String[] args) {

		List<Order> ordersList = new ArrayList<>();

		Order o1 = new Order(Status.NEW, 0, "TOMOTO", 10, 500.00);
		ordersList.add(o1);

		Order o2 = new Order(Status.ACCEPTED, 1, "POTATO", 5, 150.00);
		ordersList.add(o2);

		System.out.println(ordersList);

	}

	@Override
	public String toString() {
		return "Order [status=" + status.getValue() + ", orderId=" + orderId + ", orderName=" + orderName
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}

}
