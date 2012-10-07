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
import jpa.controllers.EmpUsersJpaController;
import jpa.entities.EmpUsers;
import jsf.util.JsfUtil;
import jpa.controllers.exceptions.NonexistentEntityException;
import jsf.util.PagingInfo;

/**
 *
 * @author achen
 */
public class EmpUsersController {

    public EmpUsersController() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaController = (EmpUsersJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "empUsersJpa");
        pagingInfo = new PagingInfo();
        converter = new EmpUsersConverter();
    }
    private EmpUsers empUsers = null;
    private List<EmpUsers> empUsersItems = null;
    private EmpUsersJpaController jpaController = null;
    private EmpUsersConverter converter = null;
    private PagingInfo pagingInfo = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(jpaController.getEmpUsersCount());
        }
        return pagingInfo;
    }

    public SelectItem[] getEmpUsersItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(jpaController.findEmpUsersEntities(), false);
    }

    public SelectItem[] getEmpUsersItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(jpaController.findEmpUsersEntities(), true);
    }

    public EmpUsers getEmpUsers() {
        if (empUsers == null) {
            empUsers = (EmpUsers) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEmpUsers", converter, null);
        }
        if (empUsers == null) {
            empUsers = new EmpUsers();
        }
        return empUsers;
    }

    public String listSetup() {
        reset(true);
        return "empUsers_list";
    }

    public String createSetup() {
        reset(false);
        empUsers = new EmpUsers();
        return "empUsers_create";
    }

    public String create() {
        try {
            jpaController.create(empUsers);
            JsfUtil.addSuccessMessage("EmpUsers was successfully created.");
        } catch (Exception e) {
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("empUsers_detail");
    }

    public String editSetup() {
        return scalarSetup("empUsers_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        empUsers = (EmpUsers) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentEmpUsers", converter, null);
        if (empUsers == null) {
            String requestEmpUsersString = JsfUtil.getRequestParameter("jsfcrud.currentEmpUsers");
            JsfUtil.addErrorMessage("The empUsers with id " + requestEmpUsersString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String empUsersString = converter.getAsString(FacesContext.getCurrentInstance(), null, empUsers);
        String currentEmpUsersString = JsfUtil.getRequestParameter("jsfcrud.currentEmpUsers");
        if (empUsersString == null || empUsersString.length() == 0 || !empUsersString.equals(currentEmpUsersString)) {
            String outcome = editSetup();
            if ("empUsers_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit empUsers. Try again.");
            }
            return outcome;
        }
        try {
            jpaController.edit(empUsers);
            JsfUtil.addSuccessMessage("EmpUsers was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentEmpUsers");
        String id = idAsString;
        try {
            jpaController.destroy(id);
            JsfUtil.addSuccessMessage("EmpUsers was successfully deleted.");
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

    public List<EmpUsers> getEmpUsersItems() {
        if (empUsersItems == null) {
            getPagingInfo();
            empUsersItems = jpaController.findEmpUsersEntities(pagingInfo.getBatchSize(), pagingInfo.getFirstItem());
        }
        return empUsersItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "empUsers_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "empUsers_list";
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
        empUsers = null;
        empUsersItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        EmpUsers newEmpUsers = new EmpUsers();
        String newEmpUsersString = converter.getAsString(FacesContext.getCurrentInstance(), null, newEmpUsers);
        String empUsersString = converter.getAsString(FacesContext.getCurrentInstance(), null, empUsers);
        if (!newEmpUsersString.equals(empUsersString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
