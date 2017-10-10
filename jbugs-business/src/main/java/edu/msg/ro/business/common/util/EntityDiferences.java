package edu.msg.ro.business.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ejb.Stateless;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.common.exception.TechnicalExeption;

/**
 * EntityDiferences class.
 * 
 * @author balinc
 *
 */
@Stateless
public class EntityDiferences {

	/**
	 * Return the declareted methods of the class without inheritance.
	 * 
	 * @param cls
	 * @return Method[]
	 */
	public <T> Method[] getMethods(Class<T> cls) {
		return cls.getDeclaredMethods();
	}

	/**
	 * Return the declareted fields of the class without inheritance.
	 * 
	 * @param cls
	 * @return Field[]
	 */
	public <T> Field[] getFields(Class<T> cls) {
		return cls.getDeclaredFields();
	}

	/**
	 * Get the value of a field by the getter.
	 * 
	 * @param field
	 * @param oldValue
	 * @return Object
	 * @throws TechnicalExeption
	 */
	public <T> Object getValueOfField(Field field, Object oldValue) throws BusinessException {
		try {
			return oldValue.getClass()
					.getMethod("get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1))
					.invoke(oldValue);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * Get field to be skiped for object.
	 * 
	 * @param value
	 * @return
	 * @return
	 * @throws BusinessException
	 */
	public List<String> getSkipFields(Object value) throws BusinessException {
		try {
			return ((List<String>) value.getClass().getMethod("skipFields").invoke(value));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * Return of array list of differences between two entities.
	 *
	 * @param oldValue
	 * @param newValue
	 * @return ArrayList<Map<String, ArrayList<Object>>>
	 * 
	 * @throws BusinessException
	 */
	public <T> ArrayList<TreeMap<String, ArrayList<String>>> checkEntitiesDiferences(Object oldValue, Object newValue)
			throws BusinessException {

		Field[] fields = getFields(oldValue.getClass());
		List<String> skip = getSkipFields(oldValue);
		ArrayList<TreeMap<String, ArrayList<String>>> unEquals = new ArrayList<TreeMap<String, ArrayList<String>>>(
				fields.length);

		for (Field field : fields) {
			Object oldFieldValue = getValueOfField(field, oldValue);
			Object newFieldValue = getValueOfField(field, newValue);

			if (!String.valueOf(oldFieldValue).equals(String.valueOf(newFieldValue))) {
				if (skip.contains(field.getName())) {
					continue;
				}
				ArrayList<String> list = new ArrayList<String>(2);
				list.add(String.valueOf(oldFieldValue));
				list.add(String.valueOf(newFieldValue));
				TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
				map.put(field.getName().toUpperCase(), (ArrayList<String>) list);
				unEquals.add(map);
			}
		}
		return unEquals;
	}
}
