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
import jpa.controllers.DiscountTypeJpaController;
import jpa.entities.DiscountType;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class DiscountTypeController {

    public DiscountTypeController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (DiscountTypeJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "discountTypeJpa");
        pagingInfo = new PagingInfo();
        converter = new DiscountTypeConverter();
    }
    private DiscountType discountType = null;
    private List<DiscountType> discountTypeItems = null;
    private DiscountTypeJpaController jpaController = null;
    private DiscountTypeConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getDiscountTypeCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getDiscountTypeItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findDiscountTypeEntities(), false);
    }

    public SelectItem[] getDiscountTypeItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findDiscountTypeEntities(), true);
    }

    public DiscountType getDiscountType() {
        if (discountType == null) {
            discountType = (DiscountType) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDiscountType", converter, null);
        }
        if (discountType == null) {
            discountType = new DiscountType();
        }
        return discountType;
    }

    public String listSetup() {
        reset(true);
        return "discountType_list";
    }

    public String createSetup() {
        reset(false);
        discountType = new DiscountType();
        return "discountType_create";
    }

    public String create() {
        try {
            jpaController.create(discountType);
            JsfUtil.addSuccessMessage("DiscountType was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("discountType_detail");
    }

    public String editSetup() {
        return scalarSetup("discountType_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        discountType = (DiscountType) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDiscountType", converter, null);
        if (discountType == null) {
            String requestDiscountTypeString = JsfUtil.getRequestParameter("jsfcrud.currentDiscountType");
            JsfUtil.addErrorMessage("The discountType with id " + requestDiscountTypeString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String discountTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, discountType);
        String currentDiscountTypeString = JsfUtil.getRequestParameter("jsfcrud.currentDiscountType");
        if (discountTypeString == null || discountTypeString.length() == 0 || !discountTypeString.equals(currentDiscountTypeString)) {
            String outcome = editSetup();
            if ("discountType_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit discountType. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(discountType);
            JsfUtil.addSuccessMessage("DiscountType was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDiscountType");
        String id = idAsString;
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("DiscountType was successfully deleted.");
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

    public List<DiscountType> getDiscountTypeItems() {
        if (discountTypeItems == null) {
            getPagingInfo();
            discountTypeItems = jpaController.findDiscountTypeEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return discountTypeItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "discountType_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "discountType_list";
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
        discountType = null;
        discountTypeItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        DiscountType newDiscountType = new DiscountType();
        String newDiscountTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDiscountType);
        String discountTypeString = converter.getAsString(FacesContext.getCurrentInstance(), null, discountType);
        if (!newDiscountTypeString.equals(discountTypeString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
