package com.exaple.orders.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receiving_orders")
public class ReceivingOrdersEntity {

	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // ID自動採番
private int orderProductsId;
private String customerName;
private String customerAddress;
private String customerTel;

public int getOrderProductsId() {
	return orderProductsId;
}
public void setOrderProductsId(int orderProductsId) {
	this.orderProductsId = orderProductsId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getCustomerAddress() {
	return customerAddress;
}
public void setCustomerAddress(String customerAddress) {
	this.customerAddress = customerAddress;
}
public String getCustomerTel() {
	return customerTel;
}
public void setCustomerTel(String customerTel) {
	this.customerTel = customerTel;
}
	



	
}
