<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Tax Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Tax Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TaxId:"/>
                    <h:outputText value="#{tax.tax.taxId}" title="TaxId" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{tax.tax.name}" title="Name" />
                    <h:outputText value="Rate:"/>
                    <h:outputText value="#{tax.tax.rate}" title="Rate" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{tax.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tax.tax][tax.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tax.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tax.tax][tax.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tax.createSetup}" value="New Tax" />
                <br />
                <h:commandLink action="#{tax.listSetup}" value="Show All Tax Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
