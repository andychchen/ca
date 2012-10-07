<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Product</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Product</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{product.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Isbn:"/>
                    <h:inputText id="isbn" value="#{product.product.isbn}" title="Isbn" required="true" requiredMessage="The isbn field is required." />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{product.product.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:inputText id="description" value="#{product.product.description}" title="Description" />
                    <h:outputText value="Qty:"/>
                    <h:inputText id="qty" value="#{product.product.qty}" title="Qty" />
                    <h:outputText value="Price:"/>
                    <h:inputText id="price" value="#{product.product.price}" title="Price" />
                    <h:outputText value="Cost:"/>
                    <h:inputText id="cost" value="#{product.product.cost}" title="Cost" />
                    <h:outputText value="LastUpdateDate (MM/dd/yyyy):"/>
                    <h:inputText id="lastUpdateDate" value="#{product.product.lastUpdateDate}" title="LastUpdateDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>

                    <h:outputText value="Brand:"/>
                    <h:selectOneMenu id="brand" value="#{product.product.brand}" title="Brand" >
                        <f:selectItems value="#{brand.brandItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Promotion:"/>
                    <h:selectOneMenu id="promotion" value="#{product.product.promotion}" title="Promotion" >
                        <f:selectItems value="#{promotion.promotionItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Tax:"/>
                    <h:selectOneMenu id="tax" value="#{product.product.tax}" title="Tax" >
                        <f:selectItems value="#{tax.taxItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IsOrganic:"/>
                    <h:selectOneMenu id="isOrganic" value="#{product.product.isOrganic}" title="IsOrganic" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="HasExpirationDate:"/>
                    <h:selectOneMenu id="hasExpirationDate" value="#{product.product.hasExpirationDate}" title="HasExpirationDate" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="BrandDiscountExcluded:"/>
                    <h:selectOneMenu id="brandDiscountExcluded" value="#{product.product.brandDiscountExcluded}" title="BrandDiscountExcluded" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="ProductExpirationQtyCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][product.product.productExpirationQtyCollection == null ? jsfcrud_null : product.product.productExpirationQtyCollection].jsfcrud_invoke}" title="ProductExpirationQtyCollection" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{product.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{product.listSetup}" value="Show All Product Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
