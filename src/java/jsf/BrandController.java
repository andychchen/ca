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
import jpa.controllers.BrandJpaController;
import jpa.entities.Brand;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class BrandController {

    public BrandController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (BrandJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "brandJpa");
        pagingInfo = new PagingInfo();
        converter = new BrandConverter();
    }
    private Brand brand = null;
    private List<Brand> brandItems = null;
    private BrandJpaController jpaController = null;
    private BrandConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getBrandCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getBrandItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findBrandEntities(), false);
    }

    public SelectItem[] getBrandItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findBrandEntities(), true);
    }

    public Brand getBrand() {
        if (brand == null) {
            brand = (Brand) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBrand", converter, null);
        }
        if (brand == null) {
            brand = new Brand();
        }
        return brand;
    }

    public String listSetup() {
        reset(true);
        //return "brand_list";
        return last();
    }

    public String createSetup() {
        reset(false);
        brand = new Brand();
        return "brand_create";
    }

    public String create() {
        try {
            jpaController.create(brand);
            JsfUtil.addSuccessMessage("Brand was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("brand_detail");
    }

    public String editSetup() {
        return scalarSetup("brand_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        brand = (Brand) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentBrand", converter, null);
        if (brand == null) {
            String requestBrandString = JsfUtil.getRequestParameter("jsfcrud.currentBrand");
            JsfUtil.addErrorMessage("The brand with id " + requestBrandString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String brandString = converter.getAsString(FacesContext.getCurrentInstance(), null, brand);
        String currentBrandString = JsfUtil.getRequestParameter("jsfcrud.currentBrand");
        if (brandString == null || brandString.length() == 0 || !brandString.equals(currentBrandString)) {
            String outcome = editSetup();
            if ("brand_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit brand. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(brand);
            JsfUtil.addSuccessMessage("Brand was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentBrand");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Brand was successfully deleted.");
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

    public List<Brand> getBrandItems() {
        if (brandItems == null) {
            getPagingInfo();
            brandItems = jpaController.findBrandEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return brandItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "brand_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "brand_list";
    }

    public String last() {
        reset(false);
        getPagingInfo().lastPage();
        return "brand_list";
    }

    public String first() {
        reset(false);
        getPagingInfo().firstPage();
        return "brand_list";
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
        brand = null;
        brandItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Brand newBrand = new Brand();
        String newBrandString = converter.getAsString(FacesContext.getCurrentInstance(), null, newBrand);
        String brandString = converter.getAsString(FacesContext.getCurrentInstance(), null, brand);
        if (!newBrandString.equals(brandString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
}
