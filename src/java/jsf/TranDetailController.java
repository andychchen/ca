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
import jpa.controllers.TranDetailJpaController;
import jpa.entities.TranDetail;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class TranDetailController {

    public TranDetailController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (TranDetailJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranDetailJpa");
        pagingInfo = new PagingInfo();
        converter = new TranDetailConverter();
    }
    private TranDetail tranDetail = null;
    private List<TranDetail> tranDetailItems = null;
    private TranDetailJpaController jpaController = null;
    private TranDetailConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getTranDetailCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getTranDetailItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findTranDetailEntities(), false);
    }

    public SelectItem[] getTranDetailItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findTranDetailEntities(), true);
    }

    public TranDetail getTranDetail() {
        if (tranDetail == null) {
            tranDetail = (TranDetail) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranDetail", converter, null);
        }
        if (tranDetail == null) {
            tranDetail = new TranDetail();
        }
        return tranDetail;
    }

    public String listSetup() {
        reset(true);
        //return "tranDetail_list";
        return last();
    }

    public String createSetup() {
        reset(false);
        tranDetail = new TranDetail();
        return "tranDetail_create";
    }

    public String create() {
        try {
            jpaController.create(tranDetail);
            JsfUtil.addSuccessMessage("TranDetail was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("tranDetail_detail");
    }

    public String editSetup() {
        return scalarSetup("tranDetail_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tranDetail = (TranDetail) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranDetail", converter, null);
        if (tranDetail == null) {
            String requestTranDetailString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetail");
            JsfUtil.addErrorMessage("The tranDetail with id " + requestTranDetailString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String tranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranDetail);
        String currentTranDetailString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetail");
        if (tranDetailString == null || tranDetailString.length() == 0 || !tranDetailString.equals(currentTranDetailString)) {
            String outcome = editSetup();
            if ("tranDetail_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tranDetail. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(tranDetail);
            JsfUtil.addSuccessMessage("TranDetail was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetail");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("TranDetail was successfully deleted.");
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

    public List<TranDetail> getTranDetailItems() {
        if (tranDetailItems == null) {
            getPagingInfo();
            tranDetailItems = jpaController.findTranDetailEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return tranDetailItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tranDetail_list";
    }

    public String last() {
        reset(false);
        getPagingInfo().lastPage();
        return "tranDetail_list";
    }

    public String first() {
        reset(false);
        getPagingInfo().firstPage();
        return "tranDetail_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tranDetail_list";
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
        tranDetail = null;
        tranDetailItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        TranDetail newTranDetail = new TranDetail();
        String newTranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTranDetail);
        String tranDetailString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranDetail);
        if (!newTranDetailString.equals(tranDetailString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
