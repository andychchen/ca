<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing TranPayment</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing TranPayment</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{tranPayment.tranPayment.id}" title="Id" />
                    <h:outputText value="Amount:"/>
                    <h:inputText id="amount" value="#{tranPayment.tranPayment.amount}" title="Amount" />
                    <h:outputText value="PayDate (MM/dd/yyyy):"/>
                    <h:inputText id="payDate" value="#{tranPayment.tranPayment.payDate}" title="PayDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="PayTimestamp (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="payTimestamp" value="#{tranPayment.tranPayment.payTimestamp}" title="PayTimestamp" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="PaymentType:"/>
                    <h:selectOneMenu id="paymentType" value="#{tranPayment.tranPayment.paymentType}" title="PaymentType" required="true" requiredMessage="The paymentType field is required." >
                        <f:selectItems value="#{paymentType.paymentTypeItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranPayment.edit}" value="Save">
                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranPayment.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranPayment.listSetup}" value="Show All TranPayment Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
