<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <link rel="stylesheet" type="text/css" href="/css/style5.css">
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
<div id="container7">
	<div id="top-one">
	<img src="${recipe.description }" style="width:460px;height:250px;border-radius:10px"/>
	</div>
	<br>
	<div id="bot-one">
		<h2>${recipe.title }</h2>
		<c:forEach items="${allcategory }" var="one">
		<c:if test="${recipe.category.id == one.id }">
		<h3>${one.name }</h3>
		</c:if>
		</c:forEach>

	</div>
	<br>
		<div id="bot-two">
		<div id="ingre">
		<h2 style="text-align:center;">Ingredients</h2>
		<h3 style="text-align:center;">${recipe.ingredients }</h3>
		</div>
		<div id="inst">
		<h2 style="text-align:center;">Instructions</h2>
		<h3 style="text-align:center;">${recipe.instruction }</h3>
		</div>
		</div>
	</div>
</div>

</body>
</html>