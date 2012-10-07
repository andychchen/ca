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
import jpa.controllers.WeekDaysJpaController;
import jpa.entities.WeekDays;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class WeekDaysController {

    public WeekDaysController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (WeekDaysJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "weekDaysJpa");
        pagingInfo = new PagingInfo();
        converter = new WeekDaysConverter();
    }
    private WeekDays weekDays = null;
    private List<WeekDays> weekDaysItems = null;
    private WeekDaysJpaController jpaController = null;
    private WeekDaysConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getWeekDaysCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getWeekDaysItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findWeekDaysEntities(), false);
    }

    public SelectItem[] getWeekDaysItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findWeekDaysEntities(), true);
    }

    public WeekDays getWeekDays() {
        if (weekDays == null) {
            weekDays = (WeekDays) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWeekDays", converter, null);
        }
        if (weekDays == null) {
            weekDays = new WeekDays();
        }
        return weekDays;
    }

    public String listSetup() {
        reset(true);
        return "weekDays_list";
    }

    public String createSetup() {
        reset(false);
        weekDays = new WeekDays();
        return "weekDays_create";
    }

    public String create() {
        try {
            jpaController.create(weekDays);
            JsfUtil.addSuccessMessage("WeekDays was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("weekDays_detail");
    }

    public String editSetup() {
        return scalarSetup("weekDays_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        weekDays = (WeekDays) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentWeekDays", converter, null);
        if (weekDays == null) {
            String requestWeekDaysString = JsfUtil.getRequestParameter("jsfcrud.currentWeekDays");
            JsfUtil.addErrorMessage("The weekDays with id " + requestWeekDaysString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String weekDaysString = converter.getAsString(FacesContext.getCurrentInstance(), null, weekDays);
        String currentWeekDaysString = JsfUtil.getRequestParameter("jsfcrud.currentWeekDays");
        if (weekDaysString == null || weekDaysString.length() == 0 || !weekDaysString.equals(currentWeekDaysString)) {
            String outcome = editSetup();
            if ("weekDays_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit weekDays. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(weekDays);
            JsfUtil.addSuccessMessage("WeekDays was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentWeekDays");
        Integer id = new Integer(idAsString);
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("WeekDays was successfully deleted.");
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

    public List<WeekDays> getWeekDaysItems() {
        if (weekDaysItems == null) {
            getPagingInfo();
            weekDaysItems = jpaController.findWeekDaysEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return weekDaysItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "weekDays_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "weekDays_list";
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
        weekDays = null;
        weekDaysItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        WeekDays newWeekDays = new WeekDays();
        String newWeekDaysString = converter.getAsString(FacesContext.getCurrentInstance(), null, newWeekDays);
        String weekDaysString = converter.getAsString(FacesContext.getCurrentInstance(), null, weekDays);
        if (!newWeekDaysString.equals(weekDaysString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
