<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing TranHead Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing TranHead Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No TranHead Items Found)<br />" rendered="#{tranHead.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{tranHead.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{tranHead.pagingInfo.firstItem + 1}..#{tranHead.pagingInfo.lastItem} of #{tranHead.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranHead.first}" value="First" rendered="#{tranHead.pagingInfo.firstItem >= tranHead.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tranHead.prev}" value="Previous #{tranHead.pagingInfo.batchSize}" rendered="#{tranHead.pagingInfo.firstItem >= tranHead.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tranHead.next}" value="Next #{tranHead.pagingInfo.batchSize}" rendered="#{tranHead.pagingInfo.lastItem + tranHead.pagingInfo.batchSize <= tranHead.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tranHead.next}" value="Remaining #{tranHead.pagingInfo.itemCount - tranHead.pagingInfo.lastItem}"
                                   rendered="#{tranHead.pagingInfo.lastItem < tranHead.pagingInfo.itemCount && tranHead.pagingInfo.lastItem + tranHead.pagingInfo.batchSize > tranHead.pagingInfo.itemCount}"/>
                    <h:commandLink action="#{tranHead.last}" value="Last" rendered="#{tranHead.pagingInfo.lastItem + tranHead.pagingInfo.batchSize <= tranHead.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{tranHead.tranHeadItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <h:outputText value="TranTimestamp"/>
                            </f:facet>
                            <h:outputText value="#{item.tranTimestamp}">
                                <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
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
                                <h:outputText value="HeadManagerDiscount"/>
                            </f:facet>
                            <h:outputText value=" #{item.headManagerDiscount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserId"/>
                            </f:facet>
                            <h:outputText value=" #{item.userId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="OriginalHeadId"/>
                            </f:facet>
                            <h:outputText value=" #{item.originalHeadId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsForReturned"/>
                            </f:facet>
                            <h:outputText value=" #{item.isForReturned}"/>
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
                            <h:commandLink value="Show" action="#{tranHead.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{tranHead.createSetup}" value="New TranHead"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
