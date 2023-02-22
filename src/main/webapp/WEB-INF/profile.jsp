<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
 <link rel="stylesheet" type="text/css" href="/css/style5.css">
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
			<div><a href="/createR" style="text-decoration: none;color:whitesmoke;"><h2>Post</h2></a></div>
			<div><a href="/success" style="text-decoration: none;color:whitesmoke;"><h3>Home</h3></a></div>
			<div><a href="/logout" style="text-decoration: none;color:whitesmoke;"><h3>LogOut</h3></a></div>
		</div>
	</div>
</div>
<br>
<br>
<div id="container3">
	<div id="pro-top">
	<div style="width:25%;height:25%;"></div>
	<h1>${thisUser.first_name } ${thisUser.last_name }</h1>
	<div style="width:25%;height:25%;"></div>
	</div>
	<br>
	<div id="pro-bottom">
	<h3>${thisUser.email }</h3>
	</div>

</div>
<br>
<div id='container4'>
	<div style="height:5%;"></div>
	<div id="liked-top">
		<h1>Saved Recipes</h1>
	</div>
	<br>
	<div id="liked">
	<c:forEach items="${allLikedrecipes }" var="one">
	<c:forEach items="${allrecipes }" var="r">
	<c:if test="${one.recipe.id == r.id  }">
	<ul>
		<li><a href="/view/${r.id}"><h3 style="text-decoration: none;color:whitesmoke;">${r.title}</h3></a></li>
	</ul>
	 </c:if>
	</c:forEach>
	</c:forEach>
	
	</div>

</div>


</body>
</html>