<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ReturnTranDetail Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ReturnTranDetail Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ReturnTranDetail Items Found)<br />" rendered="#{returnTranDetail.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{returnTranDetail.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{returnTranDetail.pagingInfo.firstItem + 1}..#{returnTranDetail.pagingInfo.lastItem} of #{returnTranDetail.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetail.prev}" value="Previous #{returnTranDetail.pagingInfo.batchSize}" rendered="#{returnTranDetail.pagingInfo.firstItem >= returnTranDetail.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetail.next}" value="Next #{returnTranDetail.pagingInfo.batchSize}" rendered="#{returnTranDetail.pagingInfo.lastItem + returnTranDetail.pagingInfo.batchSize <= returnTranDetail.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{returnTranDetail.next}" value="Remaining #{returnTranDetail.pagingInfo.itemCount - returnTranDetail.pagingInfo.lastItem}"
                                   rendered="#{returnTranDetail.pagingInfo.lastItem < returnTranDetail.pagingInfo.itemCount && returnTranDetail.pagingInfo.lastItem + returnTranDetail.pagingInfo.batchSize > returnTranDetail.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{returnTranDetail.returnTranDetailItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranDetailId"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranDetailId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Qty"/>
                            </f:facet>
                            <h:outputText value=" #{item.qty}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Cost"/>
                            </f:facet>
                            <h:outputText value=" #{item.cost}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Price"/>
                            </f:facet>
                            <h:outputText value=" #{item.price}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DiscountAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.discountAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Total"/>
                            </f:facet>
                            <h:outputText value=" #{item.total}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TaxAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.taxAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="ManagerDiscountAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.managerDiscountAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="SubTotal"/>
                            </f:facet>
                            <h:outputText value=" #{item.subTotal}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="SubTax"/>
                            </f:facet>
                            <h:outputText value=" #{item.subTax}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="DistributedHeadDiscountAmt"/>
                            </f:facet>
                            <h:outputText value=" #{item.distributedHeadDiscountAmt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="SubSubTotal"/>
                            </f:facet>
                            <h:outputText value=" #{item.subSubTotal}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="SubSubTax"/>
                            </f:facet>
                            <h:outputText value=" #{item.subSubTax}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LineNetTotal"/>
                            </f:facet>
                            <h:outputText value=" #{item.lineNetTotal}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LineManagerDiscount"/>
                            </f:facet>
                            <h:outputText value=" #{item.lineManagerDiscount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Discount"/>
                            </f:facet>
                            <h:outputText value=" #{item.discount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Isbn"/>
                            </f:facet>
                            <h:outputText value=" #{item.isbn}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Promotion"/>
                            </f:facet>
                            <h:outputText value=" #{item.promotion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TranHeadId"/>
                            </f:facet>
                            <h:outputText value=" #{item.tranHeadId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tax"/>
                            </f:facet>
                            <h:outputText value=" #{item.tax}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsReturned"/>
                            </f:facet>
                            <h:outputText value=" #{item.isReturned}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{returnTranDetail.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranDetail.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranDetail.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{returnTranDetail.createSetup}" value="New ReturnTranDetail"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
