<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Brand</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Brand</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{brand.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{brand.brand.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:inputText id="description" value="#{brand.brand.description}" title="Description" />

                    <h:outputText value="Promotion:"/>
                    <h:selectOneMenu id="promotion" value="#{brand.brand.promotion}" title="Promotion" >
                        <f:selectItems value="#{promotion.promotionItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{brand.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{brand.listSetup}" value="Show All Brand Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
