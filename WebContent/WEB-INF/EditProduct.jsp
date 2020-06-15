<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="/eCommerceApplication/productUpdate" method="POST">
     <h1>Edit Product</h1>
     ProductId       :<input type="text" name="ProductId" value="${ProductId}" ><BR><BR>
     ProductName     :<input type="text" name="ProductName" value="${ProductName}"><BR><BR>
     Price           :<input type="text" name="Price" value="${Price}"><BR><BR>
     Availability    :<input type="text" name="Availability" value="${Availability}"><BR><BR>
     <input type="submit" value="Edit Save"><BR><BR>
     <h2>${Message}</h2>
     </form>
</body>
</html>
