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
<title>Song</title>
</head>
<body>
    <form method="POST" action='song' name='form' onsubmit="return validate();">
    <div class = 'forms'>
        Song ID : <input type="text" readonly="readonly" name="songId"
            value="<c:out value='${song.id}'/>"/> <br/>
    </div>
    <div>
        Name : <input type="text" name="songName" id = "sgName" autocomplete="off" required
            value="<c:out value='${song.name}'/>"/> <br/>
    </div>
    <div class = 'forms'>
        Duration : <input type="text" name="songDuration" id="duration" autocomplete="off" required
            value="<c:out value='${song.duration}'/>"/> <br/>
        Album ID : <input type="number" name="albumId" id = "aId" autocomplete="off" required
             value="<c:out value='${song.album.id}'/>"/> <br/>
    </div>
        <input type="submit" value="Submit"/>
    </form>
</body>
<script type="text/javascript">
    <%@include file="songScript.js"%>
</script>
</html>