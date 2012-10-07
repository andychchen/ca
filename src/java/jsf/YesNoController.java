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
import jpa.controllers.YesNoJpaController;
import jpa.entities.YesNo;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class YesNoController {

    public YesNoController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (YesNoJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "yesNoJpa");
        pagingInfo = new PagingInfo();
        converter = new YesNoConverter();
    }
    private YesNo yesNo = null;
    private List<YesNo> yesNoItems = null;
    private YesNoJpaController jpaController = null;
    private YesNoConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getYesNoCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getYesNoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findYesNoEntities(), false);
    }

    public SelectItem[] getYesNoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findYesNoEntities(), true);
    }

    public YesNo getYesNo() {
        if (yesNo == null) {
            yesNo = (YesNo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentYesNo", converter, null);
        }
        if (yesNo == null) {
            yesNo = new YesNo();
        }
        return yesNo;
    }

    public String listSetup() {
        reset(true);
        return "yesNo_list";
    }

    public String createSetup() {
        reset(false);
        yesNo = new YesNo();
        return "yesNo_create";
    }

    public String create() {
        try {
            jpaController.create(yesNo);
            JsfUtil.addSuccessMessage("YesNo was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("yesNo_detail");
    }

    public String editSetup() {
        return scalarSetup("yesNo_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        yesNo = (YesNo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentYesNo", converter, null);
        if (yesNo == null) {
            String requestYesNoString = JsfUtil.getRequestParameter("jsfcrud.currentYesNo");
            JsfUtil.addErrorMessage("The yesNo with id " + requestYesNoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String yesNoString = converter.getAsString(FacesContext.getCurrentInstance(), null, yesNo);
        String currentYesNoString = JsfUtil.getRequestParameter("jsfcrud.currentYesNo");
        if (yesNoString == null || yesNoString.length() == 0 || !yesNoString.equals(currentYesNoString)) {
            String outcome = editSetup();
            if ("yesNo_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit yesNo. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(yesNo);
            JsfUtil.addSuccessMessage("YesNo was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentYesNo");
        Character id = new Character(idAsString.charAt(0));
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("YesNo was successfully deleted.");
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

    public List<YesNo> getYesNoItems() {
        if (yesNoItems == null) {
            getPagingInfo();
            yesNoItems = jpaController.findYesNoEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return yesNoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "yesNo_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "yesNo_list";
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
        yesNo = null;
        yesNoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        YesNo newYesNo = new YesNo();
        String newYesNoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newYesNo);
        String yesNoString = converter.getAsString(FacesContext.getCurrentInstance(), null, yesNo);
        if (!newYesNoString.equals(yesNoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
