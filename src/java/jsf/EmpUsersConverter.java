/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.EmpUsersJpaController;
import jpa.entities.EmpUsers;

/**
 *
 * @author achen
 */
public class EmpUsersConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        String id = string;
        EmpUsersJpaController controller = (EmpUsersJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "empUsersJpa");
        return controller.findEmpUsers(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EmpUsers) {
            EmpUsers o = (EmpUsers) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.EmpUsers");
        }
    }

}
