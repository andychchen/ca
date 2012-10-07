<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New TranDetail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New TranDetail</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{tranDetail.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Qty:"/>
                    <h:inputText id="qty" value="#{tranDetail.tranDetail.qty}" title="Qty" required="true" requiredMessage="The qty field is required." />
                    <h:outputText value="Cost:"/>
                    <h:inputText id="cost" value="#{tranDetail.tranDetail.cost}" title="Cost" />
                    <h:outputText value="Price:"/>
                    <h:inputText id="price" value="#{tranDetail.tranDetail.price}" title="Price" required="true" requiredMessage="The price field is required." />
                    <h:outputText value="DiscountAmt:"/>
                    <h:inputText id="discountAmt" value="#{tranDetail.tranDetail.discountAmt}" title="DiscountAmt" />
                    <h:outputText value="Total:"/>
                    <h:inputText id="total" value="#{tranDetail.tranDetail.total}" title="Total" />
                    <h:outputText value="TaxAmt:"/>
                    <h:inputText id="taxAmt" value="#{tranDetail.tranDetail.taxAmt}" title="TaxAmt" />
                    <h:outputText value="ManagerDiscountAmt:"/>
                    <h:inputText id="managerDiscountAmt" value="#{tranDetail.tranDetail.managerDiscountAmt}" title="ManagerDiscountAmt" />
                    <h:outputText value="SubTotal:"/>
                    <h:inputText id="subTotal" value="#{tranDetail.tranDetail.subTotal}" title="SubTotal" />
                    <h:outputText value="SubTax:"/>
                    <h:inputText id="subTax" value="#{tranDetail.tranDetail.subTax}" title="SubTax" />
                    <h:outputText value="DistributedHeadDiscountAmt:"/>
                    <h:inputText id="distributedHeadDiscountAmt" value="#{tranDetail.tranDetail.distributedHeadDiscountAmt}" title="DistributedHeadDiscountAmt" />
                    <h:outputText value="SubSubTotal:"/>
                    <h:inputText id="subSubTotal" value="#{tranDetail.tranDetail.subSubTotal}" title="SubSubTotal" />
                    <h:outputText value="SubSubTax:"/>
                    <h:inputText id="subSubTax" value="#{tranDetail.tranDetail.subSubTax}" title="SubSubTax" />
                    <h:outputText value="LineNetTotal:"/>
                    <h:inputText id="lineNetTotal" value="#{tranDetail.tranDetail.lineNetTotal}" title="LineNetTotal" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranDetail.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{tranDetail.listSetup}" value="Show All TranDetail Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
