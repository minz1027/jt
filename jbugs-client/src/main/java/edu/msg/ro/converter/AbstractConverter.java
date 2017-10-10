package edu.msg.ro.converter;

import javax.faces.bean.ManagedProperty;
import javax.faces.convert.Converter;

import edu.msg.ro.i18n.BeanTranslator;

public abstract class AbstractConverter implements Converter {

	@ManagedProperty("#{beanTranslator}")
	protected BeanTranslator translator;

	/**
	 * Set Translation Service.
	 * 
	 * @param service
	 */
	public void setTranslator(BeanTranslator translator) {
		this.translator = translator;
	}
}
