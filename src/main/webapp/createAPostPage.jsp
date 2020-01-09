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


	<h1>Post</h1>

	<form method="post" name="facebookPost" action="http://localhost:8080/post">
		<input type="text" name="post" /> <input value="Post" type="submit" />
	</form>

</body>
</html>