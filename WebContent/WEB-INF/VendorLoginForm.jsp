<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<FORM action="/eCommerceApplication/basicVendorOperations" method="POST">

<H1> Login Vendor</H1>

		Enter the User Name  : <Input type="text" name="uName"><BR><BR>
		Enter the Password   : <Input type="password" name="passwd"><BR><BR>
		<Input type = "SUBMIT" VALUE = "LOGIN">
		<h2>${Message}</h2>
</FORM>

</body>
</html>