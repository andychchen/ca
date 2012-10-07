/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.TranHeadJpaController;
import jpa.entities.TranHead;

/**
 *
 * @author achen
 */
public class TranHeadConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        TranHeadJpaController controller = (TranHeadJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tranHeadJpa");
        return controller.findTranHead(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof TranHead) {
            TranHead o = (TranHead) object;
            return o.getTranHeadId() == null ? "" : o.getTranHeadId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.TranHead");
        }
    }

}
