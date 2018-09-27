/**
 * 
 */
package com.ctl.ci.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.exceptions.DAOConfigurationException;

/**
 * @author AB40286
 *
 */
public class DAOProperties implements IProperties {

	private static final String PROPERTIES_FILE = DAO_PROPERTIES;
	private static final Properties PROPERTIES = new Properties();
	public static volatile DAOProperties daoProperties;

	public static DAOProperties getInstance() {
		if (daoProperties == null) {
			synchronized (DAOProperties.class) {
				if (daoProperties == null) {
					daoProperties = new DAOProperties();
				}
			}
		}
		return daoProperties;
	}

	static {
		// ClassLoader classLoader =
		// Thread.currentThread().getContextClassLoader();
		// InputStream propertiesFile =
		// classLoader.getResourceAsStream(PROPERTIES_FILE);
		InputStream propertiesFile = null;
		try {
			propertiesFile = new FileInputStream(new File(PROPERTIES_FILE).getAbsolutePath());
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}
		if (propertiesFile == null) {
			throw new DAOConfigurationException("Properties file '" + PROPERTIES_FILE + "' is missing in location "+ new File(PROPERTIES_FILE).getAbsolutePath());
		}
		try {
			PROPERTIES.load(propertiesFile);
		} catch (IOException e) {
			throw new DAOConfigurationException("Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
		}
	}

	private DAOProperties() throws DAOConfigurationException {
	}

	public String getProperty(final DATA_BASE_TYPE dataBaseName, final String key, final boolean mandatory)
			throws DAOConfigurationException {
		final String fullKey = dataBaseName.toString().toLowerCase() + "." + key;
		String property = PROPERTIES.getProperty(fullKey);

		if (property == null || property.trim().length() == 0) {
			if (mandatory) {
				throw new DAOConfigurationException("Required property '" + fullKey + "'"
						+ " is missing in properties file '" + PROPERTIES_FILE + "'.");
			} else {
				property = null;
			}
		}
		return property;
	}

}
