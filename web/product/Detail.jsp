<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Product Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Product Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Isbn:"/>
                    <h:outputText value="#{product.product.isbn}" title="Isbn" />
                    <h:outputText value="Name:"/>
                    <h:outputText value="#{product.product.name}" title="Name" />
                    <h:outputText value="Description:"/>
                    <h:outputText value="#{product.product.description}" title="Description" />
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{product.product.qty}" title="Qty" />
                    <h:outputText value="Price:"/>
                    <h:outputText value="#{product.product.price}" title="Price" />
                    <h:outputText value="Cost:"/>
                    <h:outputText value="#{product.product.cost}" title="Cost" />
                    <h:outputText value="LastUpdateDate:"/>
                    <h:outputText value="#{product.product.lastUpdateDate}" title="LastUpdateDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Brand:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.brand}"/>
                        <h:panelGroup rendered="#{product.product.brand != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{brand.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentBrand" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.brand][brand.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>

                            
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Promotion:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.promotion}"/>
                        <h:panelGroup rendered="#{product.product.promotion != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{promotion.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentPromotion" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.promotion][promotion.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>


                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Tax:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.tax}"/>
                        <h:panelGroup rendered="#{product.product.tax != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tax.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTax" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.tax][tax.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>


                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="IsOrganic:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.isOrganic}"/>
                        <h:panelGroup rendered="#{product.product.isOrganic != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.isOrganic][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>


                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="HasExpirationDate:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.hasExpirationDate}"/>
                        <h:panelGroup rendered="#{product.product.hasExpirationDate != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.hasExpirationDate][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>

                            
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="BrandDiscountExcluded:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{product.product.brandDiscountExcluded}"/>
                        <h:panelGroup rendered="#{product.product.brandDiscountExcluded != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{yesNo.detailSetup}">
                                <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentYesNo" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product.brandDiscountExcluded][yesNo.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="product"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController"/>
                            </h:commandLink>

                            
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    
                    <h:outputText value="TranDetailCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty product.product.tranDetailCollection}" value="(No Items)"/>
                        <h:dataTable value="#{product.product.tranDetailCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty product.product.tranDetailCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="TranDetailId"/>
                                </f:facet>
                                <h:outputText value=" #{item.tranDetailId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Qty"/>
                                </f:facet>
                                <h:outputText value=" #{item.qty}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Cost"/>
                                </f:facet>
                                <h:outputText value=" #{item.cost}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Price"/>
                                </f:facet>
                                <h:outputText value=" #{item.price}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="DiscountAmt"/>
                                </f:facet>
                                <h:outputText value=" #{item.discountAmt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Total"/>
                                </f:facet>
                                <h:outputText value=" #{item.total}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="TaxAmt"/>
                                </f:facet>
                                <h:outputText value=" #{item.taxAmt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ManagerDiscountAmt"/>
                                </f:facet>
                                <h:outputText value=" #{item.managerDiscountAmt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="SubTotal"/>
                                </f:facet>
                                <h:outputText value=" #{item.subTotal}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="SubTax"/>
                                </f:facet>
                                <h:outputText value=" #{item.subTax}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="DistributedHeadDiscountAmt"/>
                                </f:facet>
                                <h:outputText value=" #{item.distributedHeadDiscountAmt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="SubSubTotal"/>
                                </f:facet>
                                <h:outputText value=" #{item.subSubTotal}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="SubSubTax"/>
                                </f:facet>
                                <h:outputText value=" #{item.subSubTax}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="LineNetTotal"/>
                                </f:facet>
                                <h:outputText value=" #{item.lineNetTotal}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="LineManagerDiscount"/>
                                </f:facet>
                                <h:outputText value=" #{item.lineManagerDiscount}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Isbn"/>
                                </f:facet>
                                <h:outputText value=" #{item.isbn}"/>
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
                                    <h:outputText value="TranHeadId"/>
                                </f:facet>
                                <h:outputText value=" #{item.tranHeadId}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{tranDetail.detailSetup}">
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][tranDetail.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="product" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController" />
                                </h:commandLink>

                                
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    
 
                    <h:outputText value="ProductExpirationQtyCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty product.product.productExpirationQtyCollection}" value="(No Items)"/>
                        <h:dataTable value="#{product.product.productExpirationQtyCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty product.product.productExpirationQtyCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="ExpirationDate"/>
                                </f:facet>
                                <h:outputText value=" #{item.productExpirationQtyPK.expirationDate}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Qty"/>
                                </f:facet>
                                <h:outputText value=" #{item.qty}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Product"/>
                                </f:facet>
                                <h:outputText value=" #{item.product}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{productExpirationQty.detailSetup}">
                                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentProductExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][productExpirationQty.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="product" />
                                    <f:param name="jsfcrud.relatedControllerType" value="jsf.ProductController" />
                                </h:commandLink>


                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{product.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{product.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentProduct" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][product.product][product.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{product.createSetup}" value="New Product" />
                <br />
                <h:commandLink action="#{product.listSetup}" value="Show All Product Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
