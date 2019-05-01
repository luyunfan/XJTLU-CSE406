<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
    <title>Success</title>
</head>
<body>
<jsp:useBean id="USER" class="per.yunfan.cse406.javaee.beans.User" scope="request"></jsp:useBean>
<h2>
    Welcome, <%=request.getParameter("user")%>
</h2>
<h3>
    Name:
    <jsp:getProperty property="username" name="USER"></jsp:getProperty>
    <br>
    Age:
    <jsp:getProperty property="age" name="USER"></jsp:getProperty>
    <br>
    Country:
    <jsp:getProperty property="country" name="USER"></jsp:getProperty>
    <br>
</h3>
</body>
</html>
