/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.EnvironmentDao;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class EnvironmentService implements ContinousDeliveryOptions {

	public static volatile EnvironmentService INSTANCE = getInstance();

	private EnvironmentService() {
	}

	private static EnvironmentService getInstance() {
		if (INSTANCE == null) {
			synchronized (EnvironmentService.class) {
				if (INSTANCE == null) {
					INSTANCE = new EnvironmentService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws ClassNotFoundException, SQLException {
			EnvironmentDao environmentDao = (EnvironmentDao) DAOFactory.ENVIRONMENT
					.getDaoInstance(dataBaseType);
			if(!ArrayUtils.isEmpty(inputs)){
				String applicationName = inputs[0];
				if(StringUtils.isNotBlank(applicationName)){
					return environmentDao.findEnvironmentByApplicationName(applicationName);
				}
			}
			return null;
	}

}
