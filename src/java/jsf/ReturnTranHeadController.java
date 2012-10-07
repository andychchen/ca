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
import jpa.controllers.ReturnTranHeadJpaController;
import jpa.entities.ReturnTranHead;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class ReturnTranHeadController {

    public ReturnTranHeadController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ReturnTranHeadJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranHeadJpa");
        pagingInfo = new PagingInfo();
        converter = new ReturnTranHeadConverter();
    }
    private ReturnTranHead returnTranHead = null;
    private List<ReturnTranHead> returnTranHeadItems = null;
    private ReturnTranHeadJpaController jpaController = null;
    private ReturnTranHeadConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getReturnTranHeadCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getReturnTranHeadItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranHeadEntities(), false);
    }

    public SelectItem[] getReturnTranHeadItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findReturnTranHeadEntities(), true);
    }

    public ReturnTranHead getReturnTranHead() {
        if (returnTranHead == null) {
            returnTranHead = (ReturnTranHead) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranHead", converter, null);
        }
        if (returnTranHead == null) {
            returnTranHead = new ReturnTranHead();
        }
        return returnTranHead;
    }

    public String listSetup() {
        reset(true);
        return "returnTranHead_list";
    }

    public String createSetup() {
        reset(false);
        returnTranHead = new ReturnTranHead();
        return "returnTranHead_create";
    }

    public String create() {
        try {
            jpaController.create(returnTranHead);
            JsfUtil.addSuccessMessage("ReturnTranHead was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("returnTranHead_detail");
    }

    public String editSetup() {
        return scalarSetup("returnTranHead_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        returnTranHead = (ReturnTranHead) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentReturnTranHead", converter, null);
        if (returnTranHead == null) {
            String requestReturnTranHeadString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranHead");
            JsfUtil.addErrorMessage("The returnTranHead with id " + requestReturnTranHeadString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String returnTranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranHead);
        String currentReturnTranHeadString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranHead");
        if (returnTranHeadString == null || returnTranHeadString.length() == 0 || !returnTranHeadString.equals(currentReturnTranHeadString)) {
            String outcome = editSetup();
            if ("returnTranHead_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit returnTranHead. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(returnTranHead);
            JsfUtil.addSuccessMessage("ReturnTranHead was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentReturnTranHead");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ReturnTranHead was successfully deleted.");
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

    public List<ReturnTranHead> getReturnTranHeadItems() {
        if (returnTranHeadItems == null) {
            getPagingInfo();
            returnTranHeadItems = jpaController.findReturnTranHeadEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return returnTranHeadItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "returnTranHead_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "returnTranHead_list";
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
        returnTranHead = null;
        returnTranHeadItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ReturnTranHead newReturnTranHead = new ReturnTranHead();
        String newReturnTranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, newReturnTranHead);
        String returnTranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, returnTranHead);
        if (!newReturnTranHeadString.equals(returnTranHeadString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
