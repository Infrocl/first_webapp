<%@page import="org.example.hibernate.entity.Singer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <style>
        <%@include file='styles.css' %>
    </style>
    <head>
        <meta charset="UTF-8" />
        <title>Music shop</title>
    </head>
    <body>
        <h2>Music shop</h2>
        <p>Singers</p>
        <table border="1" id ='myTable'>
        <tr>
        <th>ID</th>
        <th>Name</th>
        <th colspan=2>Action</th>
        </tr>
        <c:forEach items="${singerList}" var="singer">
        <tr>
            <td><c:out value="${singer.id}" /></td>
            <td><c:out value="${singer.name}" /></td>
            <td><a href="singer?action=update&singerId=<c:out value='${singer.id}'/>">Update</a></td>
            <td><a href="singer?action=delete&singerId=<c:out value='${singer.id}'/>">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
        <a href="singer?action=add">Add new</a>
        <p><a href="index.jsp">Main page</a>
    </body>
</html>