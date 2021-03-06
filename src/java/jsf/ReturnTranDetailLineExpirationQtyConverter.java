/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.ReturnTranDetailLineExpirationQtyJpaController;
import jpa.entities.ReturnTranDetailLineExpirationQty;
import jpa.entities.ReturnTranDetailLineExpirationQtyPK;

/**
 *
 * @author achen
 */
public class ReturnTranDetailLineExpirationQtyConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        ReturnTranDetailLineExpirationQtyPK id = getId(string);
        ReturnTranDetailLineExpirationQtyJpaController controller = (ReturnTranDetailLineExpirationQtyJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "returnTranDetailLineExpirationQtyJpa");
        return controller.findReturnTranDetailLineExpirationQty(id);
    }

    ReturnTranDetailLineExpirationQtyPK getId(String string) {
        ReturnTranDetailLineExpirationQtyPK id = new ReturnTranDetailLineExpirationQtyPK();
        String[] params = new String[2];
        int p = 0;
        int grabStart = 0;
        String delim = "#";
        String escape = "~";
        Pattern pattern = Pattern.compile(escape + "*" + delim);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String found = matcher.group();
            if (found.length() % 2 == 1) {
                params[p] = string.substring(grabStart, matcher.start());
                p++;
                grabStart = matcher.end();
            }
        }
        if (p != params.length - 1) {
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 2 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setTranDetailId(Integer.parseInt(params[0]));
        id.setExpirationDate((Date) FacesContext.getCurrentInstance().getApplication().createConverter(Date.class).getAsObject(FacesContext.getCurrentInstance(), null, params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ReturnTranDetailLineExpirationQty) {
            ReturnTranDetailLineExpirationQty o = (ReturnTranDetailLineExpirationQty) object;
            ReturnTranDetailLineExpirationQtyPK id = o.getReturnTranDetailLineExpirationQtyPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String tranDetailId = String.valueOf(id.getTranDetailId());
            tranDetailId = tranDetailId.replace(escape, escape + escape);
            tranDetailId = tranDetailId.replace(delim, escape + delim);
            Object expirationDateObj = id.getExpirationDate();
            String expirationDate = expirationDateObj == null ? "" : String.valueOf(expirationDateObj);
            expirationDate = expirationDate.replace(escape, escape + escape);
            expirationDate = expirationDate.replace(delim, escape + delim);
            return tranDetailId + delim + expirationDate;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.ReturnTranDetailLineExpirationQty");
        }
    }

}
