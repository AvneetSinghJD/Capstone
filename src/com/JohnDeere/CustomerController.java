package com.JohnDeere;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {

    @RequestMapping("/loginCustomer")
	protected ModelAndView login() {

		ModelAndView M1 = new ModelAndView("CustomerLoginForm");
		return M1;
	}
    
	@RequestMapping(value = "/customerPage", method = RequestMethod.POST)
	public ModelAndView basicCusOp(@RequestParam("uName") String UserName, @RequestParam("passwd") String Password) {
		CustomerDaoClass db = new CustomerDaoClass();
		boolean result = db.loginAuthorizationCustomer(UserName,Password);

		if (result) 
		{
			ModelAndView model = new ModelAndView("CustomerPage");
			return model;
		}
		else 
		{
			ModelAndView model = new ModelAndView("CustomerLoginForm");
			model.addObject("Message","Oops...Invalid user details, please try again!");
			return model;
		}
	}
	
	@RequestMapping("/customerProductList")
	public ModelAndView viewProduct() throws exception_class 
	{
		AdminDaoClass db=new AdminDaoClass();
		List<Product> ProductList=db.productList();
		
		ModelAndView model=new ModelAndView("CustomerProductList");
		model.addObject("listOfProducts", ProductList);
		return model;
	}
	
	@RequestMapping("/addToCart/{productId}")
	public  ModelAndView edit(@PathVariable String productId) throws exception_class
	{
		AdminDaoClass db=new AdminDaoClass();
		List<Product> result=db.editProduct(productId);
		
		ModelAndView model=new ModelAndView("CustomerAddProduct");
		
		model.addObject("ProductId",result.get(0).getProductId());
		model.addObject("ProductName",result.get(0).getProductName());
		model.addObject("Price",result.get(0).getPrice());
		model.addObject("Availability",result.get(0).getAvailability());
		return model;
	}
	
	@RequestMapping(value="/goToCart",method=RequestMethod.POST)
	public  ModelAndView addToCart(@RequestParam("ProductId") String ProductId,@RequestParam("ProductName") String ProductName, @RequestParam("Price") int Price,@RequestParam("Availability") String Availability,@RequestParam("Quantity") int Quantity) throws exception_class
	{
		CustomerDaoClass db=new CustomerDaoClass();
		boolean bool=db.addToCart(ProductId,ProductName,Price,Availability,Quantity);
		
		if(bool)
		{
			List<CustomerProduct> custProductList=db.customerProductList();
			ModelAndView model=new ModelAndView("CustomerCart");
			model.addObject("listOfProducts", custProductList);
			return model;
		}
		else
		{
			ModelAndView model=new ModelAndView("CustomerAddProduct");
			model.addObject("Message","Oops...Product Already added, Go to Cart!");
			return model;
		}
	}
	
	@RequestMapping("/customerCartList")
	public ModelAndView viewCart() throws exception_class 
	{
		CustomerDaoClass db=new CustomerDaoClass();
		List<CustomerProduct> custProductList=db.customerProductList();
		
		ModelAndView model=new ModelAndView("CustomerCart");
		model.addObject("listOfProducts", custProductList);
		return model;
	}
	
    @RequestMapping("/customerPayment")
	protected ModelAndView customerPayment() {

		ModelAndView M1 = new ModelAndView("CustomerPaymentPage");
		return M1;
	}
    
	@RequestMapping("/delete/{productId}")
	public  ModelAndView delete(@PathVariable String productId) throws exception_class
	{
		CustomerDaoClass db=new CustomerDaoClass();
		boolean result=db.deleteProduct(productId);
		
		if(result)
		{
			List<CustomerProduct> custProductList=db.customerProductList();
			ModelAndView model=new ModelAndView("CustomerCart");
			model.addObject("listOfProducts", custProductList);
			return model;
		}
		else 
		{
			ModelAndView model=new ModelAndView("CustomerCart");
			model.addObject("Message","Something went wrong while deleting the entry.Try again!");
			return model;
		}	
	}
}
