<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<h1>Product List</h1>
					
			<table width="80%" border="2">
       <tr>
       <th>ProductId</th>
       <th>ProductName</th>
       <th>Price</th>
       <th>Availability</th>
       </tr>
				

			
			 <c:forEach var="pro" items="${listOfProducts}">
         <tr>
           <td>${pro.productId }</td>
           <td>${pro.productName}</td>
           <td>${pro.price}</td>
           <td>${pro.availability}</td>
         </tr>
       </c:forEach>
			
			</table>		
 <BR>
    <h2>${Message}</h2>
   <A href="/eCommerceApplication/addVendorProductForm">Add New Product</A><BR><BR>
   <A href="/eCommerceApplication/loginVendor">Logout</A><BR><BR>
   
					
 
</body>
</html>