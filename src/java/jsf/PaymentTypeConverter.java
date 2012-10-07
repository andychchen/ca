/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.PaymentTypeJpaController;
import jpa.entities.PaymentType;

/**
 *
 * @author achen
 */
public class PaymentTypeConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        PaymentTypeJpaController controller = (PaymentTypeJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "paymentTypeJpa");
        return controller.findPaymentType(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof PaymentType) {
            PaymentType o = (PaymentType) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.PaymentType");
        }
    }

}
