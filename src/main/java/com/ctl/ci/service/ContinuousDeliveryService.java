/**
 * 
 */
package com.ctl.ci.service;

import java.sql.SQLException;
import java.util.List;

import com.ctl.ci.common.utils.DateUtils;
import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.factory.DAOFactory;
import com.ctl.ci.dao.service.ContinousDeliveryOptions;

import hudson.util.ListBoxModel;

/**
 * @author AB40286
 *
 */
public final class ContinuousDeliveryService {

	/**
	 * @param dataBaseName
	 * @param options
	 * @param inputs
	 * @return ListBoxModel
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ListBoxModel getListBoxModel(final String dataBaseName, final DAOFactory options,
			final String... inputs) throws ClassNotFoundException, SQLException {
		ListBoxModel listBoxModel = null;
		final ContinousDeliveryOptions continousDeliveryOptions = DAOFactory.OPTIONS.getInstance(options);
		List<String> modelListNames = null;

		switch (dataBaseName) {
		case "IT1000":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.IT1000, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		case "IT2000":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.IT2000, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		case "IT3000":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.IT3000, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		case "IT4000":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.IT4000, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		case "IT6000":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.IT6000, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		case "SQLSERVER":
			modelListNames = continousDeliveryOptions.findAll(DATA_BASE_TYPE.SQLSERVER, inputs);
			listBoxModel = new ListBoxModel().add("Please Select The " + options.getContinuousDeliveryOption());
			break;
		default:
			break;

		}

		if (modelListNames != null)
			for (String modelName : modelListNames)
				listBoxModel.add(modelName);

		return listBoxModel;
	}

	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getReleaseMonthListBoxModel() {
		final ListBoxModel monthsListBoxModel = new ListBoxModel(13);
		monthsListBoxModel.add("Please Select The Month");
		DateUtils.getFutureMonths().forEach(month -> monthsListBoxModel.add(month));
		return monthsListBoxModel;
	}

	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getRequestTypeListBoxModel() {
		return new ListBoxModel().add("Please Select The RequestType").add("INSTALL").add("BOUNCE");
	}

	/**
	 * @return ListBoxModel
	 */
	public static ListBoxModel getDimensionsDatabaseListBoxModel() {
		return new ListBoxModel().add("Please Select The Dimensions Database").add("IT1000").add("IT2000").add("IT3000")
				.add("IT4000").add("IT6000");
	}
}
