<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New ReturnTranDetailLineExpirationQty</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New ReturnTranDetailLineExpirationQty</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{returnTranDetailLineExpirationQty.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="ExpirationDate (MM/dd/yyyy):"/>
                    <h:inputText id="expirationDate" value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQtyPK.expirationDate}" title="ExpirationDate" required="true" requiredMessage="The expirationDate field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="Qty:"/>
                    <h:inputText id="qty" value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.qty}" title="Qty" />
                    <h:outputText value="ReturnTranDetail:"/>
                    <h:selectOneMenu id="returnTranDetail" value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail}" title="ReturnTranDetail" required="true" requiredMessage="The returnTranDetail field is required." >
                        <f:selectItems value="#{returnTranDetail.returnTranDetailItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.listSetup}" value="Show All ReturnTranDetailLineExpirationQty Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
