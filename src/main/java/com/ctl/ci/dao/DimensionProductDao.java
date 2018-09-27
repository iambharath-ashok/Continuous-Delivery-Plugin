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
public interface DimensionProductDao extends GenericDao {
	String SQL_FIND_ALL_DIMENSION_PRODUCTS = "SELECT DISTINCT (PRODUCT_ID)  FROM PCMS_CHDOC_DATA ORDER BY PRODUCT_ID";

	/**
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findAllDimensionProducts() throws SQLException, ClassNotFoundException;
}
