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

import org.apache.commons.lang.StringUtils;

import com.ctl.ci.dao.AbstractSQLServerDataBase;
import com.ctl.ci.dao.EnvironmentDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.exceptions.DAOException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class EnvironmentDaoImpl extends AbstractSQLServerDataBase implements EnvironmentDao {

	private static volatile EnvironmentDaoImpl INSTANCE;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private EnvironmentDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER, final String PROPERTY_USERNAME,
			final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	/**
	 * @param dataBaseName
	 * @return
	 */
	public static EnvironmentDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		if (INSTANCE == null) {
			synchronized (EnvironmentDaoImpl.class) {
				if (INSTANCE == null) {
					final DAOProperties daoProperties = getDAOProperties();
					INSTANCE = new EnvironmentDaoImpl(daoProperties.getProperty(dataBaseName, URl, true),
							daoProperties.getProperty(dataBaseName, DRIVER, true),
							daoProperties.getProperty(dataBaseName, USER_NAME, true),
							daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findEnvironmentByApplicationName(final String applicationName)
			throws SQLException, ClassNotFoundException {
		if (StringUtils.isNotBlank(applicationName)) {
			try (final Connection connection = getConnection();
					final PreparedStatement preparedStatement = connection
							.prepareStatement(SQL_FIND_ALL_ENVIRONMENTS_BY_APPLICATION_NAME);) {
				preparedStatement.setString(1, "%" + applicationName.trim() + "%");
				try (final ResultSet resultSet = preparedStatement.executeQuery();) {
					final List<String> environmentList = new ArrayList<String>(resultSet.getFetchSize());
					while (resultSet.next()) {
						environmentList.add(resultSet.getString("TEST_ENVIRONMENT"));
					}
					return Collections.unmodifiableList(environmentList);
				}
			} catch (Throwable throwable) {
				throw new DAOConfigurationException(throwable.getMessage());
			}
		} else {
			throw new DAOException("Application is not populated. Application: " + applicationName);
		}
	}
}