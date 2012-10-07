<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing DiscountType Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing DiscountType Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No DiscountType Items Found)<br />" rendered="#{discountType.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{discountType.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{discountType.pagingInfo.firstItem + 1}..#{discountType.pagingInfo.lastItem} of #{discountType.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{discountType.prev}" value="Previous #{discountType.pagingInfo.batchSize}" rendered="#{discountType.pagingInfo.firstItem >= discountType.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{discountType.next}" value="Next #{discountType.pagingInfo.batchSize}" rendered="#{discountType.pagingInfo.lastItem + discountType.pagingInfo.batchSize <= discountType.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{discountType.next}" value="Remaining #{discountType.pagingInfo.itemCount - discountType.pagingInfo.lastItem}"
                                   rendered="#{discountType.pagingInfo.lastItem < discountType.pagingInfo.itemCount && discountType.pagingInfo.lastItem + discountType.pagingInfo.batchSize > discountType.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{discountType.discountTypeItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
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
                            <h:commandLink value="Show" action="#{discountType.detailSetup}">
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discountType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discountType.editSetup}">
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discountType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discountType.destroy}">
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][discountType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{discountType.createSetup}" value="New DiscountType"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
