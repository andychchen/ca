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
import jpa.controllers.DiscountJpaController;
import jpa.entities.Discount;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.controllers.exceptions.IllegalOrphanException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class DiscountController {

    public DiscountController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (DiscountJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "discountJpa");
        pagingInfo = new PagingInfo();
        converter = new DiscountConverter();
    }
    private Discount discount = null;
    private List<Discount> discountItems = null;
    private DiscountJpaController jpaController = null;
    private DiscountConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getDiscountCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getDiscountItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findDiscountEntities(), false);
    }

    public SelectItem[] getDiscountItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findDiscountEntities(), true);
    }

    public Discount getDiscount() {
        if (discount == null) {
            discount = (Discount) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDiscount", converter, null);
        }
        if (discount == null) {
            discount = new Discount();
        }
        return discount;
    }

    public String listSetup() {
        reset(true);
        return "discount_list";
    }

    public String createSetup() {
        reset(false);
        discount = new Discount();
        return "discount_create";
    }

    public String create() {
        try {
            jpaController.create(discount);
            JsfUtil.addSuccessMessage("Discount was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("discount_detail");
    }

    public String editSetup() {
        return scalarSetup("discount_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        discount = (Discount) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDiscount", converter, null);
        if (discount == null) {
            String requestDiscountString = JsfUtil.getRequestParameter("jsfcrud.currentDiscount");
            JsfUtil.addErrorMessage("The discount with id " + requestDiscountString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String discountString = converter.getAsString(FacesContext.getCurrentInstance(), null, discount);
        String currentDiscountString = JsfUtil.getRequestParameter("jsfcrud.currentDiscount");
        if (discountString == null || discountString.length() == 0 || !discountString.equals(currentDiscountString)) {
            String outcome = editSetup();
            if ("discount_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit discount. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(discount);
            JsfUtil.addSuccessMessage("Discount was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDiscount");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Discount was successfully deleted.");
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

    public List<Discount> getDiscountItems() {
        if (discountItems == null) {
            getPagingInfo();
            discountItems = jpaController.findDiscountEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return discountItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "discount_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "discount_list";
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
        discount = null;
        discountItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Discount newDiscount = new Discount();
        String newDiscountString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDiscount);
        String discountString = converter.getAsString(FacesContext.getCurrentInstance(), null, discount);
        if (!newDiscountString.equals(discountString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
