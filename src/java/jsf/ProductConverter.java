/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.ProductJpaController;
import jpa.entities.Product;

/**
 *
 * @author achen
 */
public class ProductConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        String id = string;
        ProductJpaController controller = (ProductJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "productJpa");
        return controller.findProduct(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Product) {
            Product o = (Product) object;
            return o.getIsbn() == null ? "" : o.getIsbn().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Product");
        }
    }

}
