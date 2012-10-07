<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Tax Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Tax Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Tax Items Found)<br />" rendered="#{tax.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{tax.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{tax.pagingInfo.firstItem + 1}..#{tax.pagingInfo.lastItem} of #{tax.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tax.prev}" value="Previous #{tax.pagingInfo.batchSize}" rendered="#{tax.pagingInfo.firstItem >= tax.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{tax.next}" value="Next #{tax.pagingInfo.batchSize}" rendered="#{tax.pagingInfo.lastItem + tax.pagingInfo.batchSize <= tax.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{tax.next}" value="Remaining #{tax.pagingInfo.itemCount - tax.pagingInfo.lastItem}"
                                   rendered="#{tax.pagingInfo.lastItem < tax.pagingInfo.itemCount && tax.pagingInfo.lastItem + tax.pagingInfo.batchSize > tax.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{tax.taxItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="TaxId"/>
                            </f:facet>
                            <h:outputText value=" #{item.taxId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Rate"/>
                            </f:facet>
                            <h:outputText value=" #{item.rate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{tax.detailSetup}">
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tax.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tax.editSetup}">
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tax.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tax.destroy}">
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tax.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{tax.createSetup}" value="New Tax"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
