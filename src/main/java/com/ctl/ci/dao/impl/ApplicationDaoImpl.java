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
import com.ctl.ci.dao.ApplicationDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class ApplicationDaoImpl extends AbstractSQLServerDataBase implements ApplicationDao {

	private static volatile ApplicationDaoImpl INSTANCE;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private ApplicationDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER, final String PROPERTY_USERNAME,
			final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	public static ApplicationDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		if (INSTANCE == null) {
			synchronized (ApplicationDaoImpl.class) {
				if (INSTANCE == null) {
					final DAOProperties daoProperties = getDAOProperties();
					INSTANCE = new ApplicationDaoImpl(daoProperties.getProperty(dataBaseName, URl, true),
							daoProperties.getProperty(dataBaseName, DRIVER, true),
							daoProperties.getProperty(dataBaseName, USER_NAME, true),
							daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName);
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAllApplications() throws SQLException, ClassNotFoundException {
		try (final Connection connection = getConnection();
				final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_APPLICATIONS);
				final ResultSet resultSet = preparedStatement.executeQuery()) {
			final List<String> applicationList = new ArrayList<String>(resultSet.getFetchSize());
			while (resultSet.next()) {
				applicationList.add(resultSet.getString("APPLIC_MAL"));
			}
			return Collections.unmodifiableList(applicationList);
		} catch (Throwable throwable) {
			throw new DAOConfigurationException(throwable.getMessage());
		}

	}
}
