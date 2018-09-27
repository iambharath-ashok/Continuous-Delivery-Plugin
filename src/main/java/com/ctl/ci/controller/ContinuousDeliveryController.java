/**
 * 
 */
package com.ctl.ci.controller;

import java.sql.SQLException;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.factory.DAOFactory;
import com.ctl.ci.service.ContinuousDeliveryService;

import hudson.util.ListBoxModel;

/**
 * @author AB40286
 *
 */
public final class ContinuousDeliveryController {

	/**
	 * @param dataBaseName
	 * @return ListBoxModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ListBoxModel getApplicationsListBoxModel(final DATA_BASE_TYPE dataBaseName)
			throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(DATA_BASE_TYPE.SQLSERVER.toString(),
				DAOFactory.APPLICATION);
	}

	/**
	 * @return ListBoxModel
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ListBoxModel getEnvironmentListBoxModel(final DATA_BASE_TYPE dataBaseName,
			final String applicationName) throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(DATA_BASE_TYPE.SQLSERVER.toString(),
				DAOFactory.ENVIRONMENT, applicationName);
	}

	/**
	 * @return ListBoxModel
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ListBoxModel getProjectListBoxModel(final DATA_BASE_TYPE dataBaseName)
			throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(DATA_BASE_TYPE.SQLSERVER.toString(),
				DAOFactory.PROJECT);
	}

	/**
	 * @param string
	 * @return ListBoxModel
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ListBoxModel getDimensionProductListBoxModel(final String dataBaseName)
			throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(dataBaseName, DAOFactory.DIMENSIONS_PRODUCT);
	}

	/**
	 * @param dataBaseName
	 * @param worksetName
	 * @return ListBoxModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ListBoxModel getDimensionWorksetListBoxModel(final String dataBaseName,
			final String dimensionProducts) throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(dataBaseName, DAOFactory.DIMENSIONS_WORKSET,
				dimensionProducts);
	}

	/**
	 * @param dataBaseName
	 * @param worksetAndProductName
	 * @return ListBoxModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ListBoxModel getDimensionSMRListBoxModel(final String dataBaseName,
			final String... worksetAndProductName) throws ClassNotFoundException, SQLException {
		return ContinuousDeliveryService.getListBoxModel(dataBaseName, DAOFactory.DIMENSIONS_SMR,
				worksetAndProductName);
	}

	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getReleaseMonthListBoxModel() {
		return ContinuousDeliveryService.getReleaseMonthListBoxModel();
	}
	
	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getRequestTypeListBoxModel() {
		return ContinuousDeliveryService.getRequestTypeListBoxModel();
	}
	
	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getDimensionsDatabaseListBoxModel() {
		return ContinuousDeliveryService.getDimensionsDatabaseListBoxModel();
	}
}
