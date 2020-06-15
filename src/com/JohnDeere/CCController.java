package com.JohnDeere;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CCController {

	 @RequestMapping("/loginCC")
		protected ModelAndView login() {

			ModelAndView M1 = new ModelAndView("CCLoginForm");
			return M1;
		}
	 
		@RequestMapping(value = "/basicCCOperations", method = RequestMethod.POST)
		public ModelAndView basicEmpOp(@RequestParam("uName") String UserName, @RequestParam("passwd") String Password) {
			CCDaoClass db = new CCDaoClass();
			boolean result = db.loginAuthorization(UserName,Password);

			if (result) 
			{
				ModelAndView model = new ModelAndView("CCOperationsPage");
				return model;
			}
			else 
			{
				ModelAndView model = new ModelAndView("CCLoginForm");
				model.addObject("Message","Oops...Invalid user details, please try again!");
				return model;
			}
		}
		
		@RequestMapping("/popularProductList")
		public ModelAndView viewEmployee() throws exception_class 
		{
			CCDaoClass db=new CCDaoClass();
			List<CustomerProduct> custProductList=db.customerProductList();
			
			ModelAndView model=new ModelAndView("CCView");
			model.addObject("listOfProducts", custProductList);
			return model;
		}
}
