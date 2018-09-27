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
public interface ApplicationDao extends GenericDao {
	String SQL_FIND_ALL_APPLICATIONS = "SELECT DISTINCT(APPLIC_MAL) FROM APPLIC_GEN_INFO WHERE INSTALL_FLOW='Standard-DevOPS'";

	/**
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findAllApplications() throws SQLException, ClassNotFoundException;
}
