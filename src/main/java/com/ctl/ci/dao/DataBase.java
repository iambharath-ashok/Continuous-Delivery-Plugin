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
public interface DataBase {

	String URl = "jdbc.url";
	String DRIVER = "jdbc.driver";
	String USER_NAME = "jdbc.username";
	String PASSWORD = "jdbc.password";

	public enum DATA_BASE_TYPE {
		SQLSERVER, IT1000, IT2000, IT3000, IT4000, IT6000;
	}

	/**
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Connection getConnection() throws SQLException, ClassNotFoundException;

	/**
	 * @param dataBase
	 * @return Connection
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Connection getConnection(final DATA_BASE_TYPE dataBase) throws SQLException, ClassNotFoundException;

}
