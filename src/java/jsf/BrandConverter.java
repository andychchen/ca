/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.BrandJpaController;
import jpa.entities.Brand;

/**
 *
 * @author achen
 */
public class BrandConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        BrandJpaController controller = (BrandJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "brandJpa");
        return controller.findBrand(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Brand) {
            Brand o = (Brand) object;
            return o.getBrandId() == null ? "" : o.getBrandId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Brand");
        }
    }

}
