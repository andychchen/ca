<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<%
        String empRole = (String) session.getAttribute("empRole");
        if (!empRole.equals("ADMIN")) {
            return;
        }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Administration</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:form>
                <h1><h:outputText value="Administration" /></h1>
                <br/>
                <h:commandLink action="#{tranHead.listSetup}" value="Show All TranHead Items"/>

                <br/>
                <!--
                <h:commandLink action="#{returnTranPayment.listSetup}" value="Show All ReturnTranPayment Items"/>

                <br/>
                <h:commandLink action="#{yesNo.listSetup}" value="Show All YesNo Items"/>

                <br/>
                <h:commandLink action="#{weekDays.listSetup}" value="Show All WeekDays Items"/>

                <br/>
                <h:commandLink action="#{tranPayment.listSetup}" value="Show All TranPayment Items"/>

                <br/>
                <h:commandLink action="#{tranHead.listSetup}" value="Show All TranHead Items"/>

                <br/>
                -->
                <h:commandLink action="#{tranDetail.listSetup}" value="Show All TranDetail Items"/>

                <br/>
                <h:commandLink action="#{tranDetailLineExpirationQty.listSetup}" value="Show All TranDetailLineExpirationQty Items"/>

                <br/>
                <br/>

                <h:commandLink action="#{brand.listSetup}" value="Show All Brand Items"/>
                <br/>
                <br/>
                <h:commandLink action="#{product.listSetup}" value="Show All Product Items"/>

                <br/>
                <h:commandLink action="#{productExpirationQty.listSetup}" value="Show All ProductExpirationQty Items"/>

                <br/>
                <br/>
                <h:commandLink action="#{discount.listSetup}" value="Show All Discount Items"/>

                <br/>
                
                <h:commandLink action="#{promotion.listSetup}" value="Show All Promotion Items"/>
                
                <br/>
                <br/>



                <h:commandLink action="#{tax.listSetup}" value="Show All Tax Items"/>

                <br/>
                <!--
                <h:commandLink action="#{returnTranHead.listSetup}" value="Show All ReturnTranHead Items"/>

                <br/>
                <h:commandLink action="#{returnTranDetailLineExpirationQty.listSetup}" value="Show All ReturnTranDetailLineExpirationQty Items"/>

                <br/>
                <h:commandLink action="#{returnTranDetail.listSetup}" value="Show All ReturnTranDetail Items"/>

                <br/>
                -->



                <h:commandLink action="#{paymentType.listSetup}" value="Show All PaymentType Items"/>

                <br/>
                <h:commandLink action="#{discountType.listSetup}" value="Show All DiscountType Items"/>

                <br/>


                <br/>

                <h:commandLink action="#{empUsers.listSetup}" value="Show All Users"/>
                <br/>

                <br/><br/>
                <h:commandLink action="main" value="Main Menu"/>
            </h:form>

        </body>
    </html>
</f:view>
