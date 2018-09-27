/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import com.ctl.ci.dao.ApplicationDao;
import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class ApplicationService implements ContinousDeliveryOptions {
	
	public static volatile ApplicationService INSTANCE = getInstance();

	private ApplicationService() {
	}

	private static ApplicationService getInstance() {
		if (INSTANCE == null) {
			synchronized (ApplicationService.class) {
				if (INSTANCE == null) {
					INSTANCE = new ApplicationService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String ... inputs)
			throws SQLException, ClassNotFoundException {
		ApplicationDao applicationDao = (ApplicationDao) DAOFactory.APPLICATION.getDaoInstance(dataBaseType);
		return applicationDao.findAllApplications();
	}

}
