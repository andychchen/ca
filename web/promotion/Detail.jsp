<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Promotion Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Promotion Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PromotionId:"/>
                    <h:outputText value="#{promotion.promotion.promotionId}" title="PromotionId" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{promotion.promotion.name}" title="Name" />
                    <h:outputText value="StartDate:"/>
                    <h:outputText value="#{promotion.promotion.startDate}" title="StartDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="EndDate:"/>
                    <h:outputText value="#{promotion.promotion.endDate}" title="EndDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="YearlyMonth:"/>
                    <h:outputText value="#{promotion.promotion.yearlyMonth}" title="YearlyMonth" />
                    <h:outputText value="MonthlyWeek:"/>
                    <h:outputText value="#{promotion.promotion.monthlyWeek}" title="MonthlyWeek" />
                    <h:outputText value="Discount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{promotion.promotion.discount}"/>
                        <h:panelGroup rendered="#{promotion.promotion.discount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="WeeklyDay:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{promotion.promotion.weeklyDay}"/>
                        <h:panelGroup rendered="#{promotion.promotion.weeklyDay != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{weekDays.detailSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.weeklyDay][weekDays.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{weekDays.editSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.weeklyDay][weekDays.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{weekDays.destroy}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.weeklyDay][weekDays.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsEnabled:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{promotion.promotion.isEnabled}"/>
                        <h:panelGroup rendered="#{promotion.promotion.isEnabled != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isEnabled][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isEnabled][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isEnabled][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsAppliedToAll:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{promotion.promotion.isAppliedToAll}"/>
                        <h:panelGroup rendered="#{promotion.promotion.isAppliedToAll != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isAppliedToAll][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isAppliedToAll][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion.isAppliedToAll][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="promotion"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    
                    <h:outputText value="TranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty promotion.promotion.tranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{promotion.promotion.tranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty promotion.promotion.tranDetailCollection}">
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
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranDetail.destroy}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>

                    <h:outputText value="ProductCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty promotion.promotion.productCollection}" value="(No Items)"/>
                        <h:dataTable value="#{promotion.promotion.productCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty promotion.promotion.productCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Isbn"/>
                                </f:facet>
                                <h:outputText value=" #{item.isbn}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value=" #{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Description"/>
                                </f:facet>
                                <h:outputText value=" #{item.description}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Qty"/>
                                </f:facet>
                                <h:outputText value=" #{item.qty}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Price"/>
                                </f:facet>
                                <h:outputText value=" #{item.price}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cost"/>
                                </f:facet>
                                <h:outputText value=" #{item.cost}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="LastUpdateDate"/>
                                </f:facet>
                                <h:outputText value="#{item.lastUpdateDate}">
                                    <f:convertDateTime pattern="MM/dd/yyyy" />
                                </h:outputText>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Brand"/>
                                </f:facet>
                                <h:outputText value=" #{item.brand}"/>
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
                                    <h:outputText value="IsOrganic"/>
                                </f:facet>
                                <h:outputText value=" #{item.isOrganic}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="HasExpirationDate"/>
                                </f:facet>
                                <h:outputText value=" #{item.hasExpirationDate}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="BrandDiscountExcluded"/>
                                </f:facet>
                                <h:outputText value=" #{item.brandDiscountExcluded}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{product.detailSetup}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{product.editSetup}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{product.destroy}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="BrandCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty promotion.promotion.brandCollection}" value="(No Items)"/>
                        <h:dataTable value="#{promotion.promotion.brandCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty promotion.promotion.brandCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="BrandId"/>
                                </f:facet>
                                <h:outputText value=" #{item.brandId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Name"/>
                                </f:facet>
                                <h:outputText value=" #{item.name}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Description"/>
                                </f:facet>
                                <h:outputText value=" #{item.description}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Promotion"/>
                                </f:facet>
                                <h:outputText value=" #{item.promotion}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{brand.detailSetup}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{brand.editSetup}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{brand.destroy}">
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="promotion" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.PromotionController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                   
                </h:panelGrid>
                <br />
                <h:commandLink action="#{promotion.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{promotion.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{promotion.createSetup}" value="New Promotion" />
                <br />
                <h:commandLink action="#{promotion.listSetup}" value="Show All Promotion Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
