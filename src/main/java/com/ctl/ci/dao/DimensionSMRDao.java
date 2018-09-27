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
public interface DimensionSMRDao extends GenericDao {
	String SQL_FIND_ALL_DIMENSION_SMR_BY_DIMENSION_PRODUCT_NAME_AND_DIMENSION_WORKSET_NAME = "SELECT DISTINCT CD.CH_DOC_ID, CD.ACTION_DATE FROM PCMS_ITEM_DATA ID, PCMS_CHDOC_DATA CD, PCMS_CHDOC_RELATED_ITEMS RI, PCMS_WORKSET_ITEMS WI, PCMS_WORKSET_INFO WF "
			+ "WHERE CD.CH_UID=RI.FROM_CH_UID AND RI.TO_ITEM_UID=ID.ITEM_UID  AND ID.ITEM_UID=WI.ITEM_UID AND WI.WORKSET_UID=WF.WORKSET_UID AND ID.PRODUCT_ID=WF.PRODUCT_ID AND CD.STATUS IN ('BUILD COMPLETE','BUILD NOT REQUIRED','CLOSED','IMPLEMENTED','PLANNED','SUBMITTED','PRODUCTION COMPLETE','PRODUCTION READY','PROD MIGR COMPLETE','REQUESTED') "
			+ "AND ID.PRODUCT_ID = ? AND WF.WORKSET_NAME = ? "
			+ "ORDER BY CD.ACTION_DATE DESC";

	/**
	 * @param product
	 * @param workset
	 * @return List<String>
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<String> findDimnesionSMRByDimensionProductNameAndDimensionWorksetName(final String dimensionProductName,
			final String dimensionWorksetName) throws SQLException, ClassNotFoundException;
}
