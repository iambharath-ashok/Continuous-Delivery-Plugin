/**
 * 
 */
package com.ctl.ci.dao.service;

import java.sql.SQLException;
import java.util.List;

import com.ctl.ci.dao.DataBase.DATA_BASE_TYPE;
import com.ctl.ci.dao.ProjectDao;
import com.ctl.ci.dao.factory.DAOFactory;

/**
 * @author AB40286
 *
 */
public final class ProjectService implements ContinousDeliveryOptions {

	public static volatile ProjectService INSTANCE = getInstance();

	private ProjectService() {
	}

	private static ProjectService getInstance() {
		if (INSTANCE == null) {
			synchronized (ProjectService.class) {
				if (INSTANCE == null) {
					INSTANCE = new ProjectService();
				}
			}
		}
		return INSTANCE;
	}

	@Override
	public List<String> findAll(final DATA_BASE_TYPE dataBaseType, final String... inputs)
			throws SQLException, ClassNotFoundException {
		ProjectDao projectDao = (ProjectDao) DAOFactory.PROJECT.getDaoInstance(dataBaseType);
		return projectDao.findAllProjects();
	}

}