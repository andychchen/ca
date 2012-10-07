<%-- 
    Document   : Report
    Created on : Feb 22, 2010, 10:56:29 PM
    Author     : achen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%
        String empRole = (String) session.getAttribute("empRole");
        if (empRole == null || empRole.trim().length() == 0) {
            empRole = "USER";
        }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<f:view>
    <h:form>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Report</title>
                <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
            </head>
            <body>
                <%--h:commandLink action="reportProduct" value="Product Report"/--%>
                <%-- if (empRole.equals("ADMIN")) {
                --%>
                <a href="/ca/faces/ReportProduct.jsp">Product Management</a>
                <%--}
                --%>

                <br><br>
                <%--h:commandLink action="reportFinance" value="Finance Report"/--%>
                <a href="/ca/faces/ReportFinance.jsp">Finance</a>

                <br><br><br><br>

                <h:commandLink action="main" value="Main Menu"/>


            </body>
        </html>
    </h:form>
</f:view>