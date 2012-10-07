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
import jpa.controllers.TranHeadJpaController;
import jpa.entities.TranHead;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class TranHeadController {

    public TranHeadController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (TranHeadJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranHeadJpa");
        pagingInfo = new PagingInfo();
        converter = new TranHeadConverter();
    }
    private TranHead tranHead = null;
    private List<TranHead> tranHeadItems = null;
    private TranHeadJpaController jpaController = null;
    private TranHeadConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getTranHeadCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getTranHeadItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findTranHeadEntities(), false);
    }

    public SelectItem[] getTranHeadItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findTranHeadEntities(), true);
    }

    public TranHead getTranHead() {
        if (tranHead == null) {
            tranHead = (TranHead) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranHead", converter, null);
        }
        if (tranHead == null) {
            tranHead = new TranHead();
        }
        return tranHead;
    }

    public String listSetup() {
        reset(true);
        //return "tranHead_list";
        return last();
    }

    public String createSetup() {
        reset(false);
        tranHead = new TranHead();
        return "tranHead_create";
    }

    public String create() {
        try {
            jpaController.create(tranHead);
            JsfUtil.addSuccessMessage("TranHead was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("tranHead_detail");
    }

    public String editSetup() {
        return scalarSetup("tranHead_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tranHead = (TranHead) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranHead", converter, null);
        if (tranHead == null) {
            String requestTranHeadString = JsfUtil.getRequestParameter("jsfcrud.currentTranHead");
            JsfUtil.addErrorMessage("The tranHead with id " + requestTranHeadString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String tranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranHead);
        String currentTranHeadString = JsfUtil.getRequestParameter("jsfcrud.currentTranHead");
        if (tranHeadString == null || tranHeadString.length() == 0 || !tranHeadString.equals(currentTranHeadString)) {
            String outcome = editSetup();
            if ("tranHead_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tranHead. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(tranHead);
            JsfUtil.addSuccessMessage("TranHead was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTranHead");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("TranHead was successfully deleted.");
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

    public List<TranHead> getTranHeadItems() {
        if (tranHeadItems == null) {
            getPagingInfo();
            tranHeadItems = jpaController.findTranHeadEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return tranHeadItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tranHead_list";
    }

    public String last() {
        reset(false);
        getPagingInfo().lastPage();
        return "tranHead_list";
    }

    public String first() {
        reset(false);
        getPagingInfo().firstPage();
        return "tranHead_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tranHead_list";
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
        tranHead = null;
        tranHeadItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        TranHead newTranHead = new TranHead();
        String newTranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTranHead);
        String tranHeadString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranHead);
        if (!newTranHeadString.equals(tranHeadString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
