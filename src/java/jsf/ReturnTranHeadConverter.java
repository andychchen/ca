/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.ReturnTranHeadJpaController;
import jpa.entities.ReturnTranHead;

/**
 *
 * @author achen
 */
public class ReturnTranHeadConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        ReturnTranHeadJpaController controller = (ReturnTranHeadJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranHeadJpa");
        return controller.findReturnTranHead(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ReturnTranHead) {
            ReturnTranHead o = (ReturnTranHead) object;
            return o.getTranHeadId() == null ? "" : o.getTranHeadId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.ReturnTranHead");
        }
    }

}
