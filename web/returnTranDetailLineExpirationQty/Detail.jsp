<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ReturnTranDetailLineExpirationQty Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ReturnTranDetailLineExpirationQty Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ExpirationDate:"/>
                    <h:outputText value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQtyPK.expirationDate}" title="ExpirationDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.qty}" title="Qty" />
                    <h:outputText value="ReturnTranDetail:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail}"/>
                        <h:panelGroup rendered="#{returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{returnTranDetail.detailSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{returnTranDetail.editSetup}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{returnTranDetail.destroy}">
                                <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentReturnTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty.returnTranDetail][returnTranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="returnTranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.ReturnTranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentReturnTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][returnTranDetailLineExpirationQty.returnTranDetailLineExpirationQty][returnTranDetailLineExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.createSetup}" value="New ReturnTranDetailLineExpirationQty" />
                <br />
                <h:commandLink action="#{returnTranDetailLineExpirationQty.listSetup}" value="Show All ReturnTranDetailLineExpirationQty Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
