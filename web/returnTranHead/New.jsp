<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New ReturnTranHead</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New ReturnTranHead</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{returnTranHead.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="TranDate (MM/dd/yyyy):"/>
                    <h:inputText id="tranDate" value="#{returnTranHead.returnTranHead.tranDate}" title="TranDate" required="true" requiredMessage="The tranDate field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="LineTotalAmt:"/>
                    <h:inputText id="lineTotalAmt" value="#{returnTranHead.returnTranHead.lineTotalAmt}" title="LineTotalAmt" required="true" requiredMessage="The lineTotalAmt field is required." />
                    <h:outputText value="LineTaxAmt:"/>
                    <h:inputText id="lineTaxAmt" value="#{returnTranHead.returnTranHead.lineTaxAmt}" title="LineTaxAmt" required="true" requiredMessage="The lineTaxAmt field is required." />
                    <h:outputText value="LineDiscountAmt:"/>
                    <h:inputText id="lineDiscountAmt" value="#{returnTranHead.returnTranHead.lineDiscountAmt}" title="LineDiscountAmt" required="true" requiredMessage="The lineDiscountAmt field is required." />
                    <h:outputText value="TotalAfterHeadDiscount:"/>
                    <h:inputText id="totalAfterHeadDiscount" value="#{returnTranHead.returnTranHead.totalAfterHeadDiscount}" title="TotalAfterHeadDiscount" required="true" requiredMessage="The totalAfterHeadDiscount field is required." />
                    <h:outputText value="TaxAfterHeadDiscount:"/>
                    <h:inputText id="taxAfterHeadDiscount" value="#{returnTranHead.returnTranHead.taxAfterHeadDiscount}" title="TaxAfterHeadDiscount" required="true" requiredMessage="The taxAfterHeadDiscount field is required." />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:inputText id="managerDiscountAmt" value="#{returnTranHead.returnTranHead.managerDiscountAmt}" title="ManagerDiscountAmt" required="true" requiredMessage="The managerDiscountAmt field is required." />
                    <h:outputText value="BottleRefund:"/>
                    <h:inputText id="bottleRefund" value="#{returnTranHead.returnTranHead.bottleRefund}" title="BottleRefund" required="true" requiredMessage="The bottleRefund field is required." />
                    <h:outputText value="HeadNetTotal:"/>
                    <h:inputText id="headNetTotal" value="#{returnTranHead.returnTranHead.headNetTotal}" title="HeadNetTotal" required="true" requiredMessage="The headNetTotal field is required." />
                    <h:outputText value="Note:"/>
                    <h:inputText id="note" value="#{returnTranHead.returnTranHead.note}" title="Note" />
                    <h:outputText value="ReturnDate (MM/dd/yyyy):"/>
                    <h:inputText id="returnDate" value="#{returnTranHead.returnTranHead.returnDate}" title="ReturnDate" required="true" requiredMessage="The returnDate field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="ReturnTranDetailCollection:"/>
                    <h:selectManyListbox id="returnTranDetailCollection" value="#{returnTranHead.returnTranHead.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].returnTranDetailCollection}" title="ReturnTranDetailCollection" size="6" converter="#{returnTranDetail.converter}" >
                        <f:selectItems value="#{returnTranDetail.returnTranDetailItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="ReturnTranPaymentCollection:"/>
                    <h:selectManyListbox id="returnTranPaymentCollection" value="#{returnTranHead.returnTranHead.jsfcrud_transform[jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method.arrayToList].returnTranPaymentCollection}" title="ReturnTranPaymentCollection" size="6" converter="#{returnTranPayment.converter}" >
                        <f:selectItems value="#{returnTranPayment.returnTranPaymentItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="HeadManagerDiscount:"/>
                    <h:selectOneMenu id="headManagerDiscount" value="#{returnTranHead.returnTranHead.headManagerDiscount}" title="HeadManagerDiscount" >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="OriginalTranHeadId:"/>
                    <h:selectOneMenu id="originalTranHeadId" value="#{returnTranHead.returnTranHead.originalTranHeadId}" title="OriginalTranHeadId" >
                        <f:selectItems value="#{tranHead.tranHeadItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IsTrainingMode:"/>
                    <h:selectOneMenu id="isTrainingMode" value="#{returnTranHead.returnTranHead.isTrainingMode}" title="IsTrainingMode" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranHead.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{returnTranHead.listSetup}" value="Show All ReturnTranHead Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
