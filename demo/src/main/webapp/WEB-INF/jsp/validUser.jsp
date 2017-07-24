<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>valid user</title>

<script type="text/javascript" src="../resources/public/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../resources/public/jquery/jquery.json.min.js"></script>

</head>
<body>

<form:form commandName="usero" action="/learn/valid/signup" method="POST"  >
 <form:errors path="*" cssClass="error" /><br/>
 用户名：<form:input path="username"></form:input>
<form:errors path="username" cssClass="error" />
<input type="submit"  />
</form:form>

</body>
</html>