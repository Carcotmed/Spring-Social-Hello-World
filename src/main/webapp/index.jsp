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

	<h1>Connection to Facebook via Spring Social</h1>
	<p>
		<a href=<c:out value="${facebookUrl}"></c:out>
			<!-- "/createFacebookAuthorization" -->> Get an url to connect to Facebook
		</a>
	</p>

</body>
</html>
