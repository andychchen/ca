<%-- 
    Document   : login
    Created on : Mar 20, 2010, 8:07:39 PM
    Author     : achen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Enchie Organic & Japanese Food Market Login Page</title>
        <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" />
    </head>
    <body OnLoad="document.loginForm.pwd.focus();">

        <h2>Welcom to <br>
            Enchie Organic & Japanese Food Market<br>
        Cash Registor System</h2>
        <br><br>
        <form name="loginForm" action="/ca/LoginServlet" method="post">
            <p><strong>Please Enter Your Emplyee ID: </strong>
            <input type="password" size="15" name="pwd">
            <p><p>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
