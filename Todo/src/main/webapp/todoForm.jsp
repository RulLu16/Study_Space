<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#addPage{
	width : 500px;
	text-align : center;
	margin :0 auto;
}

#main{

	text-align : left;
}
.button{
	float : right;
	margin : 3px;
}
</style>
<script>
function remove(){
	alert(2);
}
</script>
</head>
<body>
	<div id="addPage">
		<h2>Add TodoList</h2><br>
		<div id="main">
		<h4>What's title?</h4>
		<form class="input" method="POST" action="TodoAddServlet">
			<input type="text" name="title" placeholder="Study JAVA(max-lenght 24)" size="24">	
			<br>
			<h4>Who?</h4>
			<input type="text" name="name" placeholder="James" size="6">	<br>
			<h4>Select the Sequence</h4>
			<input type="radio" name="sequence" value="1" checked>
			Sequence 1
			<input type="radio" name="sequence" value="2">
			Sequence 2
			<input type="radio" name="sequence" value="3">
			Sequence 3<br><br>
			<button id="pre" onclick="location='MainServlet'" ><-Pre</button>

			<input class="button" type="submit">
			<input class="button" type="reset">
		</form>
		
		<br><br>
		
		</div>
		
	</div>
</body>
</html>