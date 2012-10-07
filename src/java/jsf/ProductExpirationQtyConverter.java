/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jsf;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import jpa.controllers.ProductExpirationQtyJpaController;
import jpa.entities.ProductExpirationQty;
import jpa.entities.ProductExpirationQtyPK;

/**
 *
 * @author achen
 */
public class ProductExpirationQtyConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        ProductExpirationQtyPK id = getId(string);
        ProductExpirationQtyJpaController controller = (ProductExpirationQtyJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "productExpirationQtyJpa");
        return controller.findProductExpirationQty(id);
    }

    ProductExpirationQtyPK getId(String string) {
        ProductExpirationQtyPK id = new ProductExpirationQtyPK();
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
        id.setIsbn(params[0]);
        //id.setExpirationDate((Date) FacesContext.getCurrentInstance().getApplication().createConverter(Date.class).getAsObject(FacesContext.getCurrentInstance(), null, params[1]));
        id.setExpirationDate(new Date(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof ProductExpirationQty) {
            ProductExpirationQty o = (ProductExpirationQty) object;
            ProductExpirationQtyPK id = o.getProductExpirationQtyPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String isbn = id.getIsbn();
            isbn = isbn == null ? "" : isbn.replace(escape, escape + escape);
            isbn = isbn.replace(delim, escape + delim);
            Object expirationDateObj = id.getExpirationDate();
            //String expirationDate = expirationDateObj == null ? "" : String.valueOf(expirationDateObj);
            String expirationDate = expirationDateObj == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(expirationDateObj);
            expirationDate = expirationDate.replace(escape, escape + escape);
            expirationDate = expirationDate.replace(delim, escape + delim);
            return isbn + delim + expirationDate;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: jpa.entities.ProductExpirationQty");
        }
    }

}
