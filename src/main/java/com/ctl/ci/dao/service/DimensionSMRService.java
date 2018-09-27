/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.DimensionSMRDao;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class DimensionSMRService implements ContinousDeliveryOptions {

	public static volatile DimensionSMRService INSTANCE = getInstance();

	private DimensionSMRService() {
	}

	private static DimensionSMRService getInstance() {
		if (INSTANCE == null) {
			synchronized (DimensionSMRService.class) {
				if (INSTANCE == null) {
					INSTANCE = new DimensionSMRService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws SQLException, ClassNotFoundException {
		DimensionSMRDao dimensionSMRDao = (DimensionSMRDao) DAOFactory.DIMENSIONS_SMR.getDaoInstance(dataBaseType);
		if (!ArrayUtils.isEmpty(inputs)) {
			String dimensionProductName = inputs[0];
			String dimensionWorksetName = inputs[1];
			if (StringUtils.isNotBlank(dimensionProductName) && StringUtils.isNotBlank(dimensionWorksetName)) {
				return dimensionSMRDao.findDimnesionSMRByDimensionProductNameAndDimensionWorksetName(dimensionProductName,
						dimensionWorksetName);
			}
		}
		return null;
	}

}