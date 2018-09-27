/**
 * 
 */
package com.ctl.ci.dao.factory;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.GenericDao;
import com.ctl.ci.dao.impl.ApplicationDaoImpl;
import com.ctl.ci.dao.impl.DimensionProductDaoImpl;
import com.ctl.ci.dao.impl.DimensionSMRDaoImpl;
import com.ctl.ci.dao.impl.DimensionWorksetDaoImpl;
import com.ctl.ci.dao.impl.EnvironmentDaoImpl;
import com.ctl.ci.dao.impl.ProjectDaoImpl;
import com.ctl.ci.dao.service.ApplicationService;
import com.ctl.ci.dao.service.ContinousDeliveryOptions;
import com.ctl.ci.dao.service.DimensionProductService;
import com.ctl.ci.dao.service.DimensionSMRService;
import com.ctl.ci.dao.service.DimensionWorksetService;
import com.ctl.ci.dao.service.EnvironmentService;
import com.ctl.ci.dao.service.ProjectService;

/**
 * @author AB40286
 *
 */
public enum DAOFactory {
	OPTIONS("Options"), APPLICATION("Application"), ENVIRONMENT("Environment"), PROJECT("Project"), DIMENSIONS_PRODUCT(
			"Dimensions Product"), DIMENSIONS_WORKSET("Dimensions Workset"), DIMENSIONS_SMR("Dimensions SMR");

	private String continuousDeliveryOption;

	public String getContinuousDeliveryOption() {
		return continuousDeliveryOption;
	}

	private DAOFactory(final String continuousDeliveryOption) {
		this.continuousDeliveryOption = continuousDeliveryOption;
	}

	/**
	 * @param dataBaseName
	 * @return GenericDao
	 */
	public GenericDao getDaoInstance(final DATA_BASE_TYPE dataBaseName) {
		switch (this) {
		case APPLICATION:
			return ApplicationDaoImpl.getInstance(dataBaseName);
		case ENVIRONMENT:
			return EnvironmentDaoImpl.getInstance(dataBaseName);
		case PROJECT:
			return ProjectDaoImpl.getInstance(dataBaseName);
		case DIMENSIONS_PRODUCT:
			return DimensionProductDaoImpl.getInstance(dataBaseName);
		case DIMENSIONS_WORKSET:
			return DimensionWorksetDaoImpl.getInstance(dataBaseName);
		case DIMENSIONS_SMR:
			return DimensionSMRDaoImpl.getInstance(dataBaseName);
		default:
			break;
		}
		return null;
	}

	/**
	 * @param instance
	 * @return ContinousDeliveryOptions
	 */
	public ContinousDeliveryOptions getInstance(final DAOFactory instance) {
		switch (instance) {
		case APPLICATION:
			return ApplicationService.INSTANCE;
		case ENVIRONMENT:
			return EnvironmentService.INSTANCE;
		case PROJECT:
			return ProjectService.INSTANCE;
		case DIMENSIONS_PRODUCT:
			return DimensionProductService.INSTANCE;
		case DIMENSIONS_WORKSET:
			return DimensionWorksetService.INSTANCE;
		case DIMENSIONS_SMR:
			return DimensionSMRService.INSTANCE;
		default:
			break;
		}
		return null;
	}
}
