<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Hello JSP</title>
</head>
<body>
<h2>
    <%=3 + 2%>
</h2>

<%!
    int a = 3;
    int b = 5;
%>
<%
    out.println("a = " + a);
    out.println("b = " + b);
%>

<%
    for (int i = 0; i < 10; i++) {
%>
<h2>
    <%=i%>
</h2>
<%
    }
%>
</body>
</html>
