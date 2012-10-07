<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing YesNo Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing YesNo Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No YesNo Items Found)<br />" rendered="#{yesNo.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{yesNo.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{yesNo.pagingInfo.firstItem + 1}..#{yesNo.pagingInfo.lastItem} of #{yesNo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{yesNo.prev}" value="Previous #{yesNo.pagingInfo.batchSize}" rendered="#{yesNo.pagingInfo.firstItem >= yesNo.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{yesNo.next}" value="Next #{yesNo.pagingInfo.batchSize}" rendered="#{yesNo.pagingInfo.lastItem + yesNo.pagingInfo.batchSize <= yesNo.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{yesNo.next}" value="Remaining #{yesNo.pagingInfo.itemCount - yesNo.pagingInfo.lastItem}"
                                   rendered="#{yesNo.pagingInfo.lastItem < yesNo.pagingInfo.itemCount && yesNo.pagingInfo.lastItem + yesNo.pagingInfo.batchSize > yesNo.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{yesNo.yesNoItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][yesNo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{yesNo.editSetup}">
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][yesNo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{yesNo.destroy}">
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][yesNo.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{yesNo.createSetup}" value="New YesNo"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
