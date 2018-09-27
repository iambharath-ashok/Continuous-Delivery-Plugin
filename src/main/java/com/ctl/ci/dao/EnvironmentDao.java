/**
 * 
 */
package com.ctl.ci.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author AB40286
 *
 */
public interface EnvironmentDao extends GenericDao {
	String SQL_FIND_ALL_ENVIRONMENTS_BY_APPLICATION_NAME = "SELECT DISTINCT(TEST_ENVIRONMENT) FROM APPLIC_GEN_INFO WHERE APPLIC_MAL LIKE ?";

	/**
	 * @param applicationName
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findEnvironmentByApplicationName(final String applicationName)
			throws SQLException, ClassNotFoundException;
}
