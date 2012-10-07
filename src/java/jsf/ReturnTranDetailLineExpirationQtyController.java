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
import jpa.controllers.ReturnTranDetailLineExpirationQtyJpaController;
import jpa.entities.ReturnTranDetailLineExpirationQty;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.entities.ReturnTranDetailLineExpirationQtyPK;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class ReturnTranDetailLineExpirationQtyController {

    public ReturnTranDetailLineExpirationQtyController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ReturnTranDetailLineExpirationQtyJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranDetailLineExpirationQtyJpa");
        pagingInfo = new PagingInfo();
        converter = new ReturnTranDetailLineExpirationQtyConverter();
    }
    private ReturnTranDetailLineExpirationQty returnTranDetailLineExpirationQty = null;
    private List<ReturnTranDetailLineExpirationQty> returnTranDetailLineExpirationQtyItems = null;
    private ReturnTranDetailLineExpirationQtyJpaController jpaController = null;
    private ReturnTranDetailLineExpirationQtyConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getReturnTranDetailLineExpirationQtyCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getReturnTranDetailLineExpirationQtyItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranDetailLineExpirationQtyEntities(), false);
    }

    public SelectItem[] getReturnTranDetailLineExpirationQtyItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranDetailLineExpirationQtyEntities(), true);
    }

    public ReturnTranDetailLineExpirationQty getReturnTranDetailLineExpirationQty() {
        if (returnTranDetailLineExpirationQty == null) {
            returnTranDetailLineExpirationQty = (ReturnTranDetailLineExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranDetailLineExpirationQty", converter, null);
        }
        if (returnTranDetailLineExpirationQty == null) {
            returnTranDetailLineExpirationQty = new ReturnTranDetailLineExpirationQty();
        }
        return returnTranDetailLineExpirationQty;
    }

    public String listSetup() {
        reset(true);
        return "returnTranDetailLineExpirationQty_list";
    }

    public String createSetup() {
        reset(false);
        returnTranDetailLineExpirationQty = new ReturnTranDetailLineExpirationQty();
        returnTranDetailLineExpirationQty.setReturnTranDetailLineExpirationQtyPK(new ReturnTranDetailLineExpirationQtyPK());
        return "returnTranDetailLineExpirationQty_create";
    }

    public String create() {
        try {
            jpaController.create(returnTranDetailLineExpirationQty);
            JsfUtil.addSuccessMessage("ReturnTranDetailLineExpirationQty was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("returnTranDetailLineExpirationQty_detail");
    }

    public String editSetup() {
        return scalarSetup("returnTranDetailLineExpirationQty_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        returnTranDetailLineExpirationQty = (ReturnTranDetailLineExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranDetailLineExpirationQty", converter, null);
        if (returnTranDetailLineExpirationQty == null) {
            String requestReturnTranDetailLineExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetailLineExpirationQty");
            JsfUtil.addErrorMessage("The returnTranDetailLineExpirationQty with id " + requestReturnTranDetailLineExpirationQtyString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        returnTranDetailLineExpirationQty.getReturnTranDetailLineExpirationQtyPK().setTranDetailId(returnTranDetailLineExpirationQty.getReturnTranDetail().getTranDetailId());
        String returnTranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranDetailLineExpirationQty);
        String currentReturnTranDetailLineExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetailLineExpirationQty");
        if (returnTranDetailLineExpirationQtyString == null || returnTranDetailLineExpirationQtyString.length() == 0 || !returnTranDetailLineExpirationQtyString.equals(currentReturnTranDetailLineExpirationQtyString)) {
            String outcome = editSetup();
            if ("returnTranDetailLineExpirationQty_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit returnTranDetailLineExpirationQty. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(returnTranDetailLineExpirationQty);
            JsfUtil.addSuccessMessage("ReturnTranDetailLineExpirationQty was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetailLineExpirationQty");
        ReturnTranDetailLineExpirationQtyPK id = converter.getId(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ReturnTranDetailLineExpirationQty was successfully deleted.");
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

    public List<ReturnTranDetailLineExpirationQty> getReturnTranDetailLineExpirationQtyItems() {
        if (returnTranDetailLineExpirationQtyItems == null) {
            getPagingInfo();
            returnTranDetailLineExpirationQtyItems = jpaController.findReturnTranDetailLineExpirationQtyEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return returnTranDetailLineExpirationQtyItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "returnTranDetailLineExpirationQty_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "returnTranDetailLineExpirationQty_list";
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
        returnTranDetailLineExpirationQty = null;
        returnTranDetailLineExpirationQtyItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ReturnTranDetailLineExpirationQty newReturnTranDetailLineExpirationQty = new ReturnTranDetailLineExpirationQty();
        newReturnTranDetailLineExpirationQty.setReturnTranDetailLineExpirationQtyPK(new ReturnTranDetailLineExpirationQtyPK());
        String newReturnTranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, newReturnTranDetailLineExpirationQty);
        String returnTranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranDetailLineExpirationQty);
        if (!newReturnTranDetailLineExpirationQtyString.equals(returnTranDetailLineExpirationQtyString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
