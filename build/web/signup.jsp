

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action='signupservlet' method='post'>
            Id :<input type="text" name="id"><br/>
        First Name :     <input type="text" name="first"><br/>
        Last Name    <input type="text" name="last"><br/>
        Email    <input type="email" name="mail"><br/>
        Phone number    <input type="text" name="phone"><br/>
        password    <input type="password" name="pass12"><br/>
        Renter Password    <input type="password" name="pass2"><br/>
            <input type='submit' name='submit' value='submit'>
        </form>
    </body>
</html>
