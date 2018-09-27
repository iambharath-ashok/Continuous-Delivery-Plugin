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
import com.ctl.ci.dao.DimensionSMRDao;
import com.ctl.ci.exceptions.DAOConfigurationException;
import com.ctl.ci.exceptions.DAOException;
import com.ctl.ci.properties.DAOProperties;

/**
 * @author AB40286
 *
 */
public class DimensionSMRDaoImpl extends AbstractOracleDataBase implements DimensionSMRDao {

	private static volatile DimensionSMRDaoImpl IT1000DimensionSMRDaoImpl;
	private static volatile DimensionSMRDaoImpl IT2000DimensionSMRDaoImpl;
	private static volatile DimensionSMRDaoImpl IT3000DimensionSMRDaoImpl;
	private static volatile DimensionSMRDaoImpl IT4000DimensionSMRDaoImpl;
	private static volatile DimensionSMRDaoImpl IT6000DimensionSMRDaoImpl;

	/**
	 * @param PROPERTY_URL
	 * @param PROPERTY_DRIVER
	 * @param PROPERTY_USERNAME
	 * @param PROPERTY_PASSWORD
	 */
	private DimensionSMRDaoImpl(final String PROPERTY_URL, final String PROPERTY_DRIVER, final String PROPERTY_USERNAME,
			final String PROPERTY_PASSWORD, final DATA_BASE_TYPE DATA_BASE) {
		super(PROPERTY_URL, PROPERTY_DRIVER, PROPERTY_USERNAME, PROPERTY_PASSWORD, DATA_BASE);
	}

	public static DimensionSMRDao getInstance(final DATA_BASE_TYPE dataBaseName) {
		final DAOProperties daoProperties = getDAOProperties();
		synchronized (DimensionSMRDaoImpl.class) {
			switch (dataBaseName) {
			case IT1000:
				return IT1000DimensionSMRDaoImpl == null
						? IT1000DimensionSMRDaoImpl = new DimensionSMRDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT1000DimensionSMRDaoImpl;
			case IT2000:
				return IT2000DimensionSMRDaoImpl == null
						? IT2000DimensionSMRDaoImpl = new DimensionSMRDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT2000DimensionSMRDaoImpl;
			case IT3000:
				return IT3000DimensionSMRDaoImpl == null
						? IT3000DimensionSMRDaoImpl = new DimensionSMRDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT3000DimensionSMRDaoImpl;
			case IT4000:
				return IT4000DimensionSMRDaoImpl == null
						? IT4000DimensionSMRDaoImpl = new DimensionSMRDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT4000DimensionSMRDaoImpl;
			case IT6000:
				return IT6000DimensionSMRDaoImpl == null
						? IT6000DimensionSMRDaoImpl = new DimensionSMRDaoImpl(
								daoProperties.getProperty(dataBaseName, URl, true),
								daoProperties.getProperty(dataBaseName, DRIVER, true),
								daoProperties.getProperty(dataBaseName, USER_NAME, true),
								daoProperties.getProperty(dataBaseName, PASSWORD, true), dataBaseName)
						: IT6000DimensionSMRDaoImpl;

			default:
				break;
			}
		}
		return null;
	}

	@Override
	public List<String> findDimnesionSMRByDimensionProductNameAndDimensionWorksetName(final String dimensionProductName,
			final String dimensionWorksetName) throws SQLException, ClassNotFoundException {
		if (StringUtils.isNotBlank(dimensionProductName) && StringUtils.isNotBlank(dimensionWorksetName)) {
			try (final Connection connection = getConnection();
					final PreparedStatement preparedStatement = connection.prepareStatement(
							SQL_FIND_ALL_DIMENSION_SMR_BY_DIMENSION_PRODUCT_NAME_AND_DIMENSION_WORKSET_NAME);) {
				preparedStatement.setQueryTimeout(ORACLE_QUERY_TIME_OUT_SECONDS);
				preparedStatement.setString(1, dimensionProductName.trim());
				preparedStatement.setString(2, dimensionWorksetName.trim());
				try (final ResultSet resultSet = preparedStatement.executeQuery();) {
					final List<String> dimensionSMRList = new ArrayList<String>(resultSet.getFetchSize());
					while (resultSet.next()) {
						dimensionSMRList.add(resultSet.getString("CH_DOC_ID"));
					}
					return Collections.unmodifiableList(dimensionSMRList);
				}
			} catch (Throwable throwable) {
				throw new DAOConfigurationException(throwable.getMessage());
			}
		} else {
			throw new DAOException("Dimension Product  Or Dimension Workset is not populated. DimensionProductName: "
					+ dimensionProductName + " DimensionWorkset: " + dimensionWorksetName);
		}
	}
}