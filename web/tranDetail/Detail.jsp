<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>TranDetail Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>TranDetail Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranDetailId:"/>
                    <h:outputText value="#{tranDetail.tranDetail.tranDetailId}" title="TranDetailId" />
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{tranDetail.tranDetail.qty}" title="Qty" />
                    <h:outputText value="Cost:"/>
                    <h:outputText value="#{tranDetail.tranDetail.cost}" title="Cost" />
                    <h:outputText value="Price:"/>
                    <h:outputText value="#{tranDetail.tranDetail.price}" title="Price" />
                    <h:outputText value="DiscountAmt:"/>
                    <h:outputText value="#{tranDetail.tranDetail.discountAmt}" title="DiscountAmt" />
                    <h:outputText value="Total:"/>
                    <h:outputText value="#{tranDetail.tranDetail.total}" title="Total" />
                    <h:outputText value="TaxAmt:"/>
                    <h:outputText value="#{tranDetail.tranDetail.taxAmt}" title="TaxAmt" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:outputText value="#{tranDetail.tranDetail.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="SubTotal:"/>
                    <h:outputText value="#{tranDetail.tranDetail.subTotal}" title="SubTotal" />
                    <h:outputText value="SubTax:"/>
                    <h:outputText value="#{tranDetail.tranDetail.subTax}" title="SubTax" />
                    <h:outputText value="DistributedHeadDiscountAmt:"/>
                    <h:outputText value="#{tranDetail.tranDetail.distributedHeadDiscountAmt}" title="DistributedHeadDiscountAmt" />
                    <h:outputText value="SubSubTotal:"/>
                    <h:outputText value="#{tranDetail.tranDetail.subSubTotal}" title="SubSubTotal" />
                    <h:outputText value="SubSubTax:"/>
                    <h:outputText value="#{tranDetail.tranDetail.subSubTax}" title="SubSubTax" />
                    <h:outputText value="LineNetTotal:"/>
                    <h:outputText value="#{tranDetail.tranDetail.lineNetTotal}" title="LineNetTotal" />
                    <h:outputText value="LineManagerDiscount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.lineManagerDiscount}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.lineManagerDiscount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.lineManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Discount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.discount}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.discount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.discount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Isbn:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.isbn}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.isbn != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{product.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{product.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{product.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.isbn][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Promotion:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.promotion}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.promotion != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{promotion.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{promotion.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Tax:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.tax}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.tax != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tax.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tax.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tax.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetail.tranDetail.tranHeadId}"/>
                        <h:panelGroup rendered="#{tranDetail.tranDetail.tranHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tranHead.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetail"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranDetailLineExpirationQtyCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tranDetail.tranDetail.tranDetailLineExpirationQtyCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tranDetail.tranDetail.tranDetailLineExpirationQtyCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tranDetail.tranDetail.tranDetailLineExpirationQtyCollection}">
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
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranDetailLineExpirationQty.editSetup}">
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranDetailLineExpirationQty.destroy}">
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranDetail" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranDetail.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranDetail.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetail.tranDetail][tranDetail.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranDetail.createSetup}" value="New TranDetail" />
                <br />
                <h:commandLink action="#{tranDetail.listSetup}" value="Show All TranDetail Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
