<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Promotion</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Promotion</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PromotionId:"/>
                    <h:outputText value="#{promotion.promotion.promotionId}" title="PromotionId" />
                    <h:outputText value="Name:"/>
                    <h:inputText id="name" value="#{promotion.promotion.name}" title="Name" />
                    <h:outputText value="StartDate (MM/dd/yyyy):"/>
                    <h:inputText id="startDate" value="#{promotion.promotion.startDate}" title="StartDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="EndDate (MM/dd/yyyy):"/>
                    <h:inputText id="endDate" value="#{promotion.promotion.endDate}" title="EndDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:inputText>
                    <h:outputText value="YearlyMonth:"/>
                    <h:inputText id="yearlyMonth" value="#{promotion.promotion.yearlyMonth}" title="YearlyMonth" />
                    <h:outputText value="MonthlyWeek:"/>
                    <h:inputText id="monthlyWeek" value="#{promotion.promotion.monthlyWeek}" title="MonthlyWeek" />
 
                    <h:outputText value="Discount:"/>
                    <h:selectOneMenu id="discount" value="#{promotion.promotion.discount}" title="Discount" required="true" requiredMessage="The discount field is required." >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="WeeklyDay:"/>
                    <h:selectOneMenu id="weeklyDay" value="#{promotion.promotion.weeklyDay}" title="WeeklyDay" >
                        <f:selectItems value="#{weekDays.weekDaysItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IsEnabled:"/>
                    <h:selectOneMenu id="isEnabled" value="#{promotion.promotion.isEnabled}" title="IsEnabled" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IsAppliedToAll:"/>
                    <h:selectOneMenu id="isAppliedToAll" value="#{promotion.promotion.isAppliedToAll}" title="IsAppliedToAll" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>

                </h:panelGrid>
                <br />
                <h:commandLink action="#{promotion.edit}" value="Save">
                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{promotion.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][promotion.promotion][promotion.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{promotion.listSetup}" value="Show All Promotion Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
