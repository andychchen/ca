<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing TranHead</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing TranHead</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranHeadId:"/>
                    <h:outputText value="#{tranHead.tranHead.tranHeadId}" title="TranHeadId" />
                    <h:outputText value="TranDate (MM/dd/yyyy):"/>
                    <h:inputText id="tranDate" value="#{tranHead.tranHead.tranDate}" title="TranDate" required="true" requiredMessage="The tranDate field is required." >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="TranTimestamp (MM/dd/yyyy HH:mm:ss):"/>
                    <h:inputText id="tranTimestamp" value="#{tranHead.tranHead.tranTimestamp}" title="TranTimestamp" >
                        <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                    </h:inputText>
                    <h:outputText value="LineTotalAmt:"/>
                    <h:inputText id="lineTotalAmt" value="#{tranHead.tranHead.lineTotalAmt}" title="LineTotalAmt" required="true" requiredMessage="The lineTotalAmt field is required." />
                    <h:outputText value="LineTaxAmt:"/>
                    <h:inputText id="lineTaxAmt" value="#{tranHead.tranHead.lineTaxAmt}" title="LineTaxAmt" required="true" requiredMessage="The lineTaxAmt field is required." />
                    <h:outputText value="LineDiscountAmt:"/>
                    <h:inputText id="lineDiscountAmt" value="#{tranHead.tranHead.lineDiscountAmt}" title="LineDiscountAmt" required="true" requiredMessage="The lineDiscountAmt field is required." />
                    <h:outputText value="TotalAfterHeadDiscount:"/>
                    <h:inputText id="totalAfterHeadDiscount" value="#{tranHead.tranHead.totalAfterHeadDiscount}" title="TotalAfterHeadDiscount" required="true" requiredMessage="The totalAfterHeadDiscount field is required." />
                    <h:outputText value="TaxAfterHeadDiscount:"/>
                    <h:inputText id="taxAfterHeadDiscount" value="#{tranHead.tranHead.taxAfterHeadDiscount}" title="TaxAfterHeadDiscount" required="true" requiredMessage="The taxAfterHeadDiscount field is required." />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:inputText id="managerDiscountAmt" value="#{tranHead.tranHead.managerDiscountAmt}" title="ManagerDiscountAmt" required="true" requiredMessage="The managerDiscountAmt field is required." />
                    <h:outputText value="BottleRefund:"/>
                    <h:inputText id="bottleRefund" value="#{tranHead.tranHead.bottleRefund}" title="BottleRefund" required="true" requiredMessage="The bottleRefund field is required." />
                    <h:outputText value="HeadNetTotal:"/>
                    <h:inputText id="headNetTotal" value="#{tranHead.tranHead.headNetTotal}" title="HeadNetTotal" required="true" requiredMessage="The headNetTotal field is required." />
                    <h:outputText value="Note:"/>
                    <h:inputText id="note" value="#{tranHead.tranHead.note}" title="Note" />

                    <h:outputText value="HeadManagerDiscount:"/>
                    <h:selectOneMenu id="headManagerDiscount" value="#{tranHead.tranHead.headManagerDiscount}" title="HeadManagerDiscount" >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                    <h:outputText value="UserId:"/>
                    <h:selectOneMenu id="userId" value="#{tranHead.tranHead.userId}" title="UserId" >
                        <f:selectItems value="#{empUsers.empUsersItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>


                    <h:outputText value="IsForReturned:"/>
                    <h:selectOneMenu id="isForReturned" value="#{tranHead.tranHead.isForReturned}" title="IsForReturned" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    
                    <h:outputText value="IsTrainingMode:"/>
                    <h:selectOneMenu id="isTrainingMode" value="#{tranHead.tranHead.isTrainingMode}" title="IsTrainingMode" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                    
                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranHead.edit}" value="Save">
                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranHead.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentTranHead" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranHead.tranHead][tranHead.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranHead.listSetup}" value="Show All TranHead Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
