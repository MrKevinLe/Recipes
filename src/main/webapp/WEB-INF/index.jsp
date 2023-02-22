<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<link rel="stylesheet" type="text/css" href="/css/style2.css">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="nav">
	<div id="left-nav">
	<h1>Title</h1>
	</div>
	<div id="right-nav">
		<div id="nav-menu">
			<div><a href="/" style="text-decoration: none;color:whitesmoke;"><h2>Home</h2></a></div>
		</div>
	</div>
</div>
<br>
<br>
<div id="container">
	<div id="reg">
<h2>Register</h2>
	<br>
	<form:form action="/reg" method="post" modelAttribute="user">
	<form:errors path="*" style="color:red"/>
	<div class="mb-3 ">	
	<form:label path="first_name"><h4>First Name</h4></form:label>
	<form:input path="first_name" class="form-control"/>
	</div>
	<div class="mb-3 ">	
	<form:label path="last_name"><h4>Last Name</h4></form:label>
	<form:input path="last_name" class="form-control"/>
	</div>
	<div class="mb-3 ">	
	<form:label path="email"><h4>Email</h4></form:label>
	<form:input path="email" type="email" class="form-control"/>
	</div>
	<div class="mb-3 ">	
	<form:label path="password"><h4>Password</h4></form:label>
	<form:input path="password" type="password" class="form-control"/>
	</div>
	<div class="mb-3 ">	
	<form:label path="confirm"><h4>Password Confirm</h4></form:label>
	<form:input path="confirm" type="password" class="form-control"/>
	</div>
	<br>
	<button type="submit" id="button-reg">Submit</button>
	</form:form>
	</div>
	<div id="login">
	<h1>Log In</h1>	
	<form:form action="/login" method="post" modelAttribute="newlogin">
	<form:errors path="*" style="color:red"/><br/>
	
	<div class="mb-3 ">	
	<form:label path="email"><h4>Email</h4></form:label>
	<form:input path="email" type="email" class="form-control"/>
	</div>
	<div class="mb-3 ">	
	<form:label path="password"><h4>Password</h4></form:label>
	<form:input path="password" type="password" class="form-control"/>
	</div>
	<br>
	<button type="submit" id="button-log">Submit</button>
	</form:form>
	
	</div>
</div>
	</body>
</html>