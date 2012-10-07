<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Promotion Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Promotion Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Promotion Items Found)<br />" rendered="#{promotion.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{promotion.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{promotion.pagingInfo.firstItem + 1}..#{promotion.pagingInfo.lastItem} of #{promotion.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{promotion.prev}" value="Previous #{promotion.pagingInfo.batchSize}" rendered="#{promotion.pagingInfo.firstItem >= promotion.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{promotion.next}" value="Next #{promotion.pagingInfo.batchSize}" rendered="#{promotion.pagingInfo.lastItem + promotion.pagingInfo.batchSize <= promotion.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{promotion.next}" value="Remaining #{promotion.pagingInfo.itemCount - promotion.pagingInfo.lastItem}"
                                   rendered="#{promotion.pagingInfo.lastItem < promotion.pagingInfo.itemCount && promotion.pagingInfo.lastItem + promotion.pagingInfo.batchSize > promotion.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{promotion.promotionItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PromotionId"/>
                            </f:facet>
                            <h:outputText value=" #{item.promotionId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="StartDate"/>
                            </f:facet>
                            <h:outputText value="#{item.startDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="EndDate"/>
                            </f:facet>
                            <h:outputText value="#{item.endDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="YearlyMonth"/>
                            </f:facet>
                            <h:outputText value=" #{item.yearlyMonth}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="MonthlyWeek"/>
                            </f:facet>
                            <h:outputText value=" #{item.monthlyWeek}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Discount"/>
                            </f:facet>
                            <h:outputText value=" #{item.discount}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="WeeklyDay"/>
                            </f:facet>
                            <h:outputText value=" #{item.weeklyDay}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsEnabled"/>
                            </f:facet>
                            <h:outputText value=" #{item.isEnabled}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsAppliedToAll"/>
                            </f:facet>
                            <h:outputText value=" #{item.isAppliedToAll}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{promotion.editSetup}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{promotion.destroy}">
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{promotion.createSetup}" value="New Promotion"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
