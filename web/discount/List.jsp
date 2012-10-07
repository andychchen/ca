<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Discount Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Discount Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Discount Items Found)<br />" rendered="#{discount.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{discount.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{discount.pagingInfo.firstItem + 1}..#{discount.pagingInfo.lastItem} of #{discount.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{discount.prev}" value="Previous #{discount.pagingInfo.batchSize}" rendered="#{discount.pagingInfo.firstItem >= discount.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{discount.next}" value="Next #{discount.pagingInfo.batchSize}" rendered="#{discount.pagingInfo.lastItem + discount.pagingInfo.batchSize <= discount.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{discount.next}" value="Remaining #{discount.pagingInfo.itemCount - discount.pagingInfo.lastItem}"
                                   rendered="#{discount.pagingInfo.lastItem < discount.pagingInfo.itemCount && discount.pagingInfo.lastItem + discount.pagingInfo.batchSize > discount.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{discount.discountItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discount.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{discount.createSetup}" value="New Discount"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
