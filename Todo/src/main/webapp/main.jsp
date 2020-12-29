<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import = "kr.ac.sogang.java.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.main{
		float : right;
		margin-right : 3px;
		width : 1000px;
	}
	#title{
		float : right;
		font-size : 70px;
		
	}
	#button{
		height : 80px;
		margin-right : 25px;
	}
	#add{
		height : 60px;
		width : 20%;
		background-color : #50bcdf;
		text-align : center;
		color : white;
		float : right;
	}
	.list{
		display : inline;
		height : 200px;
		width : 30%;
		margin : 5px;
		float : left;
	}
	.list_title{
		height : 70px;
		
		padding : 5px;
		background-color : #000080;
		text-align : left;
		color : white;
	}
	.item > *{
		
		height : 90px;
		
		background-color : #ADD8E6;
		text-align : left;
		list-style : none;
	}
	.moveList{
		float : right;
		width : 25px;
		height : 25px;
		font-size : 2px;
	}
	#title{
		float : left;
	}
</style>
<script type="text/javascript">
	function move(type, id){
		var oReq = new XMLHttpRequest();
		var addr="http://localhost:8080/Todo/TodoTypeServlet?type="+type+"&id="+id;
		 oReq.addEventListener("load", function() {
			 //print(type,id);
			 if(type=="Todo"){
				 var msg = document.getElementById("updoing");
				 var num=String(id);
				 var del=document.getElementById(num);
	             msg.innerHTML.append(del);	
	             del.innerHTML=""; 
			 }
			 else if(type=="Doing"){
				 var msg = document.getElementById("updone");
				 var num=String(id);
				 var del=document.getElementById(num);
				 msg.innerHTML.append(del);
				 del.innerHTML="";
			 }
		 });    
		 oReq.open("GET",addr);//parameter를 붙여서 보낼수있음. 
		 oReq.send();
	}
</script>
<%!
	List<TodoDto> list;
	TodoDto dto;
%>

</head>
<body>
<div id="title">
	TodoList
</div>
<div class="main">
<div id="button">
<button id="add" onclick = "location='TodoFormServlet'">
		Add new todo
	</button>
	</div>
<div class="list">
	<div class="list_title">
		<h3>TODO</h3>
	</div>
	<ui class="item" id="uptodo">
		<%
		list=(List<TodoDto>)request.getAttribute("todo");
		if(list!=null){		
			for(int i=0;i<list.size();i++){
				dto=list.get(i);
				if("Todo".equals(dto.getType())){ 
					
				%>
					<li class="item_todo" id='<%=dto.getId() %>'> <h3><%=dto.getTitle()%></h3>
					Date : <%=dto.getRegDate()%> <%=dto.getName()%> Sequence : <%=dto.getSequence()%>
					<button class="moveList" onclick="move('<%=dto.getType()%>', <%=dto.getId()%>)"> -> </button>
					</li>
					<%
				}
			}	
		}
		%>
	</ui>
</div>

<div class="list">
	<div class="list_title">
		<h3>DOING</h3>
	</div>
	<ui class="item" id="updoing">
				<%
				list=(List<TodoDto>)request.getAttribute("todo");
		if(list!=null){
			for(int i=0;i<list.size();i++){
				dto=list.get(i);				
				if("Doing".equals(dto.getType())){ 
				%>
					<li class="item_doing" id='<%=dto.getId() %>'> <h3><%=dto.getTitle()%></h3>
					Date : <%=dto.getRegDate()%> <%=dto.getName()%> Sequence : <%=dto.getSequence()%>
					<button class="moveList" onclick="move('<%=dto.getType()%>', <%=dto.getId()%>)"> -> </button>
					</li>
					<%
				}
			}	
		}
		%>
	</ui>
</div>

<div class="list">
	<div class="list_title">
		<h3>DONE</h3>
	</div>
	<ui class="item" id="updone">
				<%
				list=(List<TodoDto>)request.getAttribute("todo");
		if(list!=null){
			for(int i=0;i<list.size();i++){
				dto=list.get(i);				
				if("Done".equals(dto.getType())){ 
				%>
					<li class="item_done" id='<%=dto.getId() %>'> <h3><%=dto.getTitle()%></h3>
					Date : <%=dto.getRegDate()%> <%=dto.getName()%> Sequence : <%=dto.getSequence()%>
					
					</li>
					<%
				}
			}	
		}
		%>
	</ui>
</div>
</div>
</body>
</html>