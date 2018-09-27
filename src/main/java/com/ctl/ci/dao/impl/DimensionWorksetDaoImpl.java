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

import com.ctl.ci.dao.AbstractOracleDataBase;
import com.ctl.ci.dao.DimensionWorksetDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.exceptions.DAOException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class DimensionWorksetDaoImpl extends AbstractOracleDataBase implements DimensionWorksetDao {

	private static volatile DimensionWorksetDaoImpl IT1000DimensionWorksetDaoImpl;
	private static volatile DimensionWorksetDaoImpl IT2000DimensionWorksetDaoImpl;
	private static volatile DimensionWorksetDaoImpl IT3000DimensionWorksetDaoImpl;
	private static volatile DimensionWorksetDaoImpl IT4000DimensionWorksetDaoImpl;
	private static volatile DimensionWorksetDaoImpl IT6000DimensionWorksetDaoImpl;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private DimensionWorksetDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER,
			final String PROPERTY_USERNAME, final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	public static DimensionWorksetDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		final DAOProperties daoProperties = getDAOProperties();
		synchronized (DimensionWorksetDaoImpl.class) {
			switch (dataBaseName) {
			case IT1000:
				return IT1000DimensionWorksetDaoImpl == null
						? IT1000DimensionWorksetDaoImpl = new DimensionWorksetDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT1000DimensionWorksetDaoImpl;
			case IT2000:
				return IT2000DimensionWorksetDaoImpl == null
						? IT2000DimensionWorksetDaoImpl = new DimensionWorksetDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT2000DimensionWorksetDaoImpl;
			case IT3000:
				return IT3000DimensionWorksetDaoImpl == null
						? IT3000DimensionWorksetDaoImpl = new DimensionWorksetDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT3000DimensionWorksetDaoImpl;
			case IT4000:
				return IT4000DimensionWorksetDaoImpl == null
						? IT4000DimensionWorksetDaoImpl = new DimensionWorksetDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT4000DimensionWorksetDaoImpl;
			case IT6000:
				return IT6000DimensionWorksetDaoImpl == null
						? IT6000DimensionWorksetDaoImpl = new DimensionWorksetDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT6000DimensionWorksetDaoImpl;

			default:
				break;
			}
		}
		return null;
	}

	@Override
	public List<String> findDimensionWorksetByDimensionProductName(final String dimensionProductName)
			throws SQLException, ClassNotFoundException {
		if (StringUtils.isNotBlank(dimensionProductName)) {
			try (final Connection connection = getConnection();
					final PreparedStatement preparedStatement = connection
							.prepareStatement(SQL_FIND_ALL_DIMENSION_WORKSET_BY_DIMENSION_PRODUCT_NAME);) {
				preparedStatement.setQueryTimeout(ORACLE_QUERY_TIME_OUT_SECONDS);
				preparedStatement.setString(1, dimensionProductName.trim());
				try (final ResultSet resultSet = preparedStatement.executeQuery();) {
					final List<String> dimensionWorksetList = new ArrayList<String>(resultSet.getFetchSize());
					while (resultSet.next()) {
						dimensionWorksetList.add(resultSet.getString("WORKSET_NAME"));
					}
					return Collections.unmodifiableList(dimensionWorksetList);
				}
			} catch (Throwable throwable) {
				throw new DAOConfigurationException(throwable.getMessage());
			}
		} else {
			throw new DAOException("Dimension Product is not populated. DimensionProduct: " + dimensionProductName);
		}

	}
}
