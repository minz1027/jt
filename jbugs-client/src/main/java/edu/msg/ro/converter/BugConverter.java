package edu.msg.ro.converter;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import edu.msg.ro.business.bug.control.BugService;
import edu.msg.ro.business.bug.enums.StatusEnum;

@ManagedBean
@RequestScoped
public class BugConverter implements Converter {

	@EJB
	private BugService bugService;

	/**
	 * Int to BugStatus.
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return StatusEnum.valueOf(value);
	}

	/**
	 * BugStatus to Int.
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return value.toString();
	}

}
