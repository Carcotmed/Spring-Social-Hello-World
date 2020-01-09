<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Landing Page</title>
<style>
body {
	text-align: center;
}
</style>
</head>
<body>

	<h1>${userName}</h1>
	<p>Id: ${userId}</p>

	<h1>Friends</h1>

	<c:if test="${empty friendsList}">
		<p>You don't have any friends that use this app.</p>
	</c:if>

	<c:forEach var="f" items="${friendsList}">

		<p>Friend: ${f.getName()}</p>

	</c:forEach>

	<!-- 
<p><a href="localhost:8080/createAPost">Post something</a></p>
 -->

</body>
</html>
