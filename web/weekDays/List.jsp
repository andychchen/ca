<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing WeekDays Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing WeekDays Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No WeekDays Items Found)<br />" rendered="#{weekDays.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{weekDays.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{weekDays.pagingInfo.firstItem + 1}..#{weekDays.pagingInfo.lastItem} of #{weekDays.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{weekDays.prev}" value="Previous #{weekDays.pagingInfo.batchSize}" rendered="#{weekDays.pagingInfo.firstItem >= weekDays.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{weekDays.next}" value="Next #{weekDays.pagingInfo.batchSize}" rendered="#{weekDays.pagingInfo.lastItem + weekDays.pagingInfo.batchSize <= weekDays.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{weekDays.next}" value="Remaining #{weekDays.pagingInfo.itemCount - weekDays.pagingInfo.lastItem}"
                                   rendered="#{weekDays.pagingInfo.lastItem < weekDays.pagingInfo.itemCount && weekDays.pagingInfo.lastItem + weekDays.pagingInfo.batchSize > weekDays.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{weekDays.weekDaysItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Day"/>
                            </f:facet>
                            <h:outputText value=" #{item.day}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{weekDays.detailSetup}">
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][weekDays.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{weekDays.editSetup}">
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][weekDays.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{weekDays.destroy}">
                                <f:param name="jsfcrud.currentWeekDays" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][weekDays.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{weekDays.createSetup}" value="New WeekDays"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
