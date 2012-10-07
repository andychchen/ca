<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing EmpUsers Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing EmpUsers Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No EmpUsers Items Found)<br />" rendered="#{empUsers.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{empUsers.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{empUsers.pagingInfo.firstItem + 1}..#{empUsers.pagingInfo.lastItem} of #{empUsers.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{empUsers.prev}" value="Previous #{empUsers.pagingInfo.batchSize}" rendered="#{empUsers.pagingInfo.firstItem >= empUsers.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{empUsers.next}" value="Next #{empUsers.pagingInfo.batchSize}" rendered="#{empUsers.pagingInfo.lastItem + empUsers.pagingInfo.batchSize <= empUsers.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{empUsers.next}" value="Remaining #{empUsers.pagingInfo.itemCount - empUsers.pagingInfo.lastItem}"
                                   rendered="#{empUsers.pagingInfo.lastItem < empUsers.pagingInfo.itemCount && empUsers.pagingInfo.lastItem + empUsers.pagingInfo.batchSize > empUsers.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{empUsers.empUsersItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value=" #{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="UserName"/>
                            </f:facet>
                            <h:outputText value=" #{item.userName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Role"/>
                            </f:facet>
                            <h:outputText value=" #{item.role}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{empUsers.detailSetup}">
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][empUsers.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{empUsers.editSetup}">
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][empUsers.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{empUsers.destroy}">
                                <f:param name="jsfcrud.currentEmpUsers" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][empUsers.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{empUsers.createSetup}" value="New EmpUsers"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
