package com.JohnDeere;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
	
    @RequestMapping("/loginAdmin")
	protected ModelAndView login() {

		ModelAndView M1 = new ModelAndView("AdminLoginForm");
		return M1;
	}

	
	@RequestMapping(value = "/basicAdminOperations", method = RequestMethod.POST)
	public ModelAndView basicEmpOp(@RequestParam("uName") String UserName, @RequestParam("passwd") String Password) {
		AdminDaoClass db = new AdminDaoClass();
		boolean result = db.loginAuthorization(UserName,Password);

		if (result) 
		{
			ModelAndView model = new ModelAndView("AdminOperationsPage");
			return model;
		}
		else 
		{
			ModelAndView model = new ModelAndView("AdminLoginForm");
			model.addObject("Message","Oops...Invalid user details, please try again!");
			return model;
		}
	}
	
	
	@RequestMapping("/productList")
	public ModelAndView viewEmployee() throws exception_class 
	{
		AdminDaoClass db=new AdminDaoClass();
		List<Product> ProductList=db.productList();
		
		ModelAndView model=new ModelAndView("ProductList");
		model.addObject("listOfProducts", ProductList);
		return model;
	}
	
	
	@RequestMapping(value="/addProductForm")
	public ModelAndView newEmployee()
	{
		ModelAndView model=new ModelAndView("AddNewProduct");
		return model;
	}
	
	@RequestMapping(value="/addNewProduct", method = RequestMethod.POST)
	public ModelAndView addNewEmployee(@RequestParam("ProductId") String ProductId, @RequestParam("ProductName") String ProductName, @RequestParam("Price") int Price, @RequestParam("Availability") String Availability)
	{
		AdminDaoClass db=new AdminDaoClass();
		boolean result=db.addNewProduct(ProductId, ProductName, Price, Availability);
		
		ModelAndView model=new ModelAndView("AddNewProduct");
		if(result)
		{			
			model.addObject("Message","Product added successfully!");
			return model;
		}
		
		else
		{	
			model.addObject("Message","Oops..Something went wrong!");
			return model;
		}
		
	}
	
	
	@RequestMapping("/Delete/{productId}")
	public  ModelAndView delete(@PathVariable String productId) throws exception_class
	{
		AdminDaoClass db=new AdminDaoClass();
		boolean result=db.deleteProduct(productId);
		
		if(result)
		{
			List<Product> ProductList=db.productList();
			ModelAndView model=new ModelAndView("ProductList");
			model.addObject("listOfProducts", ProductList);
			return model;
		}
		else 
		{
			ModelAndView model=new ModelAndView("ProductList");
			model.addObject("Message","Something went wrong while deleting the entry.Try again!");
			return model;
		}	
	}
	
	
	@RequestMapping("/Edit/{productId}")
	public  ModelAndView edit(@PathVariable String productId) throws exception_class
	{
		AdminDaoClass db=new AdminDaoClass();
		List<Product> result=db.editProduct(productId);
		
		ModelAndView model=new ModelAndView("EditProduct");
		
		model.addObject("ProductId",result.get(0).getProductId());
		model.addObject("ProductName",result.get(0).getProductName());
		model.addObject("Price",result.get(0).getPrice());
		model.addObject("Availability",result.get(0).getAvailability());
		return model;
	}
	
	@RequestMapping(value="/productUpdate",method=RequestMethod.POST)
	public  ModelAndView editEmployee(@RequestParam("ProductId") String ProductId,@RequestParam("ProductName") String ProductName, @RequestParam("Price") int Price,@RequestParam("Availability") String Availability) throws exception_class
	{
		AdminDaoClass db=new AdminDaoClass();
		boolean bool=db.updateProduct(ProductName,Price,Availability,ProductId);
		
		if(bool)
		{
			List<Product> ProductList=db.productList();
			ModelAndView model=new ModelAndView("ProductList");
			model.addObject("listOfProducts", ProductList);
			return model;
		}
		else
		{
			ModelAndView model=new ModelAndView("EditProduct");
			model.addObject("Message","Oops...Something went wrong,Try again!");
			return model;
		}
	}
}
