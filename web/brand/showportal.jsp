/*
Beginning JavaServer Pages
Vivek Chopra, Jon Eaves, Rupert Jones, Sing Li, John T. Bell
ISBN: 0-7645-7485-X

*/


<html>
<head><title>Select Your Portal</title></head>
<body>
<h1>Select your preferred portal:</h1>
<form action="showportal.jsp" method="get">
<select name="portchoice">
<option>news</option>
<option>weather</option>
<option>entertainment</option>
</select>
<input type="submit" value="Select"/>
</form>
</body>
</html>

//showportal.jsp

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 <c:choose>
    <c:when test="${param.portchoice == 'news'}">
      <head><title>News Portal</title></head>
      <body>
       <h1>Welcome to the News Portal!</h1>
      </body>
    </c:when>
    <c:when test="${param.portchoice == 'weather'}">
       <head><title>Weather Portal</title></head>
       <body>
        <h1>You Get the Latest Weather!</h1>
       </body>
    </c:when>
    <c:when test="${param.portchoice == 'entertainment'}">
       <head><title>Entertainment Portal</title></head>
       <body>
       <h1>Entertainment News Just for You!</h1>
       </body>
    </c:when>
    <c:otherwise>
       <head><title>System Portal</title></head>
       <body>
       <h1>Application logic problem detected!</h1>
       </body>
    </c:otherwise>
</c:choose>
</html>
