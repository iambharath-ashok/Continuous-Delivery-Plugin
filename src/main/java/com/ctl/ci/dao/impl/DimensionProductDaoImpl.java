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

import com.ctl.ci.dao.AbstractOracleDataBase;
import com.ctl.ci.dao.DimensionProductDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class DimensionProductDaoImpl extends AbstractOracleDataBase implements DimensionProductDao {

	private static volatile DimensionProductDaoImpl IT1000DimensionProductDaoImpl;
	private static volatile DimensionProductDaoImpl IT2000DimensionProductDaoImpl;
	private static volatile DimensionProductDaoImpl IT3000DimensionProductDaoImpl;
	private static volatile DimensionProductDaoImpl IT4000DimensionProductDaoImpl;
	private static volatile DimensionProductDaoImpl IT6000DimensionProductDaoImpl;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private DimensionProductDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER,
			final String PROPERTY_USERNAME, final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	public static DimensionProductDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		final DAOProperties daoProperties = getDAOProperties();
		synchronized (DimensionProductDaoImpl.class) {
			switch (dataBaseName) {
			case IT1000:
				return IT1000DimensionProductDaoImpl == null
						? IT1000DimensionProductDaoImpl = new DimensionProductDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT1000DimensionProductDaoImpl;
			case IT2000:
				return IT2000DimensionProductDaoImpl == null
						? IT2000DimensionProductDaoImpl = new DimensionProductDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT2000DimensionProductDaoImpl;
			case IT3000:
				return IT3000DimensionProductDaoImpl == null
						? IT3000DimensionProductDaoImpl = new DimensionProductDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT3000DimensionProductDaoImpl;
			case IT4000:
				return IT4000DimensionProductDaoImpl == null
						? IT4000DimensionProductDaoImpl = new DimensionProductDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT4000DimensionProductDaoImpl;
			case IT6000:
				return IT6000DimensionProductDaoImpl == null
						? IT6000DimensionProductDaoImpl = new DimensionProductDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT6000DimensionProductDaoImpl;

			default:
				break;
			}
		}
		return null;
	}

	@Override
	public List<String> findAllDimensionProducts() throws SQLException, ClassNotFoundException {
		try (final Connection connection = getConnection();
				final PreparedStatement preparedStatement = connection
						.prepareStatement(SQL_FIND_ALL_DIMENSION_PRODUCTS);
				final ResultSet resultSet = preparedStatement.executeQuery();) {
			preparedStatement.setQueryTimeout(ORACLE_QUERY_TIME_OUT_SECONDS);
			final List<String> dimensionProductList = new ArrayList<String>(resultSet.getFetchSize());
			while (resultSet.next()) {
				dimensionProductList.add(resultSet.getString("PRODUCT_ID"));
			}
			return Collections.unmodifiableList(dimensionProductList);
		} catch (Throwable throwable) {
			throw new DAOConfigurationException(throwable.getMessage());
		}
	}
}