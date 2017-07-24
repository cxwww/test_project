<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../resources/public/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../resources/public/jquery/jquery.json.min.js"></script>

<script type="text/javascript" src="../resources/js/showUser.js"></script>

</head>
<body>
<div id="info"></div>
1
<c:forEach items="${userlist}" var="item">  
        ${item.id}${item.age}<br />  
    </c:forEach>  
<form action="add" method="post" id="form">
编号：<input type="text" name="id"/>
姓名：<input type="text" name="username"/>
年龄：<input type="text" name="age"/>

<input type="button" value="提交" id="submit"/>
</form>
</body>
</html>