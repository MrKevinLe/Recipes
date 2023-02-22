<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
 <link rel="stylesheet" type="text/css" href="/css/style.css">
 
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
			<div><a href="/profile/${thisUser.id }" style="text-decoration: none;color:whitesmoke;"><h2>${thisUser.first_name }</h2></a></div>
			<div><a href="/createR" style="text-decoration: none;color:whitesmoke;"><h2>Post</h2></a></div>
			<div><a href="/logout" style="text-decoration: none;color:whitesmoke;"><h3>LogOut</h3></a></div>
		</div>
	</div>
</div>
<br>
<br>

<div id="category">
	<div id="singe-category">
		<c:forEach items="${allcategory }" var="cat">
			<button class="button"><a href="/category/${cat.id }" style="text-decoration: none;color:whitesmoke;">${cat.name }</a></button>
		</c:forEach>
	</div>
</div>
	
</div>
<br>
<br>
<br>
<div id="container">
<c:forEach items="${allrecipes }" var="one">
	<div id="single-post">
		<div id="inner-post">
		<h1>${one.title}</h1>
		<c:if test="${one.users.contains(thisUser) eq false }">
      	<a href="/likerecipe/${one.id }"><img alt="Heart" src="/css/outline.png" style="width:45px;height:40px;margin-top:5px;"/></a>
      </c:if>
       <c:if test="${one.users.contains(thisUser)}">
      	<a href="/unlikerecipe/${one.id }"><img alt="Heart" src="/css/heart.png" style="width:45px;height:40px;margin-top:5px;"/></a>
      </c:if>
		</div>
		<br>
		<div id="inner-pic">
		<a href="/view/${one.id }"><img src="${one.description }" alt="food" id="pic"/></a>
		</div>
		<br>
		<div id="inner-desc">
		<p>${one.instruction }</p>
		</div>
	</div>
</c:forEach>
</div>


</body>
</html>