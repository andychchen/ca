<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>TranHead Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>TranHead Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranHeadId:"/>
                    <h:outputText value="#{tranHead.tranHead.tranHeadId}" title="TranHeadId" />
                    <h:outputText value="TranDate:"/>
                    <h:outputText value="#{tranHead.tranHead.tranDate}" title="TranDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="TranTimestamp:"/>
                    <h:outputText value="#{tranHead.tranHead.tranTimestamp}" title="TranTimestamp" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="LineTotalAmt:"/>
                    <h:outputText value="#{tranHead.tranHead.lineTotalAmt}" title="LineTotalAmt" />
                    <h:outputText value="LineTaxAmt:"/>
                    <h:outputText value="#{tranHead.tranHead.lineTaxAmt}" title="LineTaxAmt" />
                    <h:outputText value="LineDiscountAmt:"/>
                    <h:outputText value="#{tranHead.tranHead.lineDiscountAmt}" title="LineDiscountAmt" />
                    <h:outputText value="TotalAfterHeadDiscount:"/>
                    <h:outputText value="#{tranHead.tranHead.totalAfterHeadDiscount}" title="TotalAfterHeadDiscount" />
                    <h:outputText value="TaxAfterHeadDiscount:"/>
                    <h:outputText value="#{tranHead.tranHead.taxAfterHeadDiscount}" title="TaxAfterHeadDiscount" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:outputText value="#{tranHead.tranHead.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="BottleRefund:"/>
                    <h:outputText value="#{tranHead.tranHead.bottleRefund}" title="BottleRefund" />
                    <h:outputText value="HeadNetTotal:"/>
                    <h:outputText value="#{tranHead.tranHead.headNetTotal}" title="HeadNetTotal" />
                    <h:outputText value="Note:"/>
                    <h:outputText value="#{tranHead.tranHead.note}" title="Note" />
                    <h:outputText value="HeadManagerDiscount:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranHead.tranHead.headManagerDiscount}"/>
                        <h:panelGroup rendered="#{tranHead.tranHead.headManagerDiscount != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{discount.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{discount.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{discount.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentDiscount" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.headManagerDiscount][discount.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="UserId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranHead.tranHead.userId}"/>
                        <h:panelGroup rendered="#{tranHead.tranHead.userId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{empUsers.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.userId][empUsers.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{empUsers.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.userId][empUsers.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{empUsers.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.userId][empUsers.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="OriginalHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranHead.tranHead.originalHeadId}"/>
                        <h:panelGroup rendered="#{tranHead.tranHead.originalHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tranHead.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.originalHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.originalHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.originalHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsForReturned:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranHead.tranHead.isForReturned}"/>
                        <h:panelGroup rendered="#{tranHead.tranHead.isForReturned != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isForReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isForReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isForReturned][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsTrainingMode:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranHead.tranHead.isTrainingMode}"/>
                        <h:panelGroup rendered="#{tranHead.tranHead.isTrainingMode != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead.isTrainingMode][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranHead"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tranHead.tranHead.tranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tranHead.tranHead.tranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tranHead.tranHead.tranDetailCollection}">
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
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranDetail.editSetup}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranDetail.destroy}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="TranPaymentCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tranHead.tranHead.tranPaymentCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tranHead.tranHead.tranPaymentCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tranHead.tranHead.tranPaymentCollection}">
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
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranPayment.editSetup}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranPayment.destroy}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranPayment.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="TranHeadCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty tranHead.tranHead.tranHeadCollection}" value="(No Items)"/>
                        <h:dataTable value="#{tranHead.tranHead.tranHeadCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty tranHead.tranHead.tranHeadCollection}">
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
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranHead.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="tranHead" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.TranHeadController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>



                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranHead.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranHead.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranHead.createSetup}" value="New TranHead" />
                <br />
                <h:commandLink action="#{tranHead.listSetup}" value="Show All TranHead Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
