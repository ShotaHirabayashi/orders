package com.exaple.orders.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrdersEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // ID自動採番
private int orderProductsId;
private int companyId;
private String orderItemName;
private int orderCount;
private int orderPrice;


@OneToOne // ←1対1の関係でテーブルの結合をする場合
// ↓name属性に結合先Entityの何で紐づけるかを記述するよー
@JoinColumn(name="companyId", insertable=false, updatable=false)
private CompaniesEntity companiesEntity; // 1対1の結合=結合先のテーブルは1つしか存在しない事になるので単数型




public CompaniesEntity getCompaniesEntity() {
	return companiesEntity;
}
public void setCompaniesEntity(CompaniesEntity companiesEntity) {
	this.companiesEntity = companiesEntity;
}
public int getOrderProductsId() {
	return orderProductsId;
}
public void setOrderProductsId(int orderProductsId) {
	this.orderProductsId = orderProductsId;
}
public int getCompanyId() {
	return companyId;
}
public void setCompanyId(int companyId) {
	this.companyId = companyId;
}
public String getOrderItemName() {
	return orderItemName;
}
public void setOrderItemName(String orderItemName) {
	this.orderItemName = orderItemName;
}
public int getOrderCount() {
	return orderCount;
}
public void setOrderCount(int orderCount) {
	this.orderCount = orderCount;
}
public int getOrderPrice() {
	return orderPrice;
}
public void setOrderPrice(int orderPrice) {
	this.orderPrice = orderPrice;
}

	
	




}
