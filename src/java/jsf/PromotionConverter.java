/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.PromotionJpaController;
import jpa.entities.Promotion;

/**
 *
 * @author achen
 */
public class PromotionConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        PromotionJpaController controller = (PromotionJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "promotionJpa");
        return controller.findPromotion(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Promotion) {
            Promotion o = (Promotion) object;
            return o.getPromotionId() == null ? "" : o.getPromotionId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.Promotion");
        }
    }

}
