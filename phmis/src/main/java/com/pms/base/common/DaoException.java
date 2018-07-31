/**
 * <li>文件名：DaoException.java
 * <li>说明：
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-11-12
 * <li>修改人： 
 * <li>修改日期：
 */
package com.pms.base.common;

/**
 * <li>类型名称：
 * <li>说明：
 * <li>创建人： 曾明辉
 * <li>创建日期：2008-11-12
 * <li>修改人： 
 * <li>修改日期：
 */
@SuppressWarnings("serial")
public class DaoException extends BusinessException {

	

	public DaoException(String message) {
		super(message);
	}


	public DaoException(Throwable cause) {
		super(cause);
	}
}
