<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing ReturnTranDetail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing ReturnTranDetail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="TranDetailId:"/>
                    <h:outputText value="#{returnTranDetail.returnTranDetail.tranDetailId}" title="TranDetailId" />
                    <h:outputText value="Qty:"/>
                    <h:inputText id="qty" value="#{returnTranDetail.returnTranDetail.qty}" title="Qty" required="true" requiredMessage="The qty field is required." />
                    <h:outputText value="Cost:"/>
                    <h:inputText id="cost" value="#{returnTranDetail.returnTranDetail.cost}" title="Cost" />
                    <h:outputText value="Price:"/>
                    <h:inputText id="price" value="#{returnTranDetail.returnTranDetail.price}" title="Price" required="true" requiredMessage="The price field is required." />
                    <h:outputText value="DiscountAmt:"/>
                    <h:inputText id="discountAmt" value="#{returnTranDetail.returnTranDetail.discountAmt}" title="DiscountAmt" />
                    <h:outputText value="Total:"/>
                    <h:inputText id="total" value="#{returnTranDetail.returnTranDetail.total}" title="Total" />
                    <h:outputText value="TaxAmt:"/>
                    <h:inputText id="taxAmt" value="#{returnTranDetail.returnTranDetail.taxAmt}" title="TaxAmt" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:inputText id="managerDiscountAmt" value="#{returnTranDetail.returnTranDetail.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="SubTotal:"/>
                    <h:inputText id="subTotal" value="#{returnTranDetail.returnTranDetail.subTotal}" title="SubTotal" />
                    <h:outputText value="SubTax:"/>
                    <h:inputText id="subTax" value="#{returnTranDetail.returnTranDetail.subTax}" title="SubTax" />
                    <h:outputText value="DistributedHeadDiscountAmt:"/>
                    <h:inputText id="distributedHeadDiscountAmt" value="#{returnTranDetail.returnTranDetail.distributedHeadDiscountAmt}" title="DistributedHeadDiscountAmt" />
                    <h:outputText value="SubSubTotal:"/>
                    <h:inputText id="subSubTotal" value="#{returnTranDetail.returnTranDetail.subSubTotal}" title="SubSubTotal" />
                    <h:outputText value="SubSubTax:"/>
                    <h:inputText id="subSubTax" value="#{returnTranDetail.returnTranDetail.subSubTax}" title="SubSubTax" />
                    <h:outputText value="LineNetTotal:"/>
                    <h:inputText id="lineNetTotal" value="#{returnTranDetail.returnTranDetail.lineNetTotal}" title="LineNetTotal" />
                    <h:outputText value="LineManagerDiscount:"/>
                    <h:selectOneMenu id="lineManagerDiscount" value="#{returnTranDetail.returnTranDetail.lineManagerDiscount}" title="LineManagerDiscount" >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Discount:"/>
                    <h:selectOneMenu id="discount" value="#{returnTranDetail.returnTranDetail.discount}" title="Discount" >
                        <f:selectItems value="#{discount.discountItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Isbn:"/>
                    <h:selectOneMenu id="isbn" value="#{returnTranDetail.returnTranDetail.isbn}" title="Isbn" required="true" requiredMessage="The isbn field is required." >
                        <f:selectItems value="#{product.productItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Promotion:"/>
                    <h:selectOneMenu id="promotion" value="#{returnTranDetail.returnTranDetail.promotion}" title="Promotion" >
                        <f:selectItems value="#{promotion.promotionItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="TranHeadId:"/>
                    <h:selectOneMenu id="tranHeadId" value="#{returnTranDetail.returnTranDetail.tranHeadId}" title="TranHeadId" required="true" requiredMessage="The tranHeadId field is required." >
                        <f:selectItems value="#{returnTranHead.returnTranHeadItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Tax:"/>
                    <h:selectOneMenu id="tax" value="#{returnTranDetail.returnTranDetail.tax}" title="Tax" >
                        <f:selectItems value="#{tax.taxItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="IsReturned:"/>
                    <h:selectOneMenu id="isReturned" value="#{returnTranDetail.returnTranDetail.isReturned}" title="IsReturned" >
                        <f:selectItems value="#{yesNo.yesNoItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="ReturnTranDetailLineExpirationQtyCollection:"/>
                    <h:outputText escape="false" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getCollectionAsString'][returnTranDetail.returnTranDetail.returnTranDetailLineExpirationQtyCollection == null ? jsfcrud_null : returnTranDetail.returnTranDetail.returnTranDetailLineExpirationQtyCollection].jsfcrud_invoke}" title="ReturnTranDetailLineExpirationQtyCollection" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranDetail.edit}" value="Save">
                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{returnTranDetail.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetail.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{returnTranDetail.listSetup}" value="Show All ReturnTranDetail Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
