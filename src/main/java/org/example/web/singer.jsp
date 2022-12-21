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
<title>Singer</title>
</head>
<body>
    <form method="POST" action='singer'>
        <div class = 'forms'>
        Singer ID : <input type="text" readonly="readonly" name="singerId"
            value="<c:out value='${singer.id}'/>"/> <br/>
        </div>
        <div>
        Name : <input type="text" name="singerName" id="sName" autocomplete="off" required
                  value="<c:out value='${singer.name}'/>"/> <br/>
        </div>
             <input type="submit" value="Submit"/>
    </form>
</body>
<script type="text/javascript">
    <%@include file="singerScript.js"%>
</script>
</html>