/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.TaxJpaController;
import jpa.entities.Tax;

/**
 *
 * @author achen
 */
public class TaxConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        TaxJpaController controller = (TaxJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "taxJpa");
        return controller.findTax(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Tax) {
            Tax o = (Tax) object;
            return o.getTaxId() == null ? "" : o.getTaxId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Tax");
        }
    }

}
