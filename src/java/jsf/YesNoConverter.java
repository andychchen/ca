/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.YesNoJpaController;
import jpa.entities.YesNo;

/**
 *
 * @author achen
 */
public class YesNoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Character id = new Character(string.charAt(0));
        YesNoJpaController controller = (YesNoJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "yesNoJpa");
        return controller.findYesNo(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof YesNo) {
            YesNo o = (YesNo) object;
            return o.getName() == null ? "" : o.getName().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.YesNo");
        }
    }

}
