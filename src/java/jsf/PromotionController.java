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
import jpa.controllers.PromotionJpaController;
import jpa.entities.Promotion;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class PromotionController {

    public PromotionController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (PromotionJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "promotionJpa");
        pagingInfo = new PagingInfo();
        converter = new PromotionConverter();
    }
    private Promotion promotion = null;
    private List<Promotion> promotionItems = null;
    private PromotionJpaController jpaController = null;
    private PromotionConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getPromotionCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getPromotionItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findPromotionEntities(), false);
    }

    public SelectItem[] getPromotionItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findPromotionEntities(), true);
    }

    public Promotion getPromotion() {
        if (promotion == null) {
            promotion = (Promotion) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPromotion", converter, null);
        }
        if (promotion == null) {
            promotion = new Promotion();
        }
        return promotion;
    }

    public String listSetup() {
        reset(true);
        return "promotion_list";
    }

    public String createSetup() {
        reset(false);
        promotion = new Promotion();
        return "promotion_create";
    }

    public String create() {
        try {
            jpaController.create(promotion);
            JsfUtil.addSuccessMessage("Promotion was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("promotion_detail");
    }

    public String editSetup() {
        return scalarSetup("promotion_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        promotion = (Promotion) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPromotion", converter, null);
        if (promotion == null) {
            String requestPromotionString = JsfUtil.getRequestParameter("jsfcrud.currentPromotion");
            JsfUtil.addErrorMessage("The promotion with id " + requestPromotionString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String promotionString = converter.getAsString(FacesContext.getCurrentInstance(), null, promotion);
        String currentPromotionString = JsfUtil.getRequestParameter("jsfcrud.currentPromotion");
        if (promotionString == null || promotionString.length() == 0 || !promotionString.equals(currentPromotionString)) {
            String outcome = editSetup();
            if ("promotion_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit promotion. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(promotion);
            JsfUtil.addSuccessMessage("Promotion was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPromotion");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("Promotion was successfully deleted.");
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

    public List<Promotion> getPromotionItems() {
        if (promotionItems == null) {
            getPagingInfo();
            promotionItems = jpaController.findPromotionEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return promotionItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "promotion_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "promotion_list";
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
        promotion = null;
        promotionItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Promotion newPromotion = new Promotion();
        String newPromotionString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPromotion);
        String promotionString = converter.getAsString(FacesContext.getCurrentInstance(), null, promotion);
        if (!newPromotionString.equals(promotionString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
