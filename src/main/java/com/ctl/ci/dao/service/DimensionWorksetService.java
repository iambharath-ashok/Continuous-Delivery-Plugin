/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.DimensionWorksetDao;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class DimensionWorksetService implements ContinousDeliveryOptions {

	public static volatile DimensionWorksetService INSTANCE = getInstance();

	private DimensionWorksetService() {
	}

	private static DimensionWorksetService getInstance() {
		if (INSTANCE == null) {
			synchronized (DimensionWorksetService.class) {
				if (INSTANCE == null) {
					INSTANCE = new DimensionWorksetService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws SQLException, ClassNotFoundException {
		DimensionWorksetDao dimensionWorksetDao = (DimensionWorksetDao) DAOFactory.DIMENSIONS_WORKSET
				.getDaoInstance(dataBaseType);
		if (!ArrayUtils.isEmpty(inputs)) {
			String dimensionProductName = inputs[0];
			if (StringUtils.isNotBlank(dimensionProductName)) {
				return dimensionWorksetDao.findDimensionWorksetByDimensionProductName(dimensionProductName);
			}
		}
		return null;
	}
}
