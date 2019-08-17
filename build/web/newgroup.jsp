<%-- 
    Document   : newgroup
    Created on : Feb 5, 2018, 6:59:28 PM
    Author     : Parth Chutiyo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action='newgroupservlet' method="post">
        Enter Group Name : <input type="text" name='gname'><br/>
        Enter Event : <input type="text" name='event'><br/>
        Enter Party Description : <input type='textarea' name='descript'><br/>
        Enter Location : <input type='text' name='locate'><br/>
        <input type='submit' name='submit' value='submit'>
        </form>
    </body>
</html>
