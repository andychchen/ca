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
import jpa.controllers.PaymentTypeJpaController;
import jpa.entities.PaymentType;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class PaymentTypeController {

    public PaymentTypeController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (PaymentTypeJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "paymentTypeJpa");
        pagingInfo = new PagingInfo();
        converter = new PaymentTypeConverter();
    }
    private PaymentType paymentType = null;
    private List<PaymentType> paymentTypeItems = null;
    private PaymentTypeJpaController jpaController = null;
    private PaymentTypeConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getPaymentTypeCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getPaymentTypeItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findPaymentTypeEntities(), false);
    }

    public SelectItem[] getPaymentTypeItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findPaymentTypeEntities(), true);
    }

    public PaymentType getPaymentType() {
        if (paymentType == null) {
            paymentType = (PaymentType) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPaymentType", converter, null);
        }
        if (paymentType == null) {
            paymentType = new PaymentType();
        }
        return paymentType;
    }

    public String listSetup() {
        reset(true);
        return "paymentType_list";
    }

    public String createSetup() {
        reset(false);
        paymentType = new PaymentType();
        return "paymentType_create";
    }

    public String create() {
        try {
            jpaController.create(paymentType);
            JsfUtil.addSuccessMessage("PaymentType was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("paymentType_detail");
    }

    public String editSetup() {
        return scalarSetup("paymentType_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        paymentType = (PaymentType) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPaymentType", converter, null);
        if (paymentType == null) {
            String requestPaymentTypeString = JsfUtil.getRequestParameter("jsfcrud.currentPaymentType");
            JsfUtil.addErrorMessage("The paymentType with id " + requestPaymentTypeString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String paymentTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, paymentType);
        String currentPaymentTypeString = JsfUtil.getRequestParameter("jsfcrud.currentPaymentType");
        if (paymentTypeString == null || paymentTypeString.length() == 0 || !paymentTypeString.equals(currentPaymentTypeString)) {
            String outcome = editSetup();
            if ("paymentType_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit paymentType. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(paymentType);
            JsfUtil.addSuccessMessage("PaymentType was successfully updated.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPaymentType");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("PaymentType was successfully deleted.");
        } catch (IllegalOrphanException oe) {
            JsfUtil.addErrorMessages(oe.getMessages());
            return null;
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

    public List<PaymentType> getPaymentTypeItems() {
        if (paymentTypeItems == null) {
            getPagingInfo();
            paymentTypeItems = jpaController.findPaymentTypeEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return paymentTypeItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "paymentType_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "paymentType_list";
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
        paymentType = null;
        paymentTypeItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        PaymentType newPaymentType = new PaymentType();
        String newPaymentTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPaymentType);
        String paymentTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, paymentType);
        if (!newPaymentTypeString.equals(paymentTypeString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
