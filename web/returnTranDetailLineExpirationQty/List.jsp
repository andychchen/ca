<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ReturnTranDetailLineExpirationQty Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ReturnTranDetailLineExpirationQty Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ReturnTranDetailLineExpirationQty Items Found)<br />" rendered="#{returnTranDetailLineExpirationQty.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{returnTranDetailLineExpirationQty.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{returnTranDetailLineExpirationQty.pagingInfo.firstItem + 1}..#{returnTranDetailLineExpirationQty.pagingInfo.lastItem} of #{returnTranDetailLineExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetailLineExpirationQty.prev}" value="Previous #{returnTranDetailLineExpirationQty.pagingInfo.batchSize}" rendered="#{returnTranDetailLineExpirationQty.pagingInfo.firstItem >= returnTranDetailLineExpirationQty.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetailLineExpirationQty.next}" value="Next #{returnTranDetailLineExpirationQty.pagingInfo.batchSize}" rendered="#{returnTranDetailLineExpirationQty.pagingInfo.lastItem + returnTranDetailLineExpirationQty.pagingInfo.batchSize <= returnTranDetailLineExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetailLineExpirationQty.next}" value="Remaining #{returnTranDetailLineExpirationQty.pagingInfo.itemCount - returnTranDetailLineExpirationQty.pagingInfo.lastItem}"
                                   rendered="#{returnTranDetailLineExpirationQty.pagingInfo.lastItem < returnTranDetailLineExpirationQty.pagingInfo.itemCount && returnTranDetailLineExpirationQty.pagingInfo.lastItem + returnTranDetailLineExpirationQty.pagingInfo.batchSize > returnTranDetailLineExpirationQty.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQtyItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ExpirationDate"/>
                            </f:facet>
                            <h:outputText value=" #{item.returnTranDetailLineExpirationQtyPK.expirationDate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Qty"/>
                            </f:facet>
                            <h:outputText value=" #{item.qty}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ReturnTranDetail"/>
                            </f:facet>
                            <h:outputText value=" #{item.returnTranDetail}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{returnTranDetailLineExpirationQty.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranDetailLineExpirationQty.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranDetailLineExpirationQty.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.createSetup}" value="New ReturnTranDetailLineExpirationQty"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
