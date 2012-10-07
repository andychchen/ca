<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New WeekDays</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New WeekDays</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{weekDays.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{weekDays.weekDays.name}" title="Name" required="true" requiredMessage="The name field is required." />
                    <h:outputText value="Day:"/>
                    <h:inputText id="day" value="#{weekDays.weekDays.day}" title="Day" required="true" requiredMessage="The day field is required." />
                    <h:outputText value="PromotionCollection:"/>
                    <h:selectManyListbox id="promotionCollection" value="#{weekDays.weekDays.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].promotionCollection}" title="PromotionCollection" size="6" converter="#{promotion.converter}" >
                        <f:selectItems value="#{promotion.promotionItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{weekDays.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{weekDays.listSetup}" value="Show All WeekDays Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
