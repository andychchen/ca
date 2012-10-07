<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ProductExpirationQty Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ProductExpirationQty Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ExpirationDate:"/>
                    <h:outputText value="#{productExpirationQty.productExpirationQty.productExpirationQtyPK.expirationDate}" title="ExpirationDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{productExpirationQty.productExpirationQty.qty}" title="Qty" />
                    <h:outputText value="Product:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{productExpirationQty.productExpirationQty.product}"/>
                        <h:panelGroup rendered="#{productExpirationQty.productExpirationQty.product != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{product.detailSetup}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="productExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{product.editSetup}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="productExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{product.destroy}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="productExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{productExpirationQty.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{productExpirationQty.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][productExpirationQty.productExpirationQty][productExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{productExpirationQty.createSetup}" value="New ProductExpirationQty" />
                <br />
                <h:commandLink action="#{productExpirationQty.listSetup}" value="Show All ProductExpirationQty Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
