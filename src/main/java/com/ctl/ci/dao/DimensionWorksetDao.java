/**
 * 
 */
package com.ctl.ci.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author AB40286
 *
 */
public interface DimensionWorksetDao extends GenericDao {
	String SQL_FIND_ALL_DIMENSION_WORKSET_BY_DIMENSION_PRODUCT_NAME = "SELECT DISTINCT(WORKSET_NAME) FROM PCMS_WORKSET_INFO WHERE PRODUCT_ID = ? ORDER BY WORKSET_NAME";

	/**
	 * @param input
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findDimensionWorksetByDimensionProductName(final String dimensionProductName) throws SQLException, ClassNotFoundException;
}
