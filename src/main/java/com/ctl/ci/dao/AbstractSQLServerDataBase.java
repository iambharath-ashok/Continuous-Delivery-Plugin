/**
 * 
 */
package com.ctl.ci.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author AB40286
 *
 */
public abstract class AbstractSQLServerDataBase extends AbstarctDataBase {
	private Connection sqlServerConnection;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	protected AbstractSQLServerDataBase(final String PROPERTY_URL, final String PROPERTY_DRIVER,
			final String PROPERTY_USERNAME, final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	@Override
	public Connection getConnection(final DATA_BASE_TYPE dataBase) throws ClassNotFoundException, SQLException {
		if (dataBase.toString().equalsIgnoreCase(DATA_BASE_TYPE.SQLSERVER.toString())) {
			if (sqlServerConnection == null) {
				synchronized (AbstractSQLServerDataBase.class) {
					if (sqlServerConnection == null) {
						sqlServerConnection = getConnection();
					}
				}
			}
			return sqlServerConnection;
		}
		return null;
	}

}
