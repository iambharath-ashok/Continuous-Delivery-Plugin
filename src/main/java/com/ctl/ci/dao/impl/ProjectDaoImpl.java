/**
 * 
 */
package com.ctl.ci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ctl.ci.dao.AbstractSQLServerDataBase;
import com.ctl.ci.dao.ProjectDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class ProjectDaoImpl extends AbstractSQLServerDataBase implements ProjectDao {

	private static volatile ProjectDaoImpl INSTANCE;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private ProjectDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER, final String PROPERTY_USERNAME,
			final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	/**
	 * @param dataBaseName
	 * @return
	 */
	public static ProjectDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		if (INSTANCE == null) {
			synchronized (ProjectDaoImpl.class) {
				if (INSTANCE == null) {
					final DAOProperties daoProperties = getDAOProperties();
					INSTANCE = new ProjectDaoImpl(daoProperties.getProperty(dataBaseName, URl, true),
							daoProperties.getProperty(dataBaseName, DRIVER, true),
							daoProperties.getProperty(dataBaseName, USER_NAME, true),
							daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAllProjects() throws SQLException, ClassNotFoundException {
		try (final Connection connection = getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_PROJECTS);
				final ResultSet resultSet = preparedStatement.executeQuery();) {
			final List<String> projectList = new ArrayList<String>(resultSet.getFetchSize());
			while (resultSet.next()) {
				projectList.add(resultSet.getString("PROJECTNAME"));
			}
			return Collections.unmodifiableList(projectList);
		} catch (Throwable throwable) {
			throw new DAOConfigurationException(throwable.getMessage());
		}
	}

}
