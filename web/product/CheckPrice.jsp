<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : CheckPrice
    Created on : Feb 1, 2010, 3:45:18 PM
    Author     : achen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<jsp:useBean id="JspProduct" class="jpa.entities.Product" scope="session" >
</jsp:useBean>
<jsp:useBean id="message" class="java.lang.String" scope="session" >
</jsp:useBean>
<html>
    <head>
        <title>Check Price</title>
        <link rel="stylesheet" href="/ca/faces/calendarview.css">
        <style>
            body {
                font-family: Trebuchet MS;
            }
            div.calendar {
                max-width: 240px;
                margin-left: auto;
                margin-right: auto;
            }
            div.calendar table {
                width: 100%;
            }
            div.dateField {
                width: 140px;
                padding: 6px;
                -webkit-border-radius: 6px;
                -moz-border-radius: 6px;
                color: #555;
                background-color: white;
                margin-left: auto;
                margin-right: auto;
                text-align: center;
            }
            div#popupDateField:hover {
                background-color: #cde;
                cursor: pointer;
            }
        </style>
        <script src="/ca/faces/prototype.js"></script>
        <script src="/ca/faces/calendarview.js"></script>
        <script>
            function setupCalendars() {
                // Embedded Calendar
                Calendar.setup(
                {
                    dateField: 'experationDate',
                    parentElement: 'embeddedCalendar'
                }
            )

                // Popup Calendar
                Calendar.setup(
                {
                    dateField: 'popupDateField',
                    triggerElement: 'popupDateField'
                }
            )
            }

            Event.observe(window, 'load', function() { setupCalendars() })
        </script>
        <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
    </head>
    <h1>Check Price</h1>
    <body OnLoad="document.myForm.isbn.focus();">
        <table border='0' width='100%'><td>
                <form name="myForm" action="/ca/CheckPriceServlet" method="post">
                    <br></br>
                    Scan / Enter ISBN: <input type='text' name='isbn' size='18'/>
                    <input name='toDo' type='submit' value='Check'/>
                </form>
                <br><br><br><a href="/ca/faces/index.jsp">Main Menu</a>
            </td>
            <td width='65%' align="left">


                <c:if test="${!empty JspProduct.isbn}">
                    <h3><font color="BLUE">ISBN: ${JspProduct.isbn}</font></h3><br>
                    <h3><font color="BLUE">Item: ${JspProduct.name}</font></h3><br>
                    <h3><font color="RED">Price: $${JspProduct.price}</font></h3><br>
                    <h3><font color="BLUE">Qty: ${JspProduct.qty}</font></h3><br>
                    <h3><font color="BLUE">Promotion: ${JspProduct.promotion.name}</font></h3><br>
                    <h3><font color="BLUE">Tax: ${JspProduct.tax.name} ${JspProduct.tax.rate*100} %</font></h3><br>
                </c:if>
                <c:if test="${!empty message}">
                    <h3><font color="RED">${message}</font></h3><br>
                </c:if>
        </td></table>
    </body>
</html>

