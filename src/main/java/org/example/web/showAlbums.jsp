<%@page import="org.example.hibernate.entity.Singer"%>
<%@page import="org.example.hibernate.entity.Album"%>
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
        <p>Albums</p>
        <p><a href="album?action=showAlbums&type=2">Show albums with at least two songs</a></p>
        <p><a href="album?action=showAlbums">Show all</a></p>
        <table border="1" id ='myTable'>
        <tr>
        <th>ID album</th>
        <th>Name</th>
        <th>Genre</th>
        <th>ID singer</th>
        <th colspan=2>Action</th>
        </tr>
        <c:forEach items="${albumList}" var="album">
        <tr>
            <td><c:out value="${album.id}" /></td>
            <td><c:out value="${album.name}" /></td>
            <td><c:out value="${album.genre}" /></td>
            <td><c:out value="${album.singer.id}" /></td>
            <td><a href="album?action=update&albumId=<c:out value='${album.id}'/>">Update</a></td>
            <td><a href="album?action=delete&albumId=<c:out value='${album.id}'/>">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
        <a href="album?action=add">Add new</a>
        <p><a href="index.jsp">Main page</a>
    </body>
</html>