<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing ProductExpirationQty</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing ProductExpirationQty</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ExpirationDate:"/>
                    <h:outputText value="#{productExpirationQty.productExpirationQty.productExpirationQtyPK.expirationDate}" title="ExpirationDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Qty:"/>
                    <h:inputText id="qty" value="#{productExpirationQty.productExpirationQty.qty}" title="Qty" required="true" requiredMessage="The qty field is required." />
                    <h:outputText value="Product:"/>
                    <h:outputText value=" #{productExpirationQty.productExpirationQty.product}" title="Product" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{productExpirationQty.edit}" value="Save">
                    <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{productExpirationQty.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{productExpirationQty.listSetup}" value="Show All ProductExpirationQty Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
