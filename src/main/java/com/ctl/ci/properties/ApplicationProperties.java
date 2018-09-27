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

import com.ctl.ci.exceptions.ApplicationConfigurationException;

/**
 * @author AB40286
 *
 */
public class ApplicationProperties implements IProperties {

	private static final String PROPERTIES_FILE = APPLICATION_PROPERTIES;
	private static final Properties PROPERTIES = new Properties();
	public static volatile ApplicationProperties applicationProperties;

	public static ApplicationProperties getInstance() throws ApplicationConfigurationException {
		if (applicationProperties == null) {
			synchronized (ApplicationProperties.class) {
				if (applicationProperties == null) {
					applicationProperties = new ApplicationProperties();
				}
			}
		}
		return applicationProperties;
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
			throw new ApplicationConfigurationException(
					"Properties file '" + PROPERTIES_FILE + "' is missing in location "+ new File(PROPERTIES_FILE).getAbsolutePath());
		}
		try {
			PROPERTIES.load(propertiesFile);
		} catch (IOException e) {
			throw new ApplicationConfigurationException("Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
		}
	}

	public String getProperty(final String key, final boolean mandatory) throws ApplicationConfigurationException {
		String property = PROPERTIES.getProperty(key);

		if (property == null || property.trim().length() == 0) {
			if (mandatory) {
				throw new ApplicationConfigurationException("Required property '" + key + "'"
						+ " is missing in properties file '" + PROPERTIES_FILE + "'.");
			} else {
				property = null;
			}
		}
		return property;
	}
	
	private ApplicationProperties() throws ApplicationConfigurationException {
	}

}
