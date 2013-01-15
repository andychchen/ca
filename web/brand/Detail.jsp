<%@page contentType="text/html"%>

<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Brand Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Brand Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="BrandId:"/>
                    <h:outputText value="#{brand.brand.brandId}" title="BrandId" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{brand.brand.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:outputText value="#{brand.brand.description}" title="Description" />
                    <h:outputText value="Promotion:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{brand.brand.promotion}"/>
                        <h:panelGroup rendered="#{brand.brand.promotion != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][brand.brand][brand.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][brand.brand.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="brand"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.BrandController"/>
                            </h:commandLink>
                            
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="ProductCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty brand.brand.productCollection}" value="(No Items)"/>
                        <h:dataTable value="#{brand.brand.productCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty brand.brand.productCollection}">
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
                                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][brand.brand][brand.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="brand" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.BrandController" />
                                </h:commandLink>
                               
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{brand.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][brand.brand][brand.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{brand.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][brand.brand][brand.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{brand.createSetup}" value="New Brand" />
                <br />
                <h:commandLink action="#{brand.listSetup}" value="Show All Brand Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
