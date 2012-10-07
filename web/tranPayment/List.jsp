<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing TranPayment Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing TranPayment Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No TranPayment Items Found)<br />" rendered="#{tranPayment.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{tranPayment.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{tranPayment.pagingInfo.firstItem + 1}..#{tranPayment.pagingInfo.lastItem} of #{tranPayment.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranPayment.prev}" value="Previous #{tranPayment.pagingInfo.batchSize}" rendered="#{tranPayment.pagingInfo.firstItem >= tranPayment.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tranPayment.next}" value="Next #{tranPayment.pagingInfo.batchSize}" rendered="#{tranPayment.pagingInfo.lastItem + tranPayment.pagingInfo.batchSize <= tranPayment.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranPayment.next}" value="Remaining #{tranPayment.pagingInfo.itemCount - tranPayment.pagingInfo.lastItem}"
                                   rendered="#{tranPayment.pagingInfo.lastItem < tranPayment.pagingInfo.itemCount && tranPayment.pagingInfo.lastItem + tranPayment.pagingInfo.batchSize > tranPayment.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{tranPayment.tranPaymentItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value=" #{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Amount"/>
                            </f:facet>
                            <h:outputText value=" #{item.amount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PayDate"/>
                            </f:facet>
                            <h:outputText value="#{item.payDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PayTimestamp"/>
                            </f:facet>
                            <h:outputText value="#{item.payTimestamp}">
                                <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PaymentType"/>
                            </f:facet>
                            <h:outputText value=" #{item.paymentType}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranHeadId"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranHeadId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{tranPayment.detailSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranPayment.editSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranPayment.destroy}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{tranPayment.createSetup}" value="New TranPayment"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
