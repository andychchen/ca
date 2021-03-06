<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>PaymentType Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>PaymentType Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{paymentType.paymentType.id}" title="Id" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{paymentType.paymentType.name}" title="Name" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{paymentType.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][paymentType.paymentType][paymentType.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{paymentType.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][paymentType.paymentType][paymentType.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{paymentType.createSetup}" value="New PaymentType" />
                <br />
                <h:commandLink action="#{paymentType.listSetup}" value="Show All PaymentType Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
