package com.exaple.orders.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name="order_products")
public class OrderProductsEntity {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) // ID自動採番
private int orderProductsId;
private String orderProductsName;
private int price;
private Timestamp updateDate;
private Timestamp registrationDate;

//テーブル結合する
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // ←1対1の関係でテーブルの結合をする場合
// ↓name属性に結合先Entityの何で紐づけるかを記述するよー
@JoinColumn(name="orderProductsId", insertable=false, updatable=false)
private OrdersEntity ordersEntity; // 1対1の結合=結合先のテーブルは1つしか存在しない事になるので単数型


//テーブル結合する
@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)// ←1対1の関係でテーブルの結合をする場合
//↓name属性に結合先Entityの何で紐づけるかを記述するよー
@JoinColumn(name="orderProductsId", insertable=false, updatable=false)
private ReceivingOrdersEntity receivingOrdersEntity; // 1対1の結合=結合先のテーブルは1つしか存在しない事になるので単数型



public ReceivingOrdersEntity getReceivingOrdersEntity() {
	return receivingOrdersEntity;
}
public void setReceivingOrdersEntity(ReceivingOrdersEntity receivingOrdersEntity) {
	this.receivingOrdersEntity = receivingOrdersEntity;
}
public int getOrderProductsId() {
	return orderProductsId;
}
public void setOrderProductsId(int orderProductsId) {
	this.orderProductsId = orderProductsId;
}
public String getOrderProductsName() {
	return orderProductsName;
}
public void setOrderProductsName(String orderProductsName) {
	this.orderProductsName = orderProductsName;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public Timestamp getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Timestamp updateDate) {
	this.updateDate = updateDate;
}
public Timestamp getRegistrationDate() {
	return registrationDate;
}
public void setRegistrationDate(Timestamp registrationDate) {
	this.registrationDate = registrationDate;
}
public OrdersEntity getOrdersEntity() {
	return ordersEntity;
}
public void setOrdersEntity(OrdersEntity ordersEntity) {
	this.ordersEntity = ordersEntity;
}




}
