<%@page import="org.example.hibernate.entity.Song"%>
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
        <p>Songs</p>
        <p><a href="song?action=showSongs&type=genre">Show pop songs</a></p>
        <p><a href="song?action=showSongs">Show all</a></p>
        <table border="1" id ='myTable'>
        <tr>
        <th>ID song</th>
        <th>Name</th>
        <th>Duration</th>
        <th>ID album</th>
        <th colspan=2>Action</th>
        </tr>
        <c:forEach items="${songList}" var="song">
        <tr>
            <td><c:out value="${song.id}" /></td>
            <td><c:out value="${song.name}" /></td>
            <td><c:out value="${song.duration}" /></td>
            <td><c:out value="${song.album.id}" /></td>
            <td><a href="song?action=update&songId=<c:out value='${song.id}'/>">Update</a></td>
            <td><a href="song?action=delete&songId=<c:out value='${song.id}'/>">Delete</a></td>
        </tr>
        </c:forEach>
        </table>
        <a href="song?action=add">Add new</a>
        <p><a href="index.jsp">Main page</a>
    </body>
</html>