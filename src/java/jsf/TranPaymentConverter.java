/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.TranPaymentJpaController;
import jpa.entities.TranPayment;

/**
 *
 * @author achen
 */
public class TranPaymentConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        TranPaymentJpaController controller = (TranPaymentJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranPaymentJpa");
        return controller.findTranPayment(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof TranPayment) {
            TranPayment o = (TranPayment) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.TranPayment");
        }
    }

}
