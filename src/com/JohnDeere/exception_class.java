package com.JohnDeere;

@SuppressWarnings("serial")
public class exception_class extends Exception {

	String str1;
	 
	exception_class(String str2) {
		str1=str2;
	   }
   public String toString(){ 
		return ("MyException Occurred: "+str1) ;
	  }
}
