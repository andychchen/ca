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
import jpa.controllers.ReturnTranDetailJpaController;
import jpa.entities.ReturnTranDetail;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class ReturnTranDetailController {

    public ReturnTranDetailController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ReturnTranDetailJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranDetailJpa");
        pagingInfo = new PagingInfo();
        converter = new ReturnTranDetailConverter();
    }
    private ReturnTranDetail returnTranDetail = null;
    private List<ReturnTranDetail> returnTranDetailItems = null;
    private ReturnTranDetailJpaController jpaController = null;
    private ReturnTranDetailConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getReturnTranDetailCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getReturnTranDetailItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranDetailEntities(), false);
    }

    public SelectItem[] getReturnTranDetailItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranDetailEntities(), true);
    }

    public ReturnTranDetail getReturnTranDetail() {
        if (returnTranDetail == null) {
            returnTranDetail = (ReturnTranDetail) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranDetail", converter, null);
        }
        if (returnTranDetail == null) {
            returnTranDetail = new ReturnTranDetail();
        }
        return returnTranDetail;
    }

    public String listSetup() {
        reset(true);
        return "returnTranDetail_list";
    }

    public String createSetup() {
        reset(false);
        returnTranDetail = new ReturnTranDetail();
        return "returnTranDetail_create";
    }

    public String create() {
        try {
            jpaController.create(returnTranDetail);
            JsfUtil.addSuccessMessage("ReturnTranDetail was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("returnTranDetail_detail");
    }

    public String editSetup() {
        return scalarSetup("returnTranDetail_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        returnTranDetail = (ReturnTranDetail) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranDetail", converter, null);
        if (returnTranDetail == null) {
            String requestReturnTranDetailString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetail");
            JsfUtil.addErrorMessage("The returnTranDetail with id " + requestReturnTranDetailString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String returnTranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranDetail);
        String currentReturnTranDetailString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetail");
        if (returnTranDetailString == null || returnTranDetailString.length() == 0 || !returnTranDetailString.equals(currentReturnTranDetailString)) {
            String outcome = editSetup();
            if ("returnTranDetail_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit returnTranDetail. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(returnTranDetail);
            JsfUtil.addSuccessMessage("ReturnTranDetail was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranDetail");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ReturnTranDetail was successfully deleted.");
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

    public List<ReturnTranDetail> getReturnTranDetailItems() {
        if (returnTranDetailItems == null) {
            getPagingInfo();
            returnTranDetailItems = jpaController.findReturnTranDetailEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return returnTranDetailItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "returnTranDetail_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "returnTranDetail_list";
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
        returnTranDetail = null;
        returnTranDetailItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ReturnTranDetail newReturnTranDetail = new ReturnTranDetail();
        String newReturnTranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, newReturnTranDetail);
        String returnTranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranDetail);
        if (!newReturnTranDetailString.equals(returnTranDetailString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
