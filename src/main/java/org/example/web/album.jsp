<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
        <%@include file='styles.css' %>
</style>
    <head>
        <meta charset="UTF-8" />
        <title>Music shop</title>
    </head>
<title>Album</title>
</head>
<body>
    <form method="POST" action='album'>
        <div class = 'forms'>
        Album ID : <input type="text" readonly="readonly" name="albumId"
            value="<c:out value='${album.id}'/>"/> <br/>
        </div>
        <div>
        Name : <input type="text" name="albumName" id = "aName" autocomplete="off" required
            value="<c:out value='${album.name}'/>"/> <br/>
        </div>
        <div class = 'forms'>
        Genre : <input type="text" name="albumGenre" autocomplete="off" required
            value="<c:out value='${album.genre}'/>"/> <br/>
        </div>
        <div>
        Singer ID : <input type="number" name="singerId" id = "sId" autocomplete="off" required
             value="<c:out value='${album.singer.id}'/>"/> <br/>
        </div>
        <input type="submit" value="Submit"/>
    </form>
</body>
<script type="text/javascript">
    <%@include file="albumScript.js"%>
</script>
</html>