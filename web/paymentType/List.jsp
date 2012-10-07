<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing PaymentType Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing PaymentType Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No PaymentType Items Found)<br />" rendered="#{paymentType.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{paymentType.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{paymentType.pagingInfo.firstItem + 1}..#{paymentType.pagingInfo.lastItem} of #{paymentType.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{paymentType.prev}" value="Previous #{paymentType.pagingInfo.batchSize}" rendered="#{paymentType.pagingInfo.firstItem >= paymentType.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{paymentType.next}" value="Next #{paymentType.pagingInfo.batchSize}" rendered="#{paymentType.pagingInfo.lastItem + paymentType.pagingInfo.batchSize <= paymentType.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{paymentType.next}" value="Remaining #{paymentType.pagingInfo.itemCount - paymentType.pagingInfo.lastItem}"
                                   rendered="#{paymentType.pagingInfo.lastItem < paymentType.pagingInfo.itemCount && paymentType.pagingInfo.lastItem + paymentType.pagingInfo.batchSize > paymentType.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{paymentType.paymentTypeItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value=" #{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{paymentType.detailSetup}">
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][paymentType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{paymentType.editSetup}">
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][paymentType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{paymentType.destroy}">
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][paymentType.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{paymentType.createSetup}" value="New PaymentType"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
