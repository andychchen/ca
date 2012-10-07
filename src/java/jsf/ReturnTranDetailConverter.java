/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.ReturnTranDetailJpaController;
import jpa.entities.ReturnTranDetail;

/**
 *
 * @author achen
 */
public class ReturnTranDetailConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ReturnTranDetailJpaController controller = (ReturnTranDetailJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranDetailJpa");
        return controller.findReturnTranDetail(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ReturnTranDetail) {
            ReturnTranDetail o = (ReturnTranDetail) object;
            return o.getTranDetailId() == null ? "" : o.getTranDetailId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.ReturnTranDetail");
        }
    }

}
