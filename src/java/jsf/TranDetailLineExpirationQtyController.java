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
import jpa.controllers.TranDetailLineExpirationQtyJpaController;
import jpa.entities.TranDetailLineExpirationQty;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.entities.TranDetailLineExpirationQtyPK;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class TranDetailLineExpirationQtyController {

    public TranDetailLineExpirationQtyController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (TranDetailLineExpirationQtyJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranDetailLineExpirationQtyJpa");
        pagingInfo = new PagingInfo();
        converter = new TranDetailLineExpirationQtyConverter();
    }
    private TranDetailLineExpirationQty tranDetailLineExpirationQty = null;
    private List<TranDetailLineExpirationQty> tranDetailLineExpirationQtyItems = null;
    private TranDetailLineExpirationQtyJpaController jpaController = null;
    private TranDetailLineExpirationQtyConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getTranDetailLineExpirationQtyCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getTranDetailLineExpirationQtyItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findTranDetailLineExpirationQtyEntities(), false);
    }

    public SelectItem[] getTranDetailLineExpirationQtyItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findTranDetailLineExpirationQtyEntities(), true);
    }

    public TranDetailLineExpirationQty getTranDetailLineExpirationQty() {
        if (tranDetailLineExpirationQty == null) {
            tranDetailLineExpirationQty = (TranDetailLineExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranDetailLineExpirationQty", converter, null);
        }
        if (tranDetailLineExpirationQty == null) {
            tranDetailLineExpirationQty = new TranDetailLineExpirationQty();
        }
        return tranDetailLineExpirationQty;
    }

    public String listSetup() {
        reset(true);
        //return "tranDetailLineExpirationQty_list";
        return last();
    }

    public String createSetup() {
        reset(false);
        tranDetailLineExpirationQty = new TranDetailLineExpirationQty();
        tranDetailLineExpirationQty.setTranDetailLineExpirationQtyPK(new TranDetailLineExpirationQtyPK());
        return "tranDetailLineExpirationQty_create";
    }

    public String create() {
        try {
            jpaController.create(tranDetailLineExpirationQty);
            JsfUtil.addSuccessMessage("TranDetailLineExpirationQty was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("tranDetailLineExpirationQty_detail");
    }

    public String editSetup() {
        return scalarSetup("tranDetailLineExpirationQty_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tranDetailLineExpirationQty = (TranDetailLineExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTranDetailLineExpirationQty", converter, null);
        if (tranDetailLineExpirationQty == null) {
            String requestTranDetailLineExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetailLineExpirationQty");
            JsfUtil.addErrorMessage("The tranDetailLineExpirationQty with id " + requestTranDetailLineExpirationQtyString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        tranDetailLineExpirationQty.getTranDetailLineExpirationQtyPK().setTranDetailId(tranDetailLineExpirationQty.getTranDetail().getTranDetailId());
        String tranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranDetailLineExpirationQty);
        String currentTranDetailLineExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetailLineExpirationQty");
        if (tranDetailLineExpirationQtyString == null || tranDetailLineExpirationQtyString.length() == 0 || !tranDetailLineExpirationQtyString.equals(currentTranDetailLineExpirationQtyString)) {
            String outcome = editSetup();
            if ("tranDetailLineExpirationQty_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tranDetailLineExpirationQty. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(tranDetailLineExpirationQty);
            JsfUtil.addSuccessMessage("TranDetailLineExpirationQty was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTranDetailLineExpirationQty");
        TranDetailLineExpirationQtyPK id = converter.getId(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("TranDetailLineExpirationQty was successfully deleted.");
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

    public List<TranDetailLineExpirationQty> getTranDetailLineExpirationQtyItems() {
        if (tranDetailLineExpirationQtyItems == null) {
            getPagingInfo();
            tranDetailLineExpirationQtyItems = jpaController.findTranDetailLineExpirationQtyEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return tranDetailLineExpirationQtyItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tranDetailLineExpirationQty_list";
    }

    public String last() {
        reset(false);
        getPagingInfo().lastPage();
        return "tranDetailLineExpirationQty_list";
    }

    public String first() {
        reset(false);
        getPagingInfo().firstPage();
        return "tranDetailLineExpirationQty_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tranDetailLineExpirationQty_list";
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
        tranDetailLineExpirationQty = null;
        tranDetailLineExpirationQtyItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        TranDetailLineExpirationQty newTranDetailLineExpirationQty = new TranDetailLineExpirationQty();
        newTranDetailLineExpirationQty.setTranDetailLineExpirationQtyPK(new TranDetailLineExpirationQtyPK());
        String newTranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTranDetailLineExpirationQty);
        String tranDetailLineExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, tranDetailLineExpirationQty);
        if (!newTranDetailLineExpirationQtyString.equals(tranDetailLineExpirationQtyString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
