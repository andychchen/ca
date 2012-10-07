/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.DiscountJpaController;
import jpa.entities.Discount;

/**
 *
 * @author achen
 */
public class DiscountConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        DiscountJpaController controller = (DiscountJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "discountJpa");
        return controller.findDiscount(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Discount) {
            Discount o = (Discount) object;
            return o.getDiscountId() == null ? "" : o.getDiscountId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Discount");
        }
    }

}
