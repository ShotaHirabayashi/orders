package com.exaple.orders.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


public class OrdersForm {

@NotBlank(message = "文字を入力してください")
private String productsName;


@NumberFormat(pattern = "#")
//@Digits(integer = 9, fraction = 0,message = "{number}")
private Integer productsPrice;

@NumberFormat(pattern = "#")
//@Digits(integer = 9, fraction = 0,message = "{number}")
private Integer count;

@NumberFormat(pattern = "#")
@NotNull(message = "数字を入力してください")
//@Pattern(regexp = "\"[0-9,-]+\"" ,message="数字を入力してください")
//@Digits(integer = 9,fraction = 0, message ="{number}")
private Integer price;

//@Pattern(regexp = "\"[0-9,-]+\"" ,message="数字を入力してください")
@NotBlank(message = "{need}")
@Digits(integer = 9,fraction = 0,message ="{number}")
private String customerTel;



@Digits(integer = 9,fraction = 0)
private int id;
	
public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

@NotNull(message = "{need}")
private Integer companyId;

@NotNull(message = "{need}")
private String itemName;

@NotNull(message = "{need}")
private String customerName;

@NotNull(message = "{need}")
private String customerAddress;

public String getProductsName() {
	return productsName;
}

@Required
public void setProductsName(String productsName) {
	this.productsName = productsName;
}

public Integer getProductsPrice() {
	return productsPrice;
}

@Required
public void setProductsPrice(Integer productsPrice) {
	this.productsPrice = productsPrice;
}

public Integer getCount() {
	return count;
}

@Required
public void setCount(Integer count) {
	this.count = count;
}

public Integer getPrice() {
	return price;
}

@Required
public void setPrice(Integer price) {
	this.price = price;
}

public String getCustomerTel() {
	return customerTel;
}

public void setCustomerTel(String customerTel) {
	this.customerTel = customerTel;
}

public Integer getCompanyId() {
	return companyId;
}

public void setCompanyId(Integer companyId) {
	this.companyId = companyId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
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

	
}
