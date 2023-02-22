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
 <link rel="stylesheet" type="text/css" href="/css/style1.css">
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
			<div><a href="/success" style="text-decoration: none;color:whitesmoke;"><h3>Home</h3></a></div>
			<div><a href="/profile/${thisUser.id }" style="text-decoration: none;color:whitesmoke;"><h2>${thisUser.first_name }</h2></a></div>
			<div><a href="/logout" style="text-decoration: none;color:whitesmoke;"><h3>LogOut</h3></a></div>
		</div>
	</div>
</div>
	<br>
	<br>
	<br>
<div id="container2">
	 <div id="create-title">
	 	<h1>Create a Recipe</h1>
	</div>
	<br>
<div id="form-create">
 <div id="form-form">
<form:form action="/submitR" method="post" modelAttribute="recipe" >
<form:input type="hidden" path="user" value="${session }"/>
<form:errors path="*" style="color:red"/>

<div class="mb-3 " >
<form:label path="title"><h3>Title:</h3></form:label>
<form:input path="title" class="form-control" />
</div>
<div >
<form:label path="category"> <h3>Category:</h3> </form:label>
<form:select path="category" class="form-select">
<c:forEach items="${allcat }" var="cat">
<form:option value="${cat.id }">
${cat.getName()}
</form:option>
</c:forEach>
</form:select>
</div>
<div class="mb-3 ">
<form:label path="ingredients"> <h3>ingredients:</h3> </form:label>
<form:input path="ingredients" class="form-control"/>
</div>
<form:label path="instruction"> <h3>instruction:</h3> </form:label>
<div class="form-floating">
<form:textarea path="instruction" class="form-control h-25" ></form:textarea>
</div>
<div class="mb-3">
<form:label path="description"> <h3>Image url:</h3> </form:label>
<form:input path="description" class="form-control"/>
</div>
<br>
<button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</div>
</div>
</div>
</body>
</html>