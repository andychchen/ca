<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
        String empRole = (String) session.getAttribute("empRole");
//if (!empRole.equals("ADMIN")) {
//    return;
//}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<sql:setDataSource
    var="CA"
    driver="org.apache.derby.jdbc.ClientDriver"
    url="jdbc:derby://localhost:1527/ca"
    user="ca"
    password="ca"
    />

<%--
    This file is an entry point for JavaServer Faces application.
--%>

<f:view>
    <html>
        <head>

            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Product Report</title>

            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
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
            <script>
                function setUpByYear() {
                    //alert(document.financeReportForm.byDate.value.substring(0,4));
                    document.reportForm.byYear.value=document.reportForm.byDate.value.substring(0,4);
                    document.reportForm.byMonth.value="";
                }
                function setUpByMonth() {
                    //alert(document.financeReportForm.byDate.value.substring(0,4));
                    document.reportForm.byYear.value=document.reportForm.byDate.value.substring(0,4);
                    document.reportForm.byMonth.value=document.reportForm.byDate.value.substring(5,7);
                }
                function resetForDate() {
                    //alert(document.financeReportForm.byDate.value.substring(0,4));
                    document.reportForm.byYear.value="";
                    document.reportForm.byMonth.value="";
                }
                function resetShowAllProduct() {
                    document.reportForm.qty.value="";
                    document.reportForm.isbn.value="";
                    document.reportForm.productName.value="";
                }
                function resetExpiring() {
                    document.reportForm.days.value="";
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

                Event.observe(window, 'load', function() { setupCalendars() })
            </script>

        </head>
        <body>

            <h1><h:outputText value="Product Report" /></h1>

            <form name="reportForm" action="ReportProduct.jsp" method="post">

                <br/>

                <input type="submit" value="Show requested Product"/>
                <%
        if (empRole.equals("ADMIN")) {
                %>
                Qty <
                <input name="qty"  value="${param.qty}" size="2"/>
                AND <%        }
                %>ISBN =
                <input name="isbn" value="${param.isbn}" size="25"/>
                AND Product Name like
                <input name="productName" value="${param.productName}" size="25"/>


                <br><br>
                    <%
        if (empRole.equals("ADMIN")) {
                        %>

                <input type="submit" value="Show Expiring Product"/>
                Expiring in
                <input name="days"  value="${param.days}" size="5"/>
                Days
                <br><br>
                <table><tr><td>Select the Date to see Sold/Returned Products<br>Date ( YYYY-MM-DD ):
                            <input id="byDate" name="byDate" type="text" size="18" value="${param.byDate}"/>
                            <br><input type="submit" onclick="resetForDate()" value="One Day"/>
                            <br><br>
                            <input type="submit" onclick="setUpByMonth()" value="Whole Month"/>
                            <input type='hidden' id='byYear' name='byYear' value="${param.byYear}"/>
                            <input type='hidden' id='byMonth' name='byMonth' value="${param.byMonth}"/>

                            <br><br>
                            <input type="submit" onclick="setUpByYear()" value="Whole Year"/>
                        </td>
                        <td><div style="height: 190px; background-color: #efefef; padding: 10px; -webkit-border-radius: 12px; -moz-border-radius: 12px; margin-right: 10px">

                                <div id="embeddedExample" style="">
                                    <div id="embeddedCalendar" style="margin-left: auto; margin-right: auto">
                                    </div>
                                    <br />

                                    <br />
                                </div>
                </div></td></tr></table>
                                <%
       }
                        %>
            </form>
            <br>

            <h:form>
                <h:commandLink action="main" value="Main Menu"/>
            </h:form>





            <c:if test="${!empty param.qty || !empty param.isbn || !empty param.productName}">
                <c:set var="qty" value="${param.qty}"/>
                <c:set var="isbn" value="${param.isbn}"/>
                <c:set var="productName" value="${param.productName}"/>

                <c:if test="${!empty param.qty && !empty param.isbn && !empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and qty < ? and isbn = ? and lower(name) like lower('%${productName}%')
                        <sql:param value="${qty}"/>
                        <sql:param value="${isbn}"/>
                    </sql:query>
                </c:if>
                <c:if test="${!empty param.qty && empty param.isbn && !empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and qty < ? and lower(name) like lower('%${productName}%')
                        <sql:param value="${qty}"/>
                    </sql:query>
                </c:if>
                <c:if test="${!empty param.qty && !empty param.isbn && empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and qty < ? and isbn = ?
                        <sql:param value="${qty}"/>
                        <sql:param value="${isbn}"/>
                    </sql:query>
                </c:if>
                <c:if test="${empty param.qty && !empty param.isbn && !empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' isbn = ? and lower(name) like lower('%${productName}%')
                        <sql:param value="${isbn}"/>
                    </sql:query>
                </c:if>
                <c:if test="${!empty param.qty && empty param.isbn && empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and qty < ?
                        <sql:param value="${qty}"/>
                    </sql:query>
                </c:if>
                <c:if test="${empty param.qty && !empty param.isbn && empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and isbn = ?
                        <sql:param value="${isbn}"/>
                    </sql:query>
                </c:if>
                <c:if test="${empty param.qty && empty param.isbn && !empty param.productName}">
                    <sql:query var="productSql" dataSource="${CA}">
                        SELECT isbn, name, qty, price FROM product where isbn not like 'TEMP-%' and lower(name) like lower('%${productName}%')
                    </sql:query>
                </c:if>
                <h4>Search Product Results</h4>
                <table border="1" cellspacing="5" cellpadding="0">
                    <tr>
                        <%-- Get the column names for the header of the table --%>
                        <c:forEach var="columnName" items="${productSql.columnNames}">
                            <td><c:out value="${columnName}"/></td>
                        </c:forEach>
                        <%
        if (empRole.equals("ADMIN")) {
                        %>
                        <td>Action</td>
                        <%        }
                        %>
                    </tr>

                    <%-- Get the value of each column while iterating over rows --%>
                    <c:forEach var="row" items="${productSql.rows}">
                        <h:form>
                            <tr>
                                <td><c:out value="${row.isbn}"/>&nbsp;</td>
                                <td><c:out value="${row.name}"/>&nbsp;</td>
                                <td><c:out value="${row.qty}"/>&nbsp;</td>
                                <td><c:out value="${row.price}"/>&nbsp;</td>
                                <%
        if (empRole.equals("ADMIN")) {
                                %>
                                <td><h:commandLink value="Edit" action="#{product.editSetup}">
                                    </h:commandLink>
                                    /
                                    <h:commandLink value="Show" action="#{product.detailSetup}">
                                    </h:commandLink>
                                    <%--/
                                    <h:commandLink value="Destroy" action="#{product.destroy}">
                                </h:commandLink>>--%></td>
                                <%        }
                                %>
                                <input name="jsfcrud.currentProduct" type="hidden" value="${row.isbn}">
                            </tr>
                        </h:form>
                    </c:forEach>
                </table>

            </c:if>

            <c:if test="${!empty param.days}">
                <c:set var="days" value="${param.days}"/>
                <sql:query var="expiringSql" dataSource="${CA}">
                    select p.isbn isbn, p.name, peq.qty, peq.expiration_date, {fn TIMESTAMPDIFF(SQL_TSI_DAY,current_date,peq.expiration_date)} days_to_expire from CA.product p, CA.product_expiration_qty peq where p.isbn=peq.isbn and {fn TIMESTAMPDIFF(SQL_TSI_DAY,current_date,peq.expiration_date)} <= ? and peq.qty > 0 order by {fn TIMESTAMPDIFF(SQL_TSI_DAY,current_date,peq.expiration_date)}, peq.qty
                    <sql:param value="${days}"/>
                </sql:query>
                <h4>Expiring in ${days} Days Products</h4>
                <table border="1" cellspacing="5" cellpadding="0">
                    <tr>
                        <%-- Get the column names for the header of the table --%>
                        <c:forEach var="columnName" items="${expiringSql.columnNames}">
                            <td><c:out value="${columnName}"/></td>
                        </c:forEach>
                        <%
        if (empRole.equals("ADMIN")) {
                        %>
                        <td>Action</td>
                        <%        }
                        %>
                    </tr>

                    <%-- Get the value of each column while iterating over rows --%>
                    <c:forEach var="row" items="${expiringSql.rows}">
                        <h:form>
                            <tr>
                                <td><c:out value="${row.isbn}"/>&nbsp;</td>
                                <td><c:out value="${row.name}"/>&nbsp;</td>
                                <td><c:out value="${row.qty}"/>&nbsp;</td>
                                <td><c:out value="${row.EXPIRATION_DATE}"/>&nbsp;</td>
                                <td><c:out value="${row.days_to_expire}"/>&nbsp;</td>
                                <%
        if (empRole.equals("ADMIN")) {
                                %>
                                <td><h:commandLink value="Edit" action="#{product.editSetup}">
                                    </h:commandLink>
                                    /
                                    <h:commandLink value="Show" action="#{product.detailSetup}">
                                    </h:commandLink>
                                    <%--/
                                    <h:commandLink value="Destroy" action="#{product.destroy}">
                                </h:commandLink>>--%></td>
                                <%        }
                                %>
                                <input name="jsfcrud.currentProduct" type="hidden" value="${row.isbn}">
                            </tr>
                        </h:form>
                    </c:forEach>
                </table>

            </c:if>

            <c:if test="${!empty param.byDate}">
                <c:set var="byDate" value="${param.byDate}"/>
                <c:set var="byYear" value="${param.byYear}"/>
                <c:set var="byMonth" value="${param.byMonth}"/>

                <c:if test="${empty param.byYear}">
                    <sql:query var="soldSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_sold, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.ORIGINAL_HEAD_ID is NULL and h.TRAN_DATE=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byDate}"/>
                    </sql:query>
                    <sql:query var="returnedSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_returned, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.IS_FOR_RETURNED='Y' and h.TRAN_DATE=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byDate}"/>
                    </sql:query>
                    <h4>Sold/Returned Product Results in Date ${param.byDate}</h4>
                </c:if>
                <c:if test="${!empty param.byYear && !empty param.byMonth}">
                    <sql:query var="soldSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_sold, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.ORIGINAL_HEAD_ID is NULL and year(h.TRAN_DATE)=? and month(h.TRAN_DATE)=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byYear}"/>
                        <sql:param value="${byMonth}"/>
                    </sql:query>
                    <sql:query var="returnedSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_returned, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.IS_FOR_RETURNED='Y' and year(h.TRAN_DATE)=? and month(h.TRAN_DATE)=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byYear}"/>
                        <sql:param value="${byMonth}"/>
                    </sql:query>
                    <h4>Sold/Returned Product Results in Month ${param.byYear}-${param.byMonth}</h4>
                </c:if>

                <c:if test="${!empty param.byYear && empty param.byMonth}">
                    <sql:query var="soldSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_sold, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.ORIGINAL_HEAD_ID is NULL and year(h.TRAN_DATE)=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byYear}"/>
                    </sql:query>
                    <sql:query var="returnedSql" dataSource="${CA}">
                        select p.ISBN, p.NAME, sum(d.QTY) qty_returned, p.PRICE from CA.TRAN_HEAD h, CA.TRAN_DETAIL d, CA.PRODUCT p where h.TRAN_HEAD_ID=d.TRAN_HEAD_ID and p.ISBN=d.ISBN and h.IS_FOR_RETURNED='Y' and year(h.TRAN_DATE)=? group by p.ISBN, p.NAME, p.PRICE order by sum(d.QTY) desc
                        <sql:param value="${byYear}"/>
                    </sql:query>
                    <h4>Sold/Returned Product Results in Year ${param.byYear}</h4>
                </c:if>
                <table border="1" cellspacing="5" cellpadding="0">
                    <tr><td align="center">Sold Products</td><td align="center">Returned Products</td></tr>
                    <tr valign="top">

                        <td valign="top">
                            <table border="1" cellspacing="5" cellpadding="0">
                                <tr>
                                    <%-- Get the column names for the header of the table --%>
                                    <c:forEach var="columnName" items="${soldSql.columnNames}">
                                        <td><c:out value="${columnName}"/></td>
                                    </c:forEach>
                                    <%
        if (empRole.equals("ADMIN")) {
                                    %>
                                    <td>Action</td>
                                    <%        }
                                    %>
                                </tr>

                                <%-- Get the value of each column while iterating over rows --%>
                                <c:forEach var="row" items="${soldSql.rows}">
                                    <h:form>

                                        <tr>
                                            <td><c:out value="${row.isbn}"/>&nbsp;</td>
                                            <td><c:out value="${row.name}"/>&nbsp;</td>
                                            <td><c:out value="${row.qty_sold}"/>&nbsp;</td>
                                            <td><c:out value="${row.price}"/>&nbsp;</td>
                                            <%
        if (empRole.equals("ADMIN")) {
                                            %>
                                            <td><h:commandLink value="Edit" action="#{product.editSetup}">
                                                </h:commandLink>
                                                /
                                                <h:commandLink value="Show" action="#{product.detailSetup}">
                                                </h:commandLink>
                                                <%--/
                                    <h:commandLink value="Destroy" action="#{product.destroy}">
                                            </h:commandLink>>--%></td>
                                            <%        }
                                            %>
                                            <input name="jsfcrud.currentProduct" type="hidden" value="${row.isbn}">
                                        </tr>
                                    </h:form>

                                </c:forEach>
                            </table>

                        </td>


                        <td valign="top">
                            <table border="1" cellspacing="5" cellpadding="0">
                                <tr>
                                    <%-- Get the column names for the header of the table --%>
                                    <c:forEach var="columnName" items="${returnedSql.columnNames}">
                                        <td><c:out value="${columnName}"/></td>
                                    </c:forEach>
                                    <%
        if (empRole.equals("ADMIN")) {
                                    %>
                                    <td>Action</td>
                                    <%        }
                                    %>
                                </tr>

                                <%-- Get the value of each column while iterating over rows --%>
                                <c:forEach var="row" items="${returnedSql.rows}">
                                    <h:form>

                                        <tr>
                                            <td><c:out value="${row.isbn}"/>&nbsp;</td>
                                            <td><c:out value="${row.name}"/>&nbsp;</td>
                                            <td><c:out value="${row.qty_returned}"/>&nbsp;</td>
                                            <td><c:out value="${row.price}"/>&nbsp;</td>
                                            <%
        if (empRole.equals("ADMIN")) {
                                            %>
                                            <td><h:commandLink value="Edit" action="#{product.editSetup}">
                                                </h:commandLink>
                                                /
                                                <h:commandLink value="Show" action="#{product.detailSetup}">
                                                </h:commandLink>
                                                <%--/
                                    <h:commandLink value="Destroy" action="#{product.destroy}">
                                            </h:commandLink>>--%></td>
                                            <%        }
                                            %>
                                            <input name="jsfcrud.currentProduct" type="hidden" value="${row.isbn}">
                                        </tr>
                                    </h:form>

                                </c:forEach>
                            </table>

                        </td>

                    </tr>
                </table>
            </c:if>
        </body>
    </html>
</f:view>
