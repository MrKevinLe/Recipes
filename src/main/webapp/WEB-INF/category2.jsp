<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <link rel="stylesheet" type="text/css" href="/css/style6.css">
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
   
 
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
			<div><a href="/success" style="text-decoration: none;color:whitesmoke;"><h3>Home</h3></a></div>
		</div>
	</div>
</div>
<br>
<br>
<div id="container">
	<div id="top">
	<div style="width:30%"></div>
	<h1>${category.name }</h1>
	<div style="width:25%"></div>
	</div>
	<br>
	<br>

	<div id="body">
	<ul>
		<c:forEach items="${allrecipes }" var="one">
 		<c:if test="${one.category.id == category.id}">
			<li><h3><a href="/view2/${one.id}" style="text-decoration: none;color:whitesmoke;">${one.title}</a></h3></li>
	   </c:if>
		</c:forEach>
	</ul>
	</div>

</div>

</body>
</html>