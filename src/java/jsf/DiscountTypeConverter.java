/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.DiscountTypeJpaController;
import jpa.entities.DiscountType;

/**
 *
 * @author achen
 */
public class DiscountTypeConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        String id = string;
        DiscountTypeJpaController controller = (DiscountTypeJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "discountTypeJpa");
        return controller.findDiscountType(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof DiscountType) {
            DiscountType o = (DiscountType) object;
            return o.getType() == null ? "" : o.getType().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.DiscountType");
        }
    }

}
