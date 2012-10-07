/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.TranDetailJpaController;
import jpa.entities.TranDetail;

/**
 *
 * @author achen
 */
public class TranDetailConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        TranDetailJpaController controller = (TranDetailJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranDetailJpa");
        return controller.findTranDetail(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof TranDetail) {
            TranDetail o = (TranDetail) object;
            return o.getTranDetailId() == null ? "" : o.getTranDetailId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.TranDetail");
        }
    }

}
