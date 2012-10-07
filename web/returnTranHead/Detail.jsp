<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ReturnTranHead Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ReturnTranHead Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranHeadId:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.tranHeadId}" title="TranHeadId" />
                    <h:outputText value="TranDate:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.tranDate}" title="TranDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="LineTotalAmt:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.lineTotalAmt}" title="LineTotalAmt" />
                    <h:outputText value="LineTaxAmt:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.lineTaxAmt}" title="LineTaxAmt" />
                    <h:outputText value="LineDiscountAmt:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.lineDiscountAmt}" title="LineDiscountAmt" />
                    <h:outputText value="TotalAfterHeadDiscount:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.totalAfterHeadDiscount}" title="TotalAfterHeadDiscount" />
                    <h:outputText value="TaxAfterHeadDiscount:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.taxAfterHeadDiscount}" title="TaxAfterHeadDiscount" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="BottleRefund:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.bottleRefund}" title="BottleRefund" />
                    <h:outputText value="HeadNetTotal:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.headNetTotal}" title="HeadNetTotal" />
                    <h:outputText value="Note:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.note}" title="Note" />
                    <h:outputText value="ReturnDate:"/>
                    <h:outputText value="#{returnTranHead.returnTranHead.returnDate}" title="ReturnDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="HeadManagerDiscount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranHead.returnTranHead.headManagerDiscount}"/>
                        <h:panelGroup rendered="#{returnTranHead.returnTranHead.headManagerDiscount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="OriginalTranHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranHead.returnTranHead.originalTranHeadId}"/>
                        <h:panelGroup rendered="#{returnTranHead.returnTranHead.originalTranHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tranHead.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.originalTranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.originalTranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.originalTranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsTrainingMode:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranHead.returnTranHead.isTrainingMode}"/>
                        <h:panelGroup rendered="#{returnTranHead.returnTranHead.isTrainingMode != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty returnTranHead.returnTranHead.returnTranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{returnTranHead.returnTranHead.returnTranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty returnTranHead.returnTranHead.returnTranDetailCollection}">
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
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranDetail.destroy}">
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ReturnTranPaymentCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty returnTranHead.returnTranHead.returnTranPaymentCollection}" value="(No Items)"/>
                        <h:dataTable value="#{returnTranHead.returnTranHead.returnTranPaymentCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty returnTranHead.returnTranHead.returnTranPaymentCollection}">
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
                                <h:commandLink value="Show" action="#{returnTranPayment.detailSetup}">
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{returnTranPayment.editSetup}">
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{returnTranPayment.destroy}">
                                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][returnTranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="returnTranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranHeadController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranHead.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{returnTranHead.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranHead.returnTranHead][returnTranHead.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{returnTranHead.createSetup}" value="New ReturnTranHead" />
                <br />
                <h:commandLink action="#{returnTranHead.listSetup}" value="Show All ReturnTranHead Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
