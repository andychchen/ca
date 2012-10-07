<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ReturnTranHead Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ReturnTranHead Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ReturnTranHead Items Found)<br />" rendered="#{returnTranHead.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{returnTranHead.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{returnTranHead.pagingInfo.firstItem + 1}..#{returnTranHead.pagingInfo.lastItem} of #{returnTranHead.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranHead.prev}" value="Previous #{returnTranHead.pagingInfo.batchSize}" rendered="#{returnTranHead.pagingInfo.firstItem >= returnTranHead.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{returnTranHead.next}" value="Next #{returnTranHead.pagingInfo.batchSize}" rendered="#{returnTranHead.pagingInfo.lastItem + returnTranHead.pagingInfo.batchSize <= returnTranHead.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranHead.next}" value="Remaining #{returnTranHead.pagingInfo.itemCount - returnTranHead.pagingInfo.lastItem}"
                                   rendered="#{returnTranHead.pagingInfo.lastItem < returnTranHead.pagingInfo.itemCount && returnTranHead.pagingInfo.lastItem + returnTranHead.pagingInfo.batchSize > returnTranHead.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{returnTranHead.returnTranHeadItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranHeadId"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranHeadId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranDate"/>
                            </f:facet>
                            <h:outputText value="#{item.tranDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LineTotalAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.lineTotalAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LineTaxAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.lineTaxAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LineDiscountAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.lineDiscountAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TotalAfterHeadDiscount"/>
                            </f:facet>
                            <h:outputText value=" #{item.totalAfterHeadDiscount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TaxAfterHeadDiscount"/>
                            </f:facet>
                            <h:outputText value=" #{item.taxAfterHeadDiscount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ManagerDiscountAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.managerDiscountAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="BottleRefund"/>
                            </f:facet>
                            <h:outputText value=" #{item.bottleRefund}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="HeadNetTotal"/>
                            </f:facet>
                            <h:outputText value=" #{item.headNetTotal}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Note"/>
                            </f:facet>
                            <h:outputText value=" #{item.note}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ReturnDate"/>
                            </f:facet>
                            <h:outputText value="#{item.returnDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="HeadManagerDiscount"/>
                            </f:facet>
                            <h:outputText value=" #{item.headManagerDiscount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="OriginalTranHeadId"/>
                            </f:facet>
                            <h:outputText value=" #{item.originalTranHeadId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsTrainingMode"/>
                            </f:facet>
                            <h:outputText value=" #{item.isTrainingMode}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{returnTranHead.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranHead.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranHead.destroy}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{returnTranHead.createSetup}" value="New ReturnTranHead"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
