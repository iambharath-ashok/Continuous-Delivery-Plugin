/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.DimensionProductDao;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class DimensionProductService implements ContinousDeliveryOptions {

	public static volatile DimensionProductService INSTANCE = getInstance();

	private DimensionProductService() {
	}

	private static DimensionProductService getInstance() {
		if (INSTANCE == null) {
			synchronized (DimensionProductService.class) {
				if (INSTANCE == null) {
					INSTANCE = new DimensionProductService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws SQLException, ClassNotFoundException {
		DimensionProductDao dimensionProductDao = (DimensionProductDao) DAOFactory.DIMENSIONS_PRODUCT
				.getDaoInstance(dataBaseType);
		return dimensionProductDao.findAllDimensionProducts();
	}
}
