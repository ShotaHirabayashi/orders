package com.exaple.orders.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.exaple.orders.Entity.CompaniesEntity;
import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.repository.OrderProductsRepository;


@Service
public class OrderProductsService {

	// newをする
	@Autowired
	OrderProductsRepository ordersrepository;

//	// リポジトリのselectAll()メソッドを呼び出すメソッド
//	public List<OrderProductsEntity> getAll() {
//		// リポジトリのselectAll()メソッドを呼び出す
//		List<OrderProductsEntity> orderProductsEntity = ordersrepository.selectAll();
//		return orderProductsEntity;
//	}

public Page<OrderProductsEntity> getPage(int pageNum,int id,String productsName,int productsPriceFrom,int productsPriceTo,String customerName,String companyName,int sortDate) {
	// リポジトリからページ情報を取得
	Page<OrderProductsEntity> entityList = ordersrepository.getPage(pageNum,id,productsName,productsPriceFrom,productsPriceTo,customerName,companyName,sortDate);
 
	return entityList;
}
	
	
	
	public OrderProductsEntity update(int id) {
		OrderProductsEntity orderProductsEntity = ordersrepository.update(id);
		return orderProductsEntity;
	}

	public void doUpdate(int id, String productsName, int productsPrice, int companyId, String itemName, int count,
			int price, String customerName, String customerAddres, String customerTel,Timestamp timestamp) {
		System.out.println(id+productsName+productsPrice+companyId+itemName+count+price+customerName+customerAddres+customerTel);
		ordersrepository.doUpdate(id, productsName, productsPrice, companyId, itemName, count, price, customerName,
				customerAddres, customerTel,timestamp);
	}
	
	
	
	public void doRegist(String productsName, int productsPrice, int companyId, String itemName, int count,
			int price, String customerName, String customerAddres, String customerTel,Timestamp timestamp) {
		ordersrepository.doRegist(productsName, productsPrice, companyId, itemName, count, price, customerName,
				customerAddres, customerTel,timestamp);
	}

	
	public ArrayList<Integer> getIds() {
		List<CompaniesEntity> companiesEntityList = ordersrepository.getIds();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (CompaniesEntity id : companiesEntityList) {
			idList.add(id.getCompanyId());
		}
		return idList;
	}
	
	public ArrayList<Integer> getProductId() {
		List<OrderProductsEntity> orderProductsEntityList = ordersrepository.getProductId();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for (OrderProductsEntity id : orderProductsEntityList) {
			idList.add(id.getOrderProductsId());
		}
		return idList;
	}
	
	public void doDelete(int id) {
		ordersrepository.doDelete(id);
	}
	
}
