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
public interface ProjectDao extends GenericDao {
	String SQL_FIND_ALL_PROJECTS = "SELECT DISTINCT(PROJECTNAME)  FROM PROJECT_LIST";

	/**
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findAllProjects() throws SQLException, ClassNotFoundException;
}
