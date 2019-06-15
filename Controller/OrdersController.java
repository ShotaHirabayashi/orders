package com.exaple.orders.Controller;

import java.lang.ProcessBuilder.Redirect;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.From;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exaple.orders.Entity.AccountEntity;
import com.exaple.orders.Entity.OrderProductsEntity;
import com.exaple.orders.form.OrdersForm;
import com.exaple.orders.form.ViewForm;
import com.exaple.orders.service.AccountService;
import com.exaple.orders.service.OrderProductsService;
import com.exaple.orders.sesstion.LoginSession;
import com.mysql.cj.Session;

@Controller
public class OrdersController {

	//newをする
	@Autowired
	OrderProductsService ordersService;

@Autowired
LoginSession session;

@RequestMapping("/orders/view")
public ModelAndView get(@RequestParam(value="page", defaultValue="0") int pageNum,ViewForm form,BindingResult result) {
	// ページ情報取得
		Page<OrderProductsEntity> entityList = ordersService.getPage(pageNum,form.getId(),form.getProductsName(),form.getProductsPriceFrom(),form.getProductsPriceTo(),form.getCustomerName(),form.getCompanyName(),form.getSortDate());
		// ServiceのgetAll()メソッドを呼び出す
//	List<OrderProductsEntity> orderProductsEntityList =  ordersService.getAll();
	ModelAndView modelAndView = new ModelAndView();
	ArrayList<Integer> idList = ordersService.getProductId();
	modelAndView.addObject("orderProductsEntityList", entityList);
	modelAndView.addObject("idList",idList);
	modelAndView.setViewName("/view");
	
	modelAndView.addObject("id",form.getId());
	modelAndView.addObject("productsName",form.getProductsName());
	modelAndView.addObject("productsPriceTo",form.getProductsPriceTo());
	modelAndView.addObject("productsPriceFrom",form.getProductsPriceFrom());
	modelAndView.addObject("companyName",form.getCompanyName());
	modelAndView.addObject("sortDate",form.getSortDate());
	modelAndView.addObject("customerName",form.getCustomerName());
	
	
	return modelAndView;
}

@RequestMapping(value = "/orders/regist", method = RequestMethod.GET)
//行く先の時にもformのデータを持っていく
public ModelAndView insert(@ModelAttribute("form") OrdersForm form) {
	ModelAndView mav = new ModelAndView();
	mav.setViewName("/regist.html");
	mav.addObject("productsName");
	mav.addObject("productsPrice");
	mav.addObject("companyId");
	mav.addObject("itemName");
	mav.addObject("count");
	mav.addObject("price");
	mav.addObject("customerName");
	mav.addObject("customerAddress");
	mav.addObject("customerTel");
	ArrayList<Integer> idList = ordersService.getIds();
	mav.addObject("idsList", idList);
	return mav;
}

@RequestMapping(value = "/orders/registConfirm", method = RequestMethod.POST)
//行く先の時にもformのデータを持っていく
public ModelAndView registConfirm(@ModelAttribute("form") @Valid OrdersForm form, BindingResult result) {
	// ServiceのgetAll()メソッドを呼び出す
	ModelAndView mav = new ModelAndView();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	if(result.hasErrors()) {
		ArrayList<Integer> idList = ordersService.getIds();
		mav.addObject("idsList", idList);
		mav.addObject("form", form);
		mav.addObject("productsName", form.getProductsName());
		mav.addObject("productsPrice", form.getProductsPrice());
		mav.addObject("companyId", form.getCompanyId());
		mav.addObject("itemName", form.getItemName());
		mav.addObject("count", form.getCount());
		mav.addObject("price", form.getPrice());
		mav.addObject("customerName", form.getCustomerName());
		mav.addObject("customerAddress", form.getCustomerAddress());
		mav.addObject("customerTel", form.getCustomerTel());
		mav.setViewName("/regist.html");
	}else{
		mav.addObject("form", form);
		mav.addObject("productsName", form.getProductsName());
		mav.addObject("productsPrice", form.getProductsPrice());
		mav.addObject("companyId", form.getCompanyId());
		mav.addObject("itemName", form.getItemName());
		mav.addObject("count", form.getCount());
		mav.addObject("price", form.getPrice());
		mav.addObject("customerName", form.getCustomerName());
		mav.addObject("customerAddress", form.getCustomerAddress());
		mav.addObject("customerTel", form.getCustomerTel());
		
		mav.setViewName("/registConfirm.html");
		mav.addObject("timestamp",timestamp);
	}
	return mav;
}

@RequestMapping(value = "/orders/doRegist", method = RequestMethod.POST)
public String doRegist(@RequestParam("productsName") String productsName,@RequestParam("productsPrice") int productsPrice,@RequestParam("companyId") int companyId,@RequestParam("itemName") String itemName,@RequestParam("count") int count,@RequestParam("price") int price,@RequestParam("customerName") String customerName,@RequestParam("customerAddress") String customerAddress,@RequestParam("customerTel") String customerTel,@RequestParam("timestamp") Timestamp timestamp){ 
	ordersService.doRegist(productsName,productsPrice,companyId,itemName,count,price,customerName,customerAddress,customerTel,timestamp);	
	return "redirect:/orders/view";
}

@RequestMapping("/orders/detail")
public ModelAndView detail(@RequestParam("id") int id) {
	// ServiceのgetAll()メソッドを呼び出す
	OrderProductsEntity orderProductsEntity = ordersService.update(id);
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("entity", orderProductsEntity);
	modelAndView.setViewName("/detail");
	return modelAndView;
}

@RequestMapping("/orders/backregist")
public ModelAndView backregist(@ModelAttribute("form") @Valid OrdersForm form,@RequestParam("productsName") String productsName,@RequestParam("productsPrice") int productsPrice,@RequestParam("companyId") int companyId,@RequestParam("itemName") String itemName,@RequestParam("count") int count,@RequestParam("price") int price,@RequestParam("customerName") String customerName,@RequestParam("customerAddress") String customerAddress,@RequestParam("customerTel") String customerTel) {
	// ServiceのgetAll()メソッドを呼び出す
	ArrayList<Integer> idList = ordersService.getIds();

	ModelAndView mav = new ModelAndView();
	mav.addObject("idsList", idList);
	mav.addObject("form", form);
	mav.addObject("productsName", form.getProductsName());
	mav.addObject("productsPrice", form.getProductsPrice());
	mav.addObject("companyId", companyId);
	mav.addObject("itemName", form.getItemName());
	mav.addObject("count", form.getCount());
	mav.addObject("price", form.getPrice());
	mav.addObject("customerName", form.getCustomerName());
	mav.addObject("customerAddress", form.getCustomerAddress());
	mav.addObject("customerTel", form.getCustomerTel());
	mav.setViewName("/regist.html");
	return mav;
}


@RequestMapping("/orders/BackUpdate")
public ModelAndView backUpadate(@ModelAttribute("form") @Valid OrdersForm form,@RequestParam("productsName") String productsName,@RequestParam("productsPrice") int productsPrice,@RequestParam("companyId") int companyId,@RequestParam("itemName") String itemName,@RequestParam("count") int count,@RequestParam("price") int price,@RequestParam("customerName") String customerName,@RequestParam("customerAddress") String customerAddress,@RequestParam("customerTel") String customerTel) {
	// ServiceのgetAll()メソッドを呼び出す
	ArrayList<Integer> idList = ordersService.getIds();
	ModelAndView mav = new ModelAndView();
	mav.addObject("idsList", idList);
	mav.addObject("form", form);
	mav.addObject("productsName", form.getProductsName());
	mav.addObject("productsPrice", form.getProductsPrice());
	mav.addObject("companyId", companyId);
	mav.addObject("itemName", form.getItemName());
	mav.addObject("count", form.getCount());
	mav.addObject("price", form.getPrice());
	mav.addObject("customerName", form.getCustomerName());
	mav.addObject("customerAddress", form.getCustomerAddress());
	mav.addObject("customerTel", form.getCustomerTel());
	mav.addObject("id", form.getId());
	mav.setViewName("/update.html");
	return mav;
}


@RequestMapping(value = "/orders/deleteConfirm", method = RequestMethod.GET)
//行く先の時にもformのデータを持っていく
public ModelAndView delete(@RequestParam("id") int id) {
	// ServiceのgetAll()メソッドを呼び出す
	OrderProductsEntity orderProductsEntity = ordersService.update(id);
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("orderProductsEntity", orderProductsEntity);
	modelAndView.setViewName("/delete");
	return modelAndView;
}

@RequestMapping(value = "/orders/doDelete", method = RequestMethod.POST)
public String doDelete(@RequestParam("id") int id){
	//request(URL)のデータの取得してint idの箱で受け取ってね
	ordersService.doDelete(id);
	return "redirect:/orders/view";
}



@RequestMapping(value = "/orders/update", method = RequestMethod.GET)
//行く先の時にもformのデータを持っていく
public ModelAndView update(@ModelAttribute("form") OrdersForm form,@RequestParam("id") int id) {
	// ServiceのgetAll()メソッドを呼び出す
	OrderProductsEntity orderProductsEntity = ordersService.update(id);
	ArrayList<Integer> idList = ordersService.getIds();
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("productsName",orderProductsEntity.getOrderProductsName());
	modelAndView.addObject("price",orderProductsEntity.getPrice());
	modelAndView.addObject("itemName",orderProductsEntity.getOrdersEntity().getOrderItemName());
	modelAndView.addObject("companyId",orderProductsEntity.getOrdersEntity().getCompaniesEntity().getCompanyId());
	modelAndView.addObject("count",orderProductsEntity.getOrdersEntity().getOrderCount());
	modelAndView.addObject("customerName",orderProductsEntity.getReceivingOrdersEntity().getCustomerName());
	modelAndView.addObject("customerAddress",orderProductsEntity.getReceivingOrdersEntity().getCustomerAddress());
	modelAndView.addObject("customerTel",orderProductsEntity.getReceivingOrdersEntity().getCustomerTel());
	modelAndView.addObject("id",id);
	modelAndView.addObject("idsList", idList);
	modelAndView.setViewName("/update");
	return modelAndView;
}


@RequestMapping(value = "/orders/doUpdate", method = RequestMethod.POST)
public String doUpdate(@RequestParam("id") int id,@RequestParam("productsName") String productsName,@RequestParam("productsPrice") int productsPrice,@RequestParam("companyId") int companyId,@RequestParam("itemName") String itemName,@RequestParam("count") int count,@RequestParam("price") int price,@RequestParam("customerName") String customerName,@RequestParam("customerAddress") String customerAddress,@RequestParam("customerTel") String customerTel,@RequestParam("timestamp") Timestamp timestamp){ 
	ordersService.doUpdate(id,productsName,productsPrice,companyId,itemName,count,price,customerName,customerAddress,customerTel,timestamp);	
	return "redirect:/orders/view";
}

@RequestMapping(value = "/order/updateConfirm", method = RequestMethod.POST)
//public ModelAndView  doUpdate(@RequestParam("productsName") String productsName,@RequestParam("productsPrice") String productsPrice,@RequestParam("id") int id,@RequestParam("itemName") String itemName,@RequestParam("count") int count,@RequestParam("price") int price,@RequestParam("customerName") String customerName,@RequestParam("customerAddress") String customerAddress,@RequestParam("customerTel") String customerTel){
public ModelAndView  UpdateCofirm(@ModelAttribute("form") @Valid OrdersForm form, BindingResult result){		
	ModelAndView mav = new ModelAndView();
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
if(result.hasErrors()) {
	OrderProductsEntity orderProductsEntity = ordersService.update(form.getId());
	ArrayList<Integer> idList = ordersService.getIds();
	mav.addObject("form", form);
	mav.addObject("entity", orderProductsEntity);
	mav.addObject("idsList", idList);
	mav.setViewName("/update.html");
}else{
	mav.addObject("form", form);
	mav.addObject("id",form.getId());
	mav.addObject("productsName", form.getProductsName());
	mav.addObject("productsPrice", form.getProductsPrice());
	mav.addObject("companyId", form.getCompanyId());
	mav.addObject("itemName", form.getItemName());
	mav.addObject("count", form.getCount());
	mav.addObject("price", form.getPrice());
	mav.addObject("customerName", form.getCustomerName());
	mav.addObject("customerAddress", form.getCustomerAddress());
	mav.addObject("customerTel", form.getCustomerTel());
	mav.addObject("timestamp",timestamp);
	mav.setViewName("/doUpdate.html");
}
return mav;
	
}

@RequestMapping(value = "/login", method = RequestMethod.GET)
public String login() { 
	return "/login.html";
}


@RequestMapping(value = "/login-error", method = RequestMethod.GET)                                                                
public ModelAndView loginError(ModelAndView mav) {                                                                
    mav.addObject("isError", true);                                                            
    mav.setViewName("login");                                                            
    return mav;                                                            
}

//
////"/○○○"URLリクエストがGET送信で送られてきた場合に呼ばれる処理
//@RequestMapping(value="/users/detail", method = RequestMethod.GET)
//	public String init(@AuthenticationPrincipal  AccountEntity loginEntity) {
//	    
//	AccountEntity accountEntity2 = (AccountEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//}
@Autowired
AccountService accountService;


@RequestMapping(value = "/users/detail", method = RequestMethod.GET)
public ModelAndView  userDetail(@RequestParam("usersId") int usersId) {
	ModelAndView modelAndView = new ModelAndView();
	AccountEntity accountEntity = accountService.userDetail(usersId);
	modelAndView.addObject("accountEntity", accountEntity);
	modelAndView.addObject("usersId",usersId);
	modelAndView.setViewName("/usersDetail.html");
	return modelAndView;
}

@RequestMapping(value = "/users/update", method = RequestMethod.POST)
//行く先の時にもformのデータを持っていく
public ModelAndView Userupdate(@RequestParam("usersId") int usersId,@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {
	// ServiceのgetAll()メソッドを呼び出す
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("firstName",firstName);
	modelAndView.addObject("lastName",lastName);
	modelAndView.addObject("email",email);
	modelAndView.addObject("usersId",usersId);
	modelAndView.setViewName("/userUpdate");
	return modelAndView;
}

@RequestMapping(value = "/user/confirm", method = RequestMethod.POST)
//行く先の時にもformのデータを持っていく
public ModelAndView UserupdateConfirm(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("email") String email) {
	// ServiceのgetAll()メソッドを呼び出す
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.addObject("firstName",firstName);
	modelAndView.addObject("lastName",lastName);
	modelAndView.addObject("email",email);
	modelAndView.addObject("usersId",session.getUserId());
	modelAndView.setViewName("/usesrupdateComfirm");
	return modelAndView;
}




@RequestMapping(value = "/users/doUpdate", method = RequestMethod.POST)
public String doUserUpdate(@RequestParam("lastName") String lastName,@RequestParam("firstName") String firstName,@RequestParam("email") String email){ 
	Timestamp updatetime = new Timestamp(System.currentTimeMillis());
	accountService.doUserUpdate(session.getUserId(),lastName,firstName,email,updatetime);	
	return "redirect:/orders/view";
}


}


