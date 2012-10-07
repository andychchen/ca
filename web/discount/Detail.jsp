<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Discount Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Discount Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="DiscountId:"/>
                    <h:outputText value="#{discount.discount.discountId}" title="DiscountId" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{discount.discount.name}" title="Name" />
                    <h:outputText value="BuyHow:"/>
                    <h:outputText value="#{discount.discount.buyHow}" title="BuyHow" />
                    <h:outputText value="GetHow:"/>
                    <h:outputText value="#{discount.discount.getHow}" title="GetHow" />
                    <h:outputText value="Type:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{discount.discount.type}"/>
                        <h:panelGroup rendered="#{discount.discount.type != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discountType.detailSetup}">
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount.type][discountType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="discount"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discountType.editSetup}">
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount.type][discountType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="discount"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discountType.destroy}">
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscountType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount.type][discountType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="discount"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="PromotionCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.promotionCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.promotionCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.promotionCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PromotionId"/>
                                </f:facet>
                                <h:outputText value=" #{item.promotionId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value=" #{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="StartDate"/>
                                </f:facet>
                                <h:outputText value="#{item.startDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="EndDate"/>
                                </f:facet>
                                <h:outputText value="#{item.endDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="YearlyMonth"/>
                                </f:facet>
                                <h:outputText value=" #{item.yearlyMonth}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="MonthlyWeek"/>
                                </f:facet>
                                <h:outputText value=" #{item.monthlyWeek}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Discount"/>
                                </f:facet>
                                <h:outputText value=" #{item.discount}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="WeeklyDay"/>
                                </f:facet>
                                <h:outputText value=" #{item.weeklyDay}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IsEnabled"/>
                                </f:facet>
                                <h:outputText value=" #{item.isEnabled}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="IsAppliedToAll"/>
                                </f:facet>
                                <h:outputText value=" #{item.isAppliedToAll}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{promotion.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{promotion.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <!--
                    <h:outputText value="TranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.tranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.tranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.tranDetailCollection}">
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
                                    <h:outputText value="Tax"/>
                                </f:facet>
                                <h:outputText value=" #{item.tax}"/>
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
                                <h:commandLink value="Show" action="#{tranDetail.detailSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranDetail.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="TranDetailCollection1:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.tranDetailCollection1}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.tranDetailCollection1}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.tranDetailCollection1}">
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
                                    <h:outputText value="Tax"/>
                                </f:facet>
                                <h:outputText value=" #{item.tax}"/>
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
                                <h:commandLink value="Show" action="#{tranDetail.detailSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranDetail.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.returnTranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.returnTranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.returnTranDetailCollection}">
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
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranDetail.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranDetailCollection1:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.returnTranDetailCollection1}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.returnTranDetailCollection1}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.returnTranDetailCollection1}">
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
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranDetail.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="TranHeadCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.tranHeadCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.tranHeadCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.tranHeadCollection}">
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
                                    <h:outputText value="HeadManagerDiscount"/>
                                </f:facet>
                                <h:outputText value=" #{item.headManagerDiscount}"/>
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
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranHeadCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty discount.discount.returnTranHeadCollection}" value="(No Items)"/>
                        <h:dataTable value="#{discount.discount.returnTranHeadCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty discount.discount.returnTranHeadCollection}">
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
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranHead.editSetup}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranHead.destroy}">
                                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="discount" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.DiscountController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    -->
                </h:panelGrid>
                <br />
                <h:commandLink action="#{discount.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{discount.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][discount.discount][discount.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{discount.createSetup}" value="New Discount" />
                <br />
                <h:commandLink action="#{discount.listSetup}" value="Show All Discount Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
