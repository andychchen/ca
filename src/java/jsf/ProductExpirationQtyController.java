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
import jpa.controllers.ProductExpirationQtyJpaController;
import jpa.entities.ProductExpirationQty;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jpa.entities.ProductExpirationQtyPK;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class ProductExpirationQtyController {

    public ProductExpirationQtyController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (ProductExpirationQtyJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "productExpirationQtyJpa");
        pagingInfo = new PagingInfo();
        converter = new ProductExpirationQtyConverter();
    }
    private ProductExpirationQty productExpirationQty = null;
    private List<ProductExpirationQty> productExpirationQtyItems = null;
    private ProductExpirationQtyJpaController jpaController = null;
    private ProductExpirationQtyConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getProductExpirationQtyCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getProductExpirationQtyItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findProductExpirationQtyEntities(), false);
    }

    public SelectItem[] getProductExpirationQtyItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findProductExpirationQtyEntities(), true);
    }

    public ProductExpirationQty getProductExpirationQty() {
        if (productExpirationQty == null) {
            productExpirationQty = (ProductExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentProductExpirationQty", converter, null);
        }
        if (productExpirationQty == null) {
            productExpirationQty = new ProductExpirationQty();
        }
        return productExpirationQty;
    }

    public String listSetup() {
        reset(true);
        //return "productExpirationQty_list";
        return last();
    }

    public String createSetup() {
        reset(false);
        productExpirationQty = new ProductExpirationQty();
        productExpirationQty.setProductExpirationQtyPK(new ProductExpirationQtyPK());
        return "productExpirationQty_create";
    }

    public String create() {
        try {
            jpaController.create(productExpirationQty);
            JsfUtil.addSuccessMessage("ProductExpirationQty was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("productExpirationQty_detail");
    }

    public String editSetup() {
        return scalarSetup("productExpirationQty_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        productExpirationQty = (ProductExpirationQty) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentProductExpirationQty", converter, null);
        if (productExpirationQty == null) {
            String requestProductExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentProductExpirationQty");
            JsfUtil.addErrorMessage("The productExpirationQty with id " + requestProductExpirationQtyString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        productExpirationQty.getProductExpirationQtyPK().setIsbn(productExpirationQty.getProduct().getIsbn());
        String productExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, productExpirationQty);
        String currentProductExpirationQtyString = JsfUtil.getRequestParameter("jsfcrud.currentProductExpirationQty");
        if (productExpirationQtyString == null || productExpirationQtyString.length() == 0 || !productExpirationQtyString.equals(currentProductExpirationQtyString)) {
            String outcome = editSetup();
            if ("productExpirationQty_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit productExpirationQty. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(productExpirationQty);
            JsfUtil.addSuccessMessage("ProductExpirationQty was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentProductExpirationQty");
        ProductExpirationQtyPK id = converter.getId(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("ProductExpirationQty was successfully deleted.");
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

    public List<ProductExpirationQty> getProductExpirationQtyItems() {
        if (productExpirationQtyItems == null) {
            getPagingInfo();
            productExpirationQtyItems = jpaController.findProductExpirationQtyEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return productExpirationQtyItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "productExpirationQty_list";
    }

    public String last() {
        reset(false);
        getPagingInfo().lastPage();
        return "productExpirationQty_list";
    }

    public String first() {
        reset(false);
        getPagingInfo().firstPage();
        return "productExpirationQty_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "productExpirationQty_list";
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
        productExpirationQty = null;
        productExpirationQtyItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        ProductExpirationQty newProductExpirationQty = new ProductExpirationQty();
        newProductExpirationQty.setProductExpirationQtyPK(new ProductExpirationQtyPK());
        String newProductExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, newProductExpirationQty);
        String productExpirationQtyString = converter.getAsString(FacesContext.getCurrentInstance(), null, productExpirationQty);
        if (!newProductExpirationQtyString.equals(productExpirationQtyString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
