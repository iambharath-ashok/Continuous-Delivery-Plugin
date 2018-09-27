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
public abstract class AbstractOracleDataBase extends AbstarctDataBase {

	private Connection IT1000Connection;
	private Connection IT2000Connection;
	private Connection IT3000Connection;
	private Connection IT4000Connection;
	private Connection IT6000Connection;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	protected AbstractOracleDataBase(final String PROPERTY_URL, final String PROPERTY_DRIVER,
			final String PROPERTY_USERNAME, final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	@Override
	public Connection getConnection(final DATA_BASE_TYPE dataBase) throws ClassNotFoundException, SQLException {
		switch (dataBase) {
		case IT1000:
			return IT1000Connection == null ? IT1000Connection = getConnection()
					: IT1000Connection.isClosed() ? IT1000Connection = getConnection() : IT1000Connection;
		case IT2000:
			return IT2000Connection == null ? IT2000Connection = getConnection()
					: IT2000Connection.isClosed() ? IT2000Connection = getConnection() : IT2000Connection;
		case IT3000:
			return IT3000Connection == null ? IT3000Connection = getConnection()
					: IT3000Connection.isClosed() ? IT3000Connection = getConnection() : IT3000Connection;
		case IT4000:
			return IT4000Connection == null ? IT4000Connection = getConnection()
					: IT4000Connection.isClosed() ? IT4000Connection = getConnection() : IT4000Connection;
		case IT6000:
			return IT6000Connection == null ? IT6000Connection = getConnection()
					: IT6000Connection.isClosed() ? IT6000Connection = getConnection() : IT6000Connection;
		default:
			break;
		}
		return null;
	}

}
