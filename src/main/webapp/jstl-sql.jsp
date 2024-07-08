<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title> c:sql </title>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<sql:setDataSource driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/db_b202010247"
                   user="u_b202012047"
                   password="cometrue" />

<sql:query var="users" startRow="0" >
    update t_mb202012047 set zipcode = '?' where mid = 1;
    <sql:param value="00123" />
</sql:query>

<c:forEach var="row" items="${users.rows}">
    ${row.mid} - ${row.fullname} - ${row.email} - ${row.zipcode} <br>
</c:forEach>
</body>
</html>

