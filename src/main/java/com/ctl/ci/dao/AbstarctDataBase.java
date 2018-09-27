/**
 * 
 */
package com.ctl.ci.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public abstract class AbstarctDataBase implements DataBase {

	protected final String PROPERTY_URL;
	protected final String PROPERTY_DRIVER;
	protected final String PROPERTY_USERNAME;
	protected final String PROPERTY_PASSWORD;
	protected final DATA_BASE_TYPE DATA_BASE;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 * @param DATA_BASE
	 */
	protected AbstarctDataBase(final String PROPERTY_URL, final String PROPERTY_DRIVER, final String PROPERTY_USERNAME,
			final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		this.PROPERTY_URL = PROPERTY_URL;
		this.PROPERTY_DRIVER = PROPERTY_DRIVER;
		this.PROPERTY_USERNAME = PROPERTY_USERNAME;
		this.PROPERTY_PASSWORD = PROPERTY_PASSWORD;
		this.DATA_BASE = DATA_BASE;
	}

	@Override
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(PROPERTY_DRIVER);
		return DriverManager.getConnection(PROPERTY_URL, PROPERTY_USERNAME, PROPERTY_PASSWORD);
	}

	public abstract Connection getConnection(DATA_BASE_TYPE dataBase) throws ClassNotFoundException, SQLException;

	/**
	 * @return
	 */
	protected static DAOProperties getDAOProperties() {
		return DAOProperties.getInstance();
	}

}
