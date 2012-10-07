/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import jpa.controllers.ReturnTranPaymentJpaController;
import jpa.entities.ReturnTranPayment;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class ReturnTranPaymentController {

    public ReturnTranPaymentController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ReturnTranPaymentJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranPaymentJpa");
        pagingInfo = new PagingInfo();
        converter = new ReturnTranPaymentConverter();
    }
    private ReturnTranPayment returnTranPayment = null;
    private List<ReturnTranPayment> returnTranPaymentItems = null;
    private ReturnTranPaymentJpaController jpaController = null;
    private ReturnTranPaymentConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getReturnTranPaymentCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getReturnTranPaymentItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranPaymentEntities(), false);
    }

    public SelectItem[] getReturnTranPaymentItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranPaymentEntities(), true);
    }

    public ReturnTranPayment getReturnTranPayment() {
        if (returnTranPayment == null) {
            returnTranPayment = (ReturnTranPayment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranPayment", converter, null);
        }
        if (returnTranPayment == null) {
            returnTranPayment = new ReturnTranPayment();
        }
        return returnTranPayment;
    }

    public String listSetup() {
        reset(true);
        return "returnTranPayment_list";
    }

    public String createSetup() {
        reset(false);
        returnTranPayment = new ReturnTranPayment();
        return "returnTranPayment_create";
    }

    public String create() {
        try {
            jpaController.create(returnTranPayment);
            JsfUtil.addSuccessMessage("ReturnTranPayment was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("returnTranPayment_detail");
    }

    public String editSetup() {
        return scalarSetup("returnTranPayment_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        returnTranPayment = (ReturnTranPayment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranPayment", converter, null);
        if (returnTranPayment == null) {
            String requestReturnTranPaymentString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranPayment");
            JsfUtil.addErrorMessage("The returnTranPayment with id " + requestReturnTranPaymentString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String returnTranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranPayment);
        String currentReturnTranPaymentString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranPayment");
        if (returnTranPaymentString == null || returnTranPaymentString.length() == 0 || !returnTranPaymentString.equals(currentReturnTranPaymentString)) {
            String outcome = editSetup();
            if ("returnTranPayment_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit returnTranPayment. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(returnTranPayment);
            JsfUtil.addSuccessMessage("ReturnTranPayment was successfully updated.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return listSetup();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String destroy() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranPayment");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ReturnTranPayment was successfully deleted.");
        } catch (NonexistentEntityException ne) {
            JsfUtil.addErrorMessage(ne.getLocalizedMessage());
            return relatedOrListOutcome();
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<ReturnTranPayment> getReturnTranPaymentItems() {
        if (returnTranPaymentItems == null) {
            getPagingInfo();
            returnTranPaymentItems = jpaController.findReturnTranPaymentEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return returnTranPaymentItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "returnTranPayment_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "returnTranPayment_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        returnTranPayment = null;
        returnTranPaymentItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ReturnTranPayment newReturnTranPayment = new ReturnTranPayment();
        String newReturnTranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, newReturnTranPayment);
        String returnTranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranPayment);
        if (!newReturnTranPaymentString.equals(returnTranPaymentString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
