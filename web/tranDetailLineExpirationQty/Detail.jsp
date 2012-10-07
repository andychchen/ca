<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>TranDetailLineExpirationQty Detail</title>
            <link rel="stylesheet" type="text/css" href="/ca/faces/jsfcrud.css" /><%@ include file="/WEB-INF/jspf/AjaxScripts.jspf" %><script type="text/javascript" src="/ca/faces/jsfcrud.js"></script>
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>TranDetailLineExpirationQty Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="ExpirationDate:"/>
                    <h:outputText value="#{tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetailLineExpirationQtyPK.expirationDate}" title="ExpirationDate" >
                        <f:convertDateTime pattern="MM/dd/yyyy" />
                    </h:outputText>
                    <h:outputText value="Qty:"/>
                    <h:outputText value="#{tranDetailLineExpirationQty.tranDetailLineExpirationQty.qty}" title="Qty" />
                    <h:outputText value="TranDetail:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetail}"/>
                        <h:panelGroup rendered="#{tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetail != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{tranDetail.detailSetup}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{tranDetail.editSetup}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{tranDetail.destroy}">
                                <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty][tranDetailLineExpirationQty.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentTranDetail" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty.tranDetail][tranDetail.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="tranDetailLineExpirationQty"/>
                                <f:param name="jsfcrud.relatedControllerType" value="jsf.TranDetailLineExpirationQtyController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{tranDetailLineExpirationQty.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty][tranDetailLineExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{tranDetailLineExpirationQty.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentTranDetailLineExpirationQty" value="#{jsfcrud_class['jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][tranDetailLineExpirationQty.tranDetailLineExpirationQty][tranDetailLineExpirationQty.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{tranDetailLineExpirationQty.createSetup}" value="New TranDetailLineExpirationQty" />
                <br />
                <h:commandLink action="#{tranDetailLineExpirationQty.listSetup}" value="Show All TranDetailLineExpirationQty Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
