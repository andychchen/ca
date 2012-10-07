<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ReturnTranPayment Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ReturnTranPayment Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{returnTranPayment.returnTranPayment.id}" title="Id" />
                    <h:outputText value="Amount:"/>
                    <h:outputText value="#{returnTranPayment.returnTranPayment.amount}" title="Amount" />
                    <h:outputText value="PayDate:"/>
                    <h:outputText value="#{returnTranPayment.returnTranPayment.payDate}" title="PayDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="PaymentType:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranPayment.returnTranPayment.paymentType}"/>
                        <h:panelGroup rendered="#{returnTranPayment.returnTranPayment.paymentType != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{paymentType.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{paymentType.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{paymentType.destroy}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPaymentType" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.paymentType][paymentType.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="TranHeadId:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranPayment.returnTranPayment.tranHeadId}"/>
                        <h:panelGroup rendered="#{returnTranPayment.returnTranPayment.tranHeadId != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{returnTranHead.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranHead.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranHead.destroy}">
                                <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment.tranHeadId][returnTranHead.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranPayment"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranPaymentController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranPayment.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{returnTranPayment.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentReturnTranPayment" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranPayment.returnTranPayment][returnTranPayment.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{returnTranPayment.createSetup}" value="New ReturnTranPayment" />
                <br />
                <h:commandLink action="#{returnTranPayment.listSetup}" value="Show All ReturnTranPayment Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
