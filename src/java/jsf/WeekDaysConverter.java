/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.WeekDaysJpaController;
import jpa.entities.WeekDays;

/**
 *
 * @author achen
 */
public class WeekDaysConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        WeekDaysJpaController controller = (WeekDaysJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "weekDaysJpa");
        return controller.findWeekDays(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof WeekDays) {
            WeekDays o = (WeekDays) object;
            return o.getDay() == null ? "" : o.getDay().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.WeekDays");
        }
    }

}
