<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New DiscountType</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New DiscountType</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{discountType.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{discountType.discountType.name}" title="Name" />
                    <h:outputText value="Type:"/>
                    <h:inputText id="type" value="#{discountType.discountType.type}" title="Type" required="true" requiredMessage="The type field is required." />
                    <h:outputText value="DiscountCollection:"/>
                    <h:selectManyListbox id="discountCollection" value="#{discountType.discountType.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].discountCollection}" title="DiscountCollection" size="6" converter="#{discount.converter}" >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{discountType.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{discountType.listSetup}" value="Show All DiscountType Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
