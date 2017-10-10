package edu.msg.ro.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

/**
 * Converter class for SelectItem.
 * 
 * @author laszll
 *
 */
public class ConversorSelectItem implements Converter {

	/**
	 * String to SelectItem.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.isEmpty())
			return null;

		SelectItem selectItem = new SelectItem();
		selectItem.setLabel(value);
		return selectItem;
	}

	/**
	 * SelectItem to String.
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		return ((SelectItem) object).getLabel();
	}

}
