<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>TranPayment Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>TranPayment Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{tranPayment.tranPayment.id}" title="Id" />
                    <h:outputText value="Amount:"/>
                    <h:outputText value="#{tranPayment.tranPayment.amount}" title="Amount" />
                    <h:outputText value="PayDate:"/>
                    <h:outputText value="#{tranPayment.tranPayment.payDate}" title="PayDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="PayTimestamp:"/>
                    <h:outputText value="#{tranPayment.tranPayment.payTimestamp}" title="PayTimestamp" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:outputText>
                    <h:outputText value="PaymentType:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranPayment.tranPayment.paymentType}"/>
                        <h:panelGroup rendered="#{tranPayment.tranPayment.paymentType != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{paymentType.detailSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{paymentType.editSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{paymentType.destroy}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranPayment.tranPayment.tranHeadId}"/>
                        <h:panelGroup rendered="#{tranPayment.tranPayment.tranHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tranHead.detailSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranHead.editSetup}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranHead.destroy}">
                                <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment.tranHeadId][tranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranPayment.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranPayment.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranPayment.tranPayment][tranPayment.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranPayment.createSetup}" value="New TranPayment" />
                <br />
                <h:commandLink action="#{tranPayment.listSetup}" value="Show All TranPayment Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
