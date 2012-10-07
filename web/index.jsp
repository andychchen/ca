<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%
        String empRole = (String) session.getAttribute("empRole");
        if (empRole == null || empRole.trim().length() == 0) {
            empRole = "USER";
        }
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Cash Registor Main Page</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %>
            <script type="text/javascript" src="/ca/faces/jsfcrud.js">
                function changeBackground(id, colorCode)
                {
                    alert('hello');
                    document.getElementById( id ).style.background=colorCode;
                }
            </script>
        </head>
        <body>
            <h:form id="buttonForm">
                <h1><h:outputText value="Enchie Organic & Japanese Food Market" /></h1>
                <table cellpadding="10">
                    <tr>

                        <td><h:commandButton id="checkOutButton" style="height: 140px; width: 240px; color: #00CC33; background: #33FF66; font-size: 200%; font-weight: 900" value="Check Out" action="checkOut" onmouseover="document.getElementById('buttonForm:checkOutButton').style.background='#CCFF66'" onmouseout="document.getElementById('buttonForm:checkOutButton').style.background='#33FF66'"/></td>
                        <td><h:commandButton id='checkPriceButton' style="height: 140px; width: 240px; color: #66CCFF; background: #00FFFF; font-size: 200%; font-weight: 900" value="Check Price" action="checkPrice" onmouseover="document.getElementById('buttonForm:checkPriceButton').style.background='#99FFFF'" onmouseout="document.getElementById('buttonForm:checkPriceButton').style.background='#00FFFF'"/></td>
                        <td>
                            <% if (empRole.equals("ADMIN")) {
                            %>
                            <h:commandButton id="checkInButton" style="height: 140px; width: 240px; color: #777777; background: #BBBBBB; font-size: 200%; font-weight: 900" value="Check In" action="checkIn" onmouseover="document.getElementById('buttonForm:checkInButton').style.background='#DDDDDD'" onmouseout="document.getElementById('buttonForm:checkInButton').style.background='#BBBBBB'"/>
                            <%        }
                            %>
                        </td>
                    </tr>
                    <tr><td><h:commandButton id="returnButton" style="height: 140px; width: 240px; color: #CC00FF; background: #FF99FF; font-size: 200%; font-weight: 900" value="Return" action="return" onmouseover="document.getElementById('buttonForm:returnButton').style.background='#FFCCFF'" onmouseout="document.getElementById('buttonForm:returnButton').style.background='#FF99FF'"/></td>

                        <td><h:commandButton id="reportButton" style="height: 140px; width: 240px; color: #FF6600; background: #FFCC66; font-weight: 900" value="Finance/Product Management" action="report" onmouseover="document.getElementById('buttonForm:reportButton').style.background='#FFFFCC'" onmouseout="document.getElementById('buttonForm:reportButton').style.background='#FFCC66'"/></td>
                        <td>
                            <% if (empRole.equals("ADMIN")) {
                            %>
                            <h:commandButton id="adminButton" style="height: 140px; width: 240px; color: #777777; background: #BBBBBB; font-size: 200%; font-weight: 900" value="Administration" action="welcome" onmouseover="document.getElementById('buttonForm:adminButton').style.background='#DDDDDD'" onmouseout="document.getElementById('buttonForm:adminButton').style.background='#BBBBBB'"/>
                            <%        }
                            %>
                        </td>
                    </tr>

                </table>

            </h:form>


        </body>
    </html>
</f:view>