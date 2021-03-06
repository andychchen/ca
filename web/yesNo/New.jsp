<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New YesNo</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New YesNo</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{yesNo.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{yesNo.yesNo.name}" title="Name" required="true" requiredMessage="The name field is required." />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{yesNo.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{yesNo.listSetup}" value="Show All YesNo Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
