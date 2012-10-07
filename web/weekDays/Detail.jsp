<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>WeekDays Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>WeekDays Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{weekDays.weekDays.name}" title="Name" />
                    <h:outputText value="Day:"/>
                    <h:outputText value="#{weekDays.weekDays.day}" title="Day" />
                    <h:outputText value="PromotionCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty weekDays.weekDays.promotionCollection}" value="(No Items)"/>
                        <h:dataTable value="#{weekDays.weekDays.promotionCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty weekDays.weekDays.promotionCollection}">
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
                                    <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][weekDays.weekDays][weekDays.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="weekDays" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.WeekDaysController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{promotion.editSetup}">
                                    <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][weekDays.weekDays][weekDays.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="weekDays" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.WeekDaysController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{promotion.destroy}">
                                    <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][weekDays.weekDays][weekDays.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][promotion.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="weekDays" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.WeekDaysController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{weekDays.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][weekDays.weekDays][weekDays.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{weekDays.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][weekDays.weekDays][weekDays.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{weekDays.createSetup}" value="New WeekDays" />
                <br />
                <h:commandLink action="#{weekDays.listSetup}" value="Show All WeekDays Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
