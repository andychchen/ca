<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ProductExpirationQty Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ProductExpirationQty Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ProductExpirationQty Items Found)<br />" rendered="#{productExpirationQty.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{productExpirationQty.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{productExpirationQty.pagingInfo.firstItem + 1}..#{productExpirationQty.pagingInfo.lastItem} of #{productExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{productExpirationQty.first}" value="First" rendered="#{productExpirationQty.pagingInfo.firstItem >= productExpirationQty.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{productExpirationQty.prev}" value="Previous #{productExpirationQty.pagingInfo.batchSize}" rendered="#{productExpirationQty.pagingInfo.firstItem >= productExpirationQty.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{productExpirationQty.next}" value="Next #{productExpirationQty.pagingInfo.batchSize}" rendered="#{productExpirationQty.pagingInfo.lastItem + productExpirationQty.pagingInfo.batchSize <= productExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{productExpirationQty.next}" value="Remaining #{productExpirationQty.pagingInfo.itemCount - productExpirationQty.pagingInfo.lastItem}"
                                   rendered="#{productExpirationQty.pagingInfo.lastItem < productExpirationQty.pagingInfo.itemCount && productExpirationQty.pagingInfo.lastItem + productExpirationQty.pagingInfo.batchSize > productExpirationQty.pagingInfo.itemCount}"/>
                    <h:commandLink action="#{productExpirationQty.last}" value="Last" rendered="#{productExpirationQty.pagingInfo.lastItem + productExpirationQty.pagingInfo.batchSize <= productExpirationQty.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{productExpirationQty.productExpirationQtyItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ExpirationDate"/>
                            </f:facet>
                            <h:outputText value=" #{item.productExpirationQtyPK.expirationDate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Qty"/>
                            </f:facet>
                            <h:outputText value=" #{item.qty}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Product"/>
                            </f:facet>
                            <h:outputText value=" #{item.product}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{productExpirationQty.detailSetup}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][productExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{productExpirationQty.editSetup}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][productExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{productExpirationQty.destroy}">
                                <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][productExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{productExpirationQty.createSetup}" value="New ProductExpirationQty"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
