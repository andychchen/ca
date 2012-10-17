<%--
    Document   : test
    Created on : Feb 22, 2010, 8:32:50 PM
    Author     : achen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>

<%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %>

<%@ page import='java.text.SimpleDateFormat' %>
<%@ page import='java.util.Date' %>



<%

        String empName = (String) session.getAttribute("empName");
        String empRole = (String) session.getAttribute("empRole");
        String empId = (String) session.getAttribute("empId");
        if (empRole == null || empRole.trim().length() == 0) {
            empRole = "USER";
        }
        boolean isAdmin = false;
        if (empRole.equals("ADMIN")) {
            isAdmin = true;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tdString = sdf.format(new Date());
        String currentDatetime = (new Date()).toString();

%>

<sql:setDataSource
    var="CA"
    driver="org.apache.derby.jdbc.ClientDriver"
    url="jdbc:derby://localhost:1527/ca"
    user="ca"
    password="ca"
    />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finance Report</title>
        <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" />

        <link rel="stylesheet" type="text/css" href="/ca/faces/calendarview.css">

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
        <script src="/ca/faces/jsfcrud.js"></script>
        <script>
            function setUpByYear() {
                //alert(document.financeReportForm.byDate.value.substring(0,4));
                document.financeReportForm.byYear.value=document.financeReportForm.byDate.value.substring(0,4);
                document.financeReportForm.byMonth.value="";
            }
            function setUpByMonth() {
                //alert(document.financeReportForm.byDate.value.substring(0,4));
                document.financeReportForm.byYear.value=document.financeReportForm.byDate.value.substring(0,4);
                document.financeReportForm.byMonth.value=document.financeReportForm.byDate.value.substring(5,7);
            }
            function resetForDate() {
                //alert(document.financeReportForm.byDate.value.substring(0,4));
                document.financeReportForm.byYear.value="";
                document.financeReportForm.byMonth.value="";
            }
            function setupCalendars() {
                // Embedded Calendar
                Calendar.setup(
                {
                    dateField: 'byDate',
                    parentElement: 'embeddedCalendar',
                    dateFormat: '%Y-%m-%d'
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
            <% if (empRole.equals("ADMIN")) {
                            %>

                                Event.observe(window, 'load', function() { setupCalendars() })
            <%}%>

                function sendToPrint()
                {
                    document.financeReportForm.printIt.value = "true";
                    document.financeReportForm.target = "PrintWindow";
                    window.open("","PrintWindow","width=500,height=300,toolbar=0");
                    document.financeReportForm.submit();
                    //document.financeReportForm.target = '_self';

                    var a = window.setTimeout("document.financeReportForm.target = '_self';document.financeReportForm.printIt.value = 'false';",1000);

                }

        </script>
    </head>
    <f:view>
        <c:if test="${param.printIt != 'true'}">
            <body>
        </c:if>
        <c:if test="${param.printIt == 'true'}">
            <body onload="window.print();window.setTimeout('window.close();',500);">
            </c:if>



            <table><tr><td>



                        <div id="hiddenForPrint" <c:if test="${param.printIt == 'true'}"> style="display:none;" </c:if> >

                            <h1><h:outputText value="Finance Report" /></h1>

                            <form target="_self" id="finaneReportForm" name="financeReportForm" action="ReportFinance.jsp" method="post">
                                <table>
                                    <tr><td>

                                            <sql:query var="userSql" dataSource="${CA}">
                                                select id, user_name, role from CA.EMP_USERS
                                            </sql:query>
                                            <c:if test="${empRole != 'ADMIN'}">
                                                <c:set var="selectedId" value="${empId}"></c:set>
                                            </c:if>
                                            <c:if test="${empRole == 'ADMIN'}">
                                                <c:set var="selectedId" value="${param.userSelect}"></c:set>
                                            </c:if>
                                            ===<c:out value="${empId}" />===<c:out value="${selectedId}" />+++++++++++++
                                            <select id="userSelect" name="userSelect" <% if (!empRole.equals("ADMIN")) {%> disabled <% }%> >

                                                <option value="all">All</option>

                                                <c:forEach var="row" items="${userSql.rows}">
                                                    <option value='<c:out value="${row.id}"/>' <c:if test="${ row.id == selectedId }" > selected <c:set var="selectedName" value="${row.user_name}"></c:set></c:if>><c:out value="${row.user_name}"/> - <c:out value="${row.role}"/></option>
                                                </c:forEach>

                                            </select>'s transaction report.<br><br>
                                            <c:if test="${ empty selectedName}">
                                                <c:set var="selectedName" value="All"></c:set>
                                            </c:if>
                                            ===<c:out value="${selectedName}" />===
                                    </td></tr>

                                    <tr><td>Select the Date to see Payment<br>Date ( YYYY-MM-DD ):
                                            <input
                                                <% if (!empRole.equals("ADMIN")) {
                                                %>
                                                disabled
                                                <%}%>
                                                id="byDate" name="byDate" type="text" size="18" value='<%=tdString%>'/>
                                            <% if (!empRole.equals("ADMIN")) {
                                            %>
                                            <input type="hidden" name="byDate" value='<%=tdString%>'/>
                                            <%}%>
                                            <br><input type="submit" onclick="resetForDate()" value="One Day"/>
                                            <br><br>
                                            <% if (empRole.equals("ADMIN")) {
                                            %>
                                            <input type="submit" onclick="setUpByMonth()" value="Whole Month"/>
                                            <input type='hidden' id='byYear' name='byYear' value="${param.byYear}"/>
                                            <input type='hidden' id='byMonth' name='byMonth' value="${param.byMonth}"/>

                                            <br><br>
                                            <input type="submit" onclick="setUpByYear()" value="Whole Year"/>
                                            <%        }
                                            %>
                                        </td>

                                        <td>

                                            <% if (empRole.equals("ADMIN")) {
                                            %>

                                            <div style="height: 190px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px">

                                                <div id="embeddedExample" style="">
                                                    <div id="embeddedCalendar" style="margin-left: auto; margin-right: auto">
                                                    </div>
                                                    <br />

                                                    <br />
                                                </div>
                                            </div>
                                            <%}%>
                                </td></tr></table>

                                <br>
                                <input id="printIt" name="printIt" value="false" type="hidden">
                                <input style="height: 40px; width: 240px; color: #00CC33; background: #33FF66; font-size: 110%; font-weight: 900" type="button" value="Print" onclick="sendToPrint();return false;">
                            </form>
                            <br>
                            <h:form>
                                <h:commandLink action="main" value="Main Menu"/>
                            </h:form>
                            <br>

                        </div><!-- end of hiddenForPrint div -->



                    </td>
                    <td>


                        <c:if test="${!empty param.byDate}">
                            <c:set var="byDate" value="${param.byDate}"/>
                            <c:set var="byYear" value="${param.byYear}"/>
                            <c:set var="byMonth" value="${param.byMonth}"/>

                            <c:if test="${empty param.byYear}">

                                <c:choose>
                                    <c:when test="${selectedId == 'all'}">
                                        <sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total, 'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD where (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and tran_date = ? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD where (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and tran_date = ? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>

                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>

                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                    </c:when>
                                    <c:otherwise>
                                        <sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total, 'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD where USER_ID = ? and (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and tran_date = ? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD where USER_ID = ? and (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and tran_date = ? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and pay_date=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byDate}"/>
                                        </sql:query>

                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and th.TRAN_DATE=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and th.TRAN_DATE=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                        </sql:query>

                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and tran_date = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byDate}"/>
                                        </sql:query>
                                    </c:otherwise>
                                </c:choose>

                                <h3>Payment in Date ${param.byDate}</h3>
                            </c:if>

                            <c:if test="${!empty param.byYear && !empty param.byMonth}">
                                <c:choose>
                                    <c:when test="${selectedId == 'all'}">
                                        <sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>

                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>

                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where  th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>

                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total,  'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where  (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD where (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? and month(tran_date)=? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD where (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? and month(tran_date)=? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date)=? and month(pay_date)=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>

                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>


                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                    </c:when>
                                    <c:otherwise>
                                        <sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total,  'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and  (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and month(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD th where  th.USER_ID = ? and (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? and month(tran_date)=? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD th where th.USER_ID = ? and (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? and month(tran_date)=? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date)=? and month(pay_date)=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                           <sql:param value="${selectedId}"/> <sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? and month(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ? and month(tran_date) = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${byMonth}"/>
                                        </sql:query>
                                    </c:otherwise>
                                </c:choose>
                                <h3>Payment in Month ${param.byYear}-${param.byMonth}</h3>
                            </c:if>

                            <c:if test="${!empty param.byYear && empty param.byMonth}">
                                <c:choose>
                                    <c:when test="${selectedId == 'all'}">
                                        <sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where  th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total,  'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where  (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD where (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD where (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date)=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${byYear}"/>
                                            <sql:param value="${byYear}"/>
                                        </sql:query>
                                    </c:when>
                                    <c:otherwise><sql:query var="paymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="returnPaymentSql" dataSource="${CA}">
                                            select sum( amount ) total,  pt.NAME Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID group by pt.NAME order by pt.NAME
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="bottleSql" dataSource="${CA}">
                                            select sum(bottle_refund) total, 'Bottle Refund' Payment_Type from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="grossSql" dataSource="${CA}">
                                            select sum( amount ) total,  'Gross Income' Payment_type from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date) = ? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate'
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="netSql" dataSource="${CA}">
                                            select temp.total-(select sum( Line_Tax_Amt ) from CA.TRAN_HEAD th where th.USER_ID = ? and (ORIGINAL_HEAD_ID is null) and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? ) + (select CASE WHEN sum( Line_Tax_Amt ) is null THEN 0 ELSE sum( Line_Tax_Amt ) END from CA.TRAN_HEAD th where th.USER_ID = ? and (IS_FOR_RETURNED='Y') and (IS_TRAINING_MODE is null or IS_TRAINING_MODE!='Y') and year(tran_date)=? ) total, 'Net Income' Payment_type from ( select sum( amount ) total from CA.TRAN_PAYMENT tp, CA.PAYMENT_TYPE pt, CA.TRAN_HEAD th where th.USER_ID = ? and (th.ORIGINAL_HEAD_ID is null or th.IS_FOR_RETURNED='Y') and (th.IS_TRAINING_MODE is null or th.IS_TRAINING_MODE!='Y') and th.TRAN_HEAD_ID=tp.TRAN_HEAD_ID and year(pay_date)=? and tp.PAYMENT_TYPE=pt.ID and pt.NAME!='Gift Certificate' ) as temp
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="gstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'GST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="pstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 8/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='GST & PST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'PST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="hstTaxSql" dataSource="${CA}">
                                            select cast( ( ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) + ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.ORIGINAL_HEAD_ID is null and year(th.TRAN_DATE)=?) - ( select CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='HST' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=? ) - ( select (CASE WHEN sum(td.SUB_TAX) is null THEN 0 ELSE sum(td.SUB_TAX) END)* 5/13 from CA.TRAN_DETAIL td, CA.TRAN_HEAD th, CA.tax tax where th.USER_ID = ? and (th.is_training_mode!='Y' or th.is_training_mode is null ) and th.TRAN_HEAD_ID=td.TRAN_HEAD_ID and tax.TAX_ID=td.TAX and tax.NAME='XXX' and th.IS_FOR_RETURNED='Y' and year(th.TRAN_DATE)=?) )+0.005 as decimal (15,2)) total , 'HST Tax' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                        <sql:query var="discountSql" dataSource="${CA}">
                                            select ((select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.ORIGINAL_HEAD_ID is null and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?) - (select CASE WHEN sum(th.LINE_DISCOUNT_AMT) is null THEN 0 ELSE sum(th.LINE_DISCOUNT_AMT) END from CA.TRAN_HEAD th where th.USER_ID = ? and th.IS_FOR_RETURNED='Y' and (th.is_training_mode!='Y' or th.is_training_mode is null ) and year(tran_date) = ?)) total, 'Discount' PAYMENT_TYPE from SYSIBM.SYSDUMMY1
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                            <sql:param value="${selectedId}"/><sql:param value="${byYear}"/>
                                        </sql:query>
                                    </c:otherwise>
                                </c:choose>
                                <h3>Payment in Year ${param.byYear}</h3>
                            </c:if>
                            - printed by <%=empName%> <br>
                            @ <%=currentDatetime%> <br>
                            for <c:out value="${selectedName}"/>'s Transaction:
                            <table border="1" cellspacing="0" cellpadding="1">
                                <tr>
                                    <%-- Get the column names for the header of the table --%>
                                    <c:forEach var="columnName" items="${paymentSql.columnNames}">
                                        <td><c:out value="${columnName}"/></td>
                                    </c:forEach>
                                </tr>

                                <%-- Get the value of each column while iterating over rows --%>
                                <c:forEach var="row" items="${paymentSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>

                                <c:forEach var="row" items="${returnPaymentSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/> from Return &nbsp;</td>
                                    </tr>
                                </c:forEach>

                                <c:forEach var="row" items="${gstTaxSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="row" items="${pstTaxSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>

                                <c:forEach var="row" items="${hstTaxSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>

                                <c:forEach var="row" items="${bottleSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="row" items="${discountSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="row" items="${grossSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="row" items="${netSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>

                                <% if (empRole.equals("ADMIN")) {
                                %>

                                <sql:query var="invPriceSql" dataSource="${CA}">
                                    select cast ( (sum(qty * price)+0.005) as decimal ( 15,2)) total, 'Inventory Price' payment_type from product
                                </sql:query>
                                <sql:query var="invCostSql" dataSource="${CA}">
                                    select cast ( (sum(qty * cost)+0.005) as decimal ( 15,2)) total, 'Inventory Cost' payment_type from product
                                </sql:query>
                                <c:forEach var="row" items="${invPriceSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="row" items="${invCostSql.rows}">
                                    <tr>
                                        <td><c:out value="${row.total}"/>&nbsp;</td>
                                        <td><c:out value="${row.Payment_type}"/>&nbsp;</td>
                                    </tr>
                                </c:forEach>
                                <% }%>
                            </table>
                        </c:if>
            </td></tr></table>



        </body>
    </f:view>
</html>

