<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Product Items</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Product Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Product Items Found)<br />" rendered="#{product.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{product.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{product.pagingInfo.firstItem + 1}..#{product.pagingInfo.lastItem} of #{product.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{product.first}" value="First" rendered="#{product.pagingInfo.firstItem >= product.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{product.prev}" value="Previous #{product.pagingInfo.batchSize}" rendered="#{product.pagingInfo.firstItem >= product.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{product.next}" value="Next #{product.pagingInfo.batchSize}" rendered="#{product.pagingInfo.lastItem + product.pagingInfo.batchSize <= product.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{product.next}" value="Remaining #{product.pagingInfo.itemCount - product.pagingInfo.lastItem}"
                                   rendered="#{product.pagingInfo.lastItem < product.pagingInfo.itemCount && product.pagingInfo.lastItem + product.pagingInfo.batchSize > product.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{product.last}" value="Last" rendered="#{product.pagingInfo.lastItem + product.pagingInfo.batchSize <= product.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{product.productItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Isbn"/>
                            </f:facet>
                            <h:outputText value=" #{item.isbn}"/>
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
                                <h:outputText value="Qty"/>
                            </f:facet>
                            <h:outputText value=" #{item.qty}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Price"/>
                            </f:facet>
                            <h:outputText value=" #{item.price}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Cost"/>
                            </f:facet>
                            <h:outputText value=" #{item.cost}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LastUpdateDate"/>
                            </f:facet>
                            <h:outputText value="#{item.lastUpdateDate}">
                                <f:convertDateTime pattern="MM/dd/yyyy" />
                            </h:outputText>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Brand"/>
                            </f:facet>
                            <h:outputText value=" #{item.brand}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Promotion"/>
                            </f:facet>
                            <h:outputText value=" #{item.promotion}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Tax"/>
                            </f:facet>
                            <h:outputText value=" #{item.tax}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="IsOrganic"/>
                            </f:facet>
                            <h:outputText value=" #{item.isOrganic}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="HasExpirationDate"/>
                            </f:facet>
                            <h:outputText value=" #{item.hasExpirationDate}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="BrandDiscountExcluded"/>
                            </f:facet>
                            <h:outputText value=" #{item.brandDiscountExcluded}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{product.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{product.editSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{product.destroy}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{product.createSetup}" value="New Product"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
