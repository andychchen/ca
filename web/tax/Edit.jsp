<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Tax</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Tax</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TaxId:"/>
                    <h:outputText value="#{tax.tax.taxId}" title="TaxId" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{tax.tax.name}" title="Name" />
                    <h:outputText value="Rate:"/>
                    <h:inputText id="rate" value="#{tax.tax.rate}" title="Rate" />
 
                </h:panelGrid>
                <br />
                <h:commandLink action="#{tax.edit}" value="Save">
                    <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tax.tax][tax.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tax.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tax.tax][tax.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{tax.listSetup}" value="Show All Tax Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
