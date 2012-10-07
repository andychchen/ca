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
import jpa.controllers.TranPaymentJpaController;
import jpa.entities.TranPayment;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class TranPaymentController {

    public TranPaymentController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (TranPaymentJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranPaymentJpa");
        pagingInfo = new PagingInfo();
        converter = new TranPaymentConverter();
    }
    private TranPayment tranPayment = null;
    private List<TranPayment> tranPaymentItems = null;
    private TranPaymentJpaController jpaController = null;
    private TranPaymentConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getTranPaymentCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getTranPaymentItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findTranPaymentEntities(), false);
    }

    public SelectItem[] getTranPaymentItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findTranPaymentEntities(), true);
    }

    public TranPayment getTranPayment() {
        if (tranPayment == null) {
            tranPayment = (TranPayment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranPayment", converter, null);
        }
        if (tranPayment == null) {
            tranPayment = new TranPayment();
        }
        return tranPayment;
    }

    public String listSetup() {
        reset(true);
        return "tranPayment_list";
    }

    public String createSetup() {
        reset(false);
        tranPayment = new TranPayment();
        return "tranPayment_create";
    }

    public String create() {
        try {
            jpaController.create(tranPayment);
            JsfUtil.addSuccessMessage("TranPayment was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("tranPayment_detail");
    }

    public String editSetup() {
        return scalarSetup("tranPayment_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tranPayment = (TranPayment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranPayment", converter, null);
        if (tranPayment == null) {
            String requestTranPaymentString = JsfUtil.getRequestParameter("jsfcrud.currentTranPayment");
            JsfUtil.addErrorMessage("The tranPayment with id " + requestTranPaymentString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String tranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranPayment);
        String currentTranPaymentString = JsfUtil.getRequestParameter("jsfcrud.currentTranPayment");
        if (tranPaymentString == null || tranPaymentString.length() == 0 || !tranPaymentString.equals(currentTranPaymentString)) {
            String outcome = editSetup();
            if ("tranPayment_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tranPayment. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(tranPayment);
            JsfUtil.addSuccessMessage("TranPayment was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTranPayment");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("TranPayment was successfully deleted.");
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

    public List<TranPayment> getTranPaymentItems() {
        if (tranPaymentItems == null) {
            getPagingInfo();
            tranPaymentItems = jpaController.findTranPaymentEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return tranPaymentItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tranPayment_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tranPayment_list";
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
        tranPayment = null;
        tranPaymentItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        TranPayment newTranPayment = new TranPayment();
        String newTranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTranPayment);
        String tranPaymentString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranPayment);
        if (!newTranPaymentString.equals(tranPaymentString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
