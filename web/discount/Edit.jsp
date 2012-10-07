<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Discount</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Discount</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="DiscountId:"/>
                    <h:outputText value="#{discount.discount.discountId}" title="DiscountId" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{discount.discount.name}" title="Name" />
                    <h:outputText value="BuyHow:"/>
                    <h:inputText id="buyHow" value="#{discount.discount.buyHow}" title="BuyHow" />
                    <h:outputText value="GetHow:"/>
                    <h:inputText id="getHow" value="#{discount.discount.getHow}" title="GetHow" />
                    <h:outputText value="PromotionCollection:"/>
                    <h:selectManyListbox id="promotionCollection" value="#{discount.discount.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].promotionCollection}" title="PromotionCollection" size="6" converter="#{promotion.converter}" >
                        <f:selectItems value="#{promotion.promotionItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>

                    <h:outputText value="Type:"/>
                    <h:selectOneMenu id="type" value="#{discount.discount.type}" title="Type" required="true" requiredMessage="The type field is required." >
                        <f:selectItems value="#{discountType.discountTypeItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{discount.edit}" value="Save">
                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{discount.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{discount.listSetup}" value="Show All Discount Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
