<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing EmpUsers</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing EmpUsers</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{empUsers.empUsers.id}" title="Id" />
                    <h:outputText value="UserName:"/>
                    <h:inputText id="userName" value="#{empUsers.empUsers.userName}" title="UserName" required="true" requiredMessage="The userName field is required." />
                    <h:outputText value="Role:"/>
                    <h:inputText id="role" value="#{empUsers.empUsers.role}" title="Role" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{empUsers.edit}" value="Save">
                    <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][empUsers.empUsers][empUsers.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{empUsers.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][empUsers.empUsers][empUsers.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{empUsers.listSetup}" value="Show All EmpUsers Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
