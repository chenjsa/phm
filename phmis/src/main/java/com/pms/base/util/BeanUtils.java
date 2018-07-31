package com.pms.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * 
 * <li>类型名称：
 * <li>说明：扩展Apache Commons BeanUtils, 提供一些反射方面缺失功能的封装.
 * <li>创建人： 陈嗣洪
 * <li>创建日期：2008-10-14
 * <li>修改人： 
 * <li>修改日期：
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	protected static final Log logger = LogFactory.getLog(BeanUtils.class);

	/**
	 * 
	 * <li>说明：
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	private BeanUtils() {
	}
	
	/**
	 * 
	 * <li>方法名：getDeclaredField
	 * <li>@param object
	 * <li>@param propertyName
	 * <li>@return
	 * <li>@throws NoSuchFieldException 如果没有该Field时抛出.
	 * <li>返回类型：Field
	 * <li>说明：循环向上转型,获取对象的DeclaredField.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 
	 * <li>方法名：getDeclaredField
	 * <li>@param clazz
	 * <li>@param propertyName
	 * <li>@return
	 * <li>@throws NoSuchFieldException  如果没有该Field时抛出.
	 * <li>返回类型：Field
	 * <li>说明：循环向上转型,获取对象的DeclaredField.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Field getDeclaredField(Class clazz, String propertyName) throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
	}

	/**
	 * 
	 * <li>方法名：forceGetProperty
	 * <li>@param object
	 * <li>@param propertyName
	 * <li>@return
	 * <li>@throws NoSuchFieldException 如果没有该Field时抛出.
	 * <li>返回类型：Object
	 * <li>说明： 暴力获取对象变量值,忽略private,protected修饰符的限制.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Object forceGetProperty(Object object, String propertyName) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);

		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}
	
	/**
	 * 
	 * <li>方法名：forceSetProperty
	 * <li>@param object
	 * <li>@param propertyName
	 * <li>@param newValue
	 * <li>@throws NoSuchFieldException 如果没有该Field时抛出.
	 * <li>返回类型：void
	 * <li>说明：暴力设置对象变量值,忽略private,protected修饰符的限制.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static void forceSetProperty(Object object, String propertyName, Object newValue)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		Field field = getDeclaredField(object, propertyName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			logger.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}
		
	/**
	 * 
	 * <li>方法名：invokePrivateMethod
	 * <li>@param object
	 * <li>@param methodName
	 * <li>@param params
	 * <li>@return
	 * <li>@throws NoSuchMethodException 如果没有该Method时抛出.
	 * <li>返回类型：Object
	 * <li>说明：暴力调用对象函数,忽略private,protected修饰符的限制.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Object invokePrivateMethod(Object object, String methodName, Object... params)
			throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class clazz = object.getClass();
		Method method = null;
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				// 方法不在当前类定义,继续向上转型
			}
		}

		if (method == null)
			throw new NoSuchMethodException("No Such Method:" + clazz.getSimpleName() + methodName);

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * 
	 * <li>方法名：getFieldsByType
	 * <li>@param object
	 * <li>@param type
	 * <li>@return
	 * <li>返回类型：List<Field>
	 * <li>说明：按Filed的类型取得Field列表.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static List<Field> getFieldsByType(Object object, Class type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * 
	 * <li>方法名：getPropertyType
	 * <li>@param type
	 * <li>@param name
	 * <li>@return
	 * <li>@throws NoSuchFieldException
	 * <li>返回类型：Class
	 * <li>说明：按FiledName获得Field的类型.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Class getPropertyType(Class type, String name) throws NoSuchFieldException {
		return getDeclaredField(type, name).getType();
	}

	/**
	 * 
	 * <li>方法名：getGetterName
	 * <li>@param type
	 * <li>@param fieldName
	 * <li>@return
	 * <li>返回类型：String
	 * <li>说明：获得field的getter函数名称.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static String getGetterName(Class type, String fieldName) {
		Assert.notNull(type, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (type.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * 
	 * <li>方法名：getGetterMethod
	 * <li>@param type
	 * <li>@param fieldName
	 * <li>@return
	 * <li>返回类型：Method
	 * <li>说明：获得field的getter函数,如果找不到该方法,返回null.
	 * <li>创建人：陈嗣洪
	 * <li>创建日期：2008-10-14
	 * <li>修改人： 
	 * <li>修改日期：
	 */
	public static Method getGetterMethod(Class type, String fieldName) {
		try {
			return type.getMethod(getGetterName(type, fieldName));
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
