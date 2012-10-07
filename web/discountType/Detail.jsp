<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>DiscountType Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>DiscountType Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{discountType.discountType.name}" title="Name" />
                    <h:outputText value="Type:"/>
                    <h:outputText value="#{discountType.discountType.type}" title="Type" />
                    <h:outputText value="DiscountCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discountType.discountType.discountCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discountType.discountType.discountCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discountType.discountType.discountCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="DiscountId"/>
                                </f:facet>
                                <h:outputText value=" #{item.discountId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value=" #{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="BuyHow"/>
                                </f:facet>
                                <h:outputText value=" #{item.buyHow}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="GetHow"/>
                                </f:facet>
                                <h:outputText value=" #{item.getHow}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Type"/>
                                </f:facet>
                                <h:outputText value=" #{item.type}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{discount.detailSetup}">
                                    <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discountType.discountType][discountType.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discountType" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountTypeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{discount.editSetup}">
                                    <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discountType.discountType][discountType.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discountType" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountTypeController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{discount.destroy}">
                                    <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discountType.discountType][discountType.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discountType" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountTypeController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{discountType.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discountType.discountType][discountType.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{discountType.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discountType.discountType][discountType.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{discountType.createSetup}" value="New DiscountType" />
                <br />
                <h:commandLink action="#{discountType.listSetup}" value="Show All DiscountType Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
