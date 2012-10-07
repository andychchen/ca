<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New ReturnTranPayment</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New ReturnTranPayment</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{returnTranPayment.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Amount:"/>
                    <h:inputText id="amount" value="#{returnTranPayment.returnTranPayment.amount}" title="Amount" />
                    <h:outputText value="PayDate (MM/dd/yyyy):"/>
                    <h:inputText id="payDate" value="#{returnTranPayment.returnTranPayment.payDate}" title="PayDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="PaymentType:"/>
                    <h:selectOneMenu id="paymentType" value="#{returnTranPayment.returnTranPayment.paymentType}" title="PaymentType" required="true" requiredMessage="The paymentType field is required." >
                        <f:selectItems value="#{paymentType.paymentTypeItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="TranHeadId:"/>
                    <h:selectOneMenu id="tranHeadId" value="#{returnTranPayment.returnTranPayment.tranHeadId}" title="TranHeadId" required="true" requiredMessage="The tranHeadId field is required." >
                        <f:selectItems value="#{returnTranHead.returnTranHeadItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranPayment.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{returnTranPayment.listSetup}" value="Show All ReturnTranPayment Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
