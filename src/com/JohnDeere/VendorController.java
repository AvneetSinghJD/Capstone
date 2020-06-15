package com.JohnDeere;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VendorController {

	 @RequestMapping("/loginVendor")
		protected ModelAndView login() {

			ModelAndView M1 = new ModelAndView("VendorLoginForm");
			return M1;
		}

		
		@RequestMapping(value = "/basicVendorOperations", method = RequestMethod.POST)
		public ModelAndView basicEmpOp(@RequestParam("uName") String UserName, @RequestParam("passwd") String Password) {
			VendorDaoClass db = new VendorDaoClass();
			boolean result = db.loginAuthorization(UserName,Password);

			if (result) 
			{
				ModelAndView model = new ModelAndView("VendorOperationsPage");
				return model;
			}
			else 
			{
				ModelAndView model = new ModelAndView("VendorLoginForm");
				model.addObject("Message","Oops...Invalid user details, please try again!");
				return model;
			}
		}
		
		@RequestMapping("/vendorProductList")
		public ModelAndView viewEmployee() throws exception_class 
		{
			AdminDaoClass db=new AdminDaoClass();
			List<Product> ProductList=db.productList();
			
			ModelAndView model=new ModelAndView("VendorProductList");
			model.addObject("listOfProducts", ProductList);
			return model;
		}
		
		@RequestMapping(value="/addVendorProductForm")
		public ModelAndView newEmployee()
		{
			ModelAndView model=new ModelAndView("AddVendorProduct");
			return model;
		}
		
		
		@RequestMapping(value="/addNewVendorProduct", method = RequestMethod.POST)
		public ModelAndView addNewEmployee(@RequestParam("ProductId") String ProductId, @RequestParam("ProductName") String ProductName, @RequestParam("Price") int Price, @RequestParam("Availability") String Availability)
		{
			AdminDaoClass db=new AdminDaoClass();
			boolean result=db.addNewProduct(ProductId, ProductName, Price, Availability);
			
			ModelAndView model=new ModelAndView("AddVendorProduct");
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
}
