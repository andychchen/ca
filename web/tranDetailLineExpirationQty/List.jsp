<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing TranDetailLineExpirationQty Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing TranDetailLineExpirationQty Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No TranDetailLineExpirationQty Items Found)<br />" rendered="#{tranDetailLineExpirationQty.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{tranDetailLineExpirationQty.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{tranDetailLineExpirationQty.pagingInfo.firstItem + 1}..#{tranDetailLineExpirationQty.pagingInfo.lastItem} of #{tranDetailLineExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranDetailLineExpirationQty.first}" value="First" rendered="#{tranDetailLineExpirationQty.pagingInfo.firstItem >= tranDetailLineExpirationQty.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tranDetailLineExpirationQty.prev}" value="Previous #{tranDetailLineExpirationQty.pagingInfo.batchSize}" rendered="#{tranDetailLineExpirationQty.pagingInfo.firstItem >= tranDetailLineExpirationQty.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tranDetailLineExpirationQty.next}" value="Next #{tranDetailLineExpirationQty.pagingInfo.batchSize}" rendered="#{tranDetailLineExpirationQty.pagingInfo.lastItem + tranDetailLineExpirationQty.pagingInfo.batchSize <= tranDetailLineExpirationQty.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranDetailLineExpirationQty.next}" value="Remaining #{tranDetailLineExpirationQty.pagingInfo.itemCount - tranDetailLineExpirationQty.pagingInfo.lastItem}"
                                   rendered="#{tranDetailLineExpirationQty.pagingInfo.lastItem < tranDetailLineExpirationQty.pagingInfo.itemCount && tranDetailLineExpirationQty.pagingInfo.lastItem + tranDetailLineExpirationQty.pagingInfo.batchSize > tranDetailLineExpirationQty.pagingInfo.itemCount}"/>
                    <h:commandLink action="#{tranDetailLineExpirationQty.last}" value="Last" rendered="#{tranDetailLineExpirationQty.pagingInfo.lastItem + tranDetailLineExpirationQty.pagingInfo.batchSize <= tranDetailLineExpirationQty.pagingInfo.itemCount}"/>
                   <h:dataTable value="#{tranDetailLineExpirationQty.tranDetailLineExpirationQtyItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ExpirationDate"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranDetailLineExpirationQtyPK.expirationDate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Qty"/>
                            </f:facet>
                            <h:outputText value=" #{item.qty}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranDetail"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranDetail}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{tranDetailLineExpirationQty.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranDetailLineExpirationQty.editSetup}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranDetailLineExpirationQty.destroy}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{tranDetailLineExpirationQty.createSetup}" value="New TranDetailLineExpirationQty"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
