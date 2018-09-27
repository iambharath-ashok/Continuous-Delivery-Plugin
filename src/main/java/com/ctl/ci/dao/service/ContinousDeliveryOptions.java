/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;

/**
 * @author AB40286
 *
 */
public interface ContinousDeliveryOptions {

	/**
	 * @param dataBaseType
	 * @param inputs
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws SQLException, ClassNotFoundException;
}
