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
import jpa.controllers.TaxJpaController;
import jpa.entities.Tax;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class TaxController {

    public TaxController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (TaxJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "taxJpa");
        pagingInfo = new PagingInfo();
        converter = new TaxConverter();
    }
    private Tax tax = null;
    private List<Tax> taxItems = null;
    private TaxJpaController jpaController = null;
    private TaxConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getTaxCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getTaxItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findTaxEntities(), false);
    }

    public SelectItem[] getTaxItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findTaxEntities(), true);
    }

    public Tax getTax() {
        if (tax == null) {
            tax = (Tax) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTax", converter, null);
        }
        if (tax == null) {
            tax = new Tax();
        }
        return tax;
    }

    public String listSetup() {
        reset(true);
        return "tax_list";
    }

    public String createSetup() {
        reset(false);
        tax = new Tax();
        return "tax_create";
    }

    public String create() {
        try {
            jpaController.create(tax);
            JsfUtil.addSuccessMessage("Tax was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("tax_detail");
    }

    public String editSetup() {
        return scalarSetup("tax_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tax = (Tax) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTax", converter, null);
        if (tax == null) {
            String requestTaxString = JsfUtil.getRequestParameter("jsfcrud.currentTax");
            JsfUtil.addErrorMessage("The tax with id " + requestTaxString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String taxString = converter.getAsString(FacesContext.getCurrentInstance(), null, tax);
        String currentTaxString = JsfUtil.getRequestParameter("jsfcrud.currentTax");
        if (taxString == null || taxString.length() == 0 || !taxString.equals(currentTaxString)) {
            String outcome = editSetup();
            if ("tax_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tax. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(tax);
            JsfUtil.addSuccessMessage("Tax was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTax");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Tax was successfully deleted.");
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

    public List<Tax> getTaxItems() {
        if (taxItems == null) {
            getPagingInfo();
            taxItems = jpaController.findTaxEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return taxItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tax_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tax_list";
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
        tax = null;
        taxItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Tax newTax = new Tax();
        String newTaxString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTax);
        String taxString = converter.getAsString(FacesContext.getCurrentInstance(), null, tax);
        if (!newTaxString.equals(taxString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
