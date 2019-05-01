<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add result</title>
</head>
<body>
<%
    double n1 = (double) request.getAttribute("num1");
    double n2 = (double) request.getAttribute("num2");
%>
<h2>
    <%=n1%> + <%=n2%> = <%=n1 + n2%>
</h2>
</body>
</html>
