<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Brand Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Brand Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Brand Items Found)<br />" rendered="#{brand.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{brand.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{brand.pagingInfo.firstItem + 1}..#{brand.pagingInfo.lastItem} of #{brand.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{brand.first}" value="First" rendered="#{brand.pagingInfo.firstItem >= brand.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{brand.prev}" value="Previous #{brand.pagingInfo.batchSize}" rendered="#{brand.pagingInfo.firstItem >= brand.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{brand.next}" value="Next #{brand.pagingInfo.batchSize}" rendered="#{brand.pagingInfo.lastItem + brand.pagingInfo.batchSize <= brand.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{brand.next}" value="Remaining #{brand.pagingInfo.itemCount - brand.pagingInfo.lastItem}"
                                   rendered="#{brand.pagingInfo.lastItem < brand.pagingInfo.itemCount && brand.pagingInfo.lastItem + brand.pagingInfo.batchSize > brand.pagingInfo.itemCount}"/>
                    <h:commandLink action="#{brand.last}" value="Last" rendered="#{brand.pagingInfo.lastItem + brand.pagingInfo.batchSize <= brand.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{brand.brandItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="BrandId"/>
                            </f:facet>
                            <h:outputText value=" #{item.brandId}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Description"/>
                            </f:facet>
                            <h:outputText value=" #{item.description}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Promotion"/>
                            </f:facet>
                            <h:outputText value=" #{item.promotion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{brand.detailSetup}">
                                <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{brand.editSetup}">
                                <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{brand.destroy}">
                                <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][brand.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{brand.createSetup}" value="New Brand"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
