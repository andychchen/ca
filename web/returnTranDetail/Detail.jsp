<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ReturnTranDetail Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ReturnTranDetail Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranDetailId:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.tranDetailId}" title="TranDetailId" />
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.qty}" title="Qty" />
                    <h:outputText value="Cost:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.cost}" title="Cost" />
                    <h:outputText value="Price:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.price}" title="Price" />
                    <h:outputText value="DiscountAmt:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.discountAmt}" title="DiscountAmt" />
                    <h:outputText value="Total:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.total}" title="Total" />
                    <h:outputText value="TaxAmt:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.taxAmt}" title="TaxAmt" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="SubTotal:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.subTotal}" title="SubTotal" />
                    <h:outputText value="SubTax:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.subTax}" title="SubTax" />
                    <h:outputText value="DistributedHeadDiscountAmt:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.distributedHeadDiscountAmt}" title="DistributedHeadDiscountAmt" />
                    <h:outputText value="SubSubTotal:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.subSubTotal}" title="SubSubTotal" />
                    <h:outputText value="SubSubTax:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.subSubTax}" title="SubSubTax" />
                    <h:outputText value="LineNetTotal:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.lineNetTotal}" title="LineNetTotal" />
                    <h:outputText value="LineManagerDiscount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.lineManagerDiscount}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.lineManagerDiscount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Discount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.discount}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.discount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Isbn:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.isbn}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.isbn != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{product.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{product.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{product.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Promotion:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.promotion}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.promotion != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{promotion.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{promotion.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.tranHeadId}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.tranHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{returnTranHead.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranHead.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranHead.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Tax:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.tax}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.tax != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tax.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tax.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tax.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsReturned:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetail.returnTranDetail.isReturned}"/>
                        <h:panelGroup rendered="#{returnTranDetail.returnTranDetail.isReturned != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail.isReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranDetailLineExpirationQtyCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty returnTranDetail.returnTranDetail.returnTranDetailLineExpirationQtyCollection}" value="(No Items)"/>
                        <h:dataTable value="#{returnTranDetail.returnTranDetail.returnTranDetailLineExpirationQtyCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty returnTranDetail.returnTranDetail.returnTranDetailLineExpirationQtyCollection}">
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
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranDetailLineExpirationQty.editSetup}">
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranDetailLineExpirationQty.destroy}">
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranDetail.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{returnTranDetail.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{returnTranDetail.createSetup}" value="New ReturnTranDetail" />
                <br />
                <h:commandLink action="#{returnTranDetail.listSetup}" value="Show All ReturnTranDetail Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
