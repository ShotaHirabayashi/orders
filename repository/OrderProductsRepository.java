package com.exaple.orders.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.exaple.orders.Entity.CompaniesEntity;
import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.Entity.OrdersEntity;
import com.exaple.orders.Entity.ReceivingOrdersEntity;
import com.exaple.orders.interFace.JpaInterface;
import com.exaple.orders.interFace.JpaInterfaceCompany;
import com.exaple.orders.interFace.JpaInterfaceOrders;
import com.exaple.orders.interFace.JpaInterfaceRecevingOrders;




@Repository
public class OrderProductsRepository {

	@Autowired
	JpaInterface jpaInterface; // ←定義したインターフェースを使ってJPAのメソッドが使えます。
	
	@Autowired
	JpaInterfaceCompany jpaInterfaceCompany; 
	
	@Autowired
	JpaInterfaceRecevingOrders jpaInterfaceRecevingOrders;
	
	@Autowired
	JpaInterfaceOrders jpaInterfaceOrders;
	
//	public List<OrderProductsEntity> selectAll() {
//		// DBの内容を全件取得
//		List<OrderProductsEntity> orderProductsEntity = jpaInterface.findAll();
//		return orderProductsEntity;
//	}

public Page<OrderProductsEntity> getPage(int pageNum,int id,String productsName,int productsPriceFrom,int productsPriceTo,String customerName,String companyName,int sortDate) {
	// ソート条件を設定 new Sort(ソート条件, ソート基準（エンティティのプロパティ名）)
//	Sort sort = new Sort(Direction.ASC, "insertDatetime");
	
	Sort sort;
	if(sortDate == 1) {
		 sort = new Sort(Direction.DESC, "registrationDate");
	}else {
		 sort = new Sort(Direction.ASC, "registrationDate");
	}

	// new PageRequest(取得したいページ番号, 1ページあたりの件数, ソート条件)→あとでsortをたす
	Pageable pageable = PageRequest.of(pageNum, 3,sort);
	///////////////////////////////
	//検索条件の設定
	//あいまい検索のときに.like
	//検索がなかった＝全権表示
	//and,andがfalseの時にシンタックスエラーになるから1行目をかませる
	Specification<OrderProductsEntity> searchConditions = Specification.where((Specification<OrderProductsEntity>) null);
	if(id > 0) {
		Specification<OrderProductsEntity> searchId = (root, query, cb) -> cb.equal(root.get("orderProductsId"),id);
		searchConditions = searchConditions.and(searchId);
	}
	if(productsName != null && !productsName.isEmpty()) {
		Specification<OrderProductsEntity> searchProductsName = (root, query, cb) -> cb.like(root.get("orderProductsName"), "%"+ productsName + "%");
		searchConditions = searchConditions.and(searchProductsName);
	}
	if(customerName != null && !customerName.isEmpty()) {
		Specification<OrderProductsEntity> searchCustomerName = (root, query, cb) -> cb.like(root.get("receivingOrdersEntity").get("customerName"), "%"+ customerName + "%");
		searchConditions = searchConditions.and(searchCustomerName);
	}
	if(productsPriceFrom  > 0) {
		Specification<OrderProductsEntity> searchProductsPriceFrom = (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), productsPriceFrom );
		searchConditions = searchConditions.and(searchProductsPriceFrom);
	}
	if(productsPriceTo  > 0) {
		Specification<OrderProductsEntity> searchProductsPriceTo = (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"),  productsPriceTo);
		searchConditions = searchConditions.and(searchProductsPriceTo);
	}
	if(companyName != null && !companyName.isEmpty()) {
		Specification<OrderProductsEntity> searchCompanyName = (root, query, cb) -> cb.like(root.get("ordersEntity").get("companiesEntity").get("companyName"), "%"+ companyName + "%");
		searchConditions = searchConditions.and(searchCompanyName);
	}
	
	
	/////////////////////////////////////////
	// DBの内容をページ型で取得
	Page<OrderProductsEntity> entityList = jpaInterface.findAll(searchConditions,pageable);
	return entityList;
}
	
public void doRegist(String productsName,int productsPrice,int companyId,String itemName,int count,int price,String customerName,String customerAddres,String customerTel,Timestamp timestamp) {
	System.out.println(productsName+productsPrice+companyId+itemName+count+ price+customerName+customerAddres+customerTel+timestamp);
	OrderProductsEntity entity = new OrderProductsEntity(); //まだ空
	entity.setOrderProductsName(productsName);
	entity.setPrice(productsPrice);

	entity.setUpdateDate(timestamp);
	entity.setRegistrationDate(timestamp);
	ReceivingOrdersEntity rEntity = new ReceivingOrdersEntity();
	rEntity.setCustomerName(customerName); 
	rEntity.setCustomerAddress(customerAddres); 
	rEntity.setCustomerTel(customerTel); 
	
	OrdersEntity oEntity  = new OrdersEntity();
	oEntity.setCompanyId(companyId);
	oEntity.setOrderItemName(itemName);
	oEntity.setOrderCount(count); 
	oEntity.setOrderPrice(price); 
	
	entity.setOrdersEntity(oEntity);
	entity.setReceivingOrdersEntity(rEntity);
//	データをセットし1レコード=entity
	jpaInterface.save(entity);
}




	
	
	public OrderProductsEntity update(int id) {
		OrderProductsEntity orderProductsEntity =jpaInterface.getOne(id);
		return	orderProductsEntity;
	}
	
	public void doUpdate(int id,String productsName,int productsPrice,int companyId,String itemName,int count,int price,String customerName,String customerAddres,String customerTel,Timestamp timestamp) {
		OrderProductsEntity entity = jpaInterface.getOne(id);
		entity.setOrderProductsId(id);
		entity.setOrderProductsName(productsName);
		entity.setPrice(productsPrice);
		entity.setUpdateDate(timestamp);
		entity.getOrdersEntity().setCompanyId(companyId);
		entity.getOrdersEntity().setOrderItemName(itemName);
		entity.getOrdersEntity().setOrderCount(count); 
		entity.getOrdersEntity().setOrderPrice(price);
		entity.getReceivingOrdersEntity().setCustomerName(customerName); 
		entity.getReceivingOrdersEntity().setCustomerAddress(customerAddres); 
		entity.getReceivingOrdersEntity().setCustomerTel(customerTel); 
//		データをセットし1レコード=entity
		jpaInterface.save(entity);
	}
	

	public List<CompaniesEntity> getIds(){
	List<CompaniesEntity> companiesEntityList =jpaInterfaceCompany.findAll();
	return  companiesEntityList;
}
	
	public List<OrderProductsEntity> getProductId(){
	List<OrderProductsEntity> orderProductsEntityList =jpaInterface.findAll();
	return  orderProductsEntityList;
}	
	
	public void doDelete(int id) {
	OrdersEntity entityOrders = jpaInterfaceOrders.getOne(id);
		jpaInterfaceOrders.delete(entityOrders);
		
		ReceivingOrdersEntity entityReceving =jpaInterfaceRecevingOrders.getOne(id);
		jpaInterfaceRecevingOrders.delete(entityReceving);
		
		OrderProductsEntity entity = jpaInterface.getOne(id);
		jpaInterface.delete(entity);
	}
	
	
	
	
	
	

}
