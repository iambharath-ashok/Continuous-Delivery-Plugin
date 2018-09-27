/**
 * 
 */
package com.ctl.ci.properties;

/**
 * @author AB40286
 *
 */
public interface IProperties {

	String DAO_PROPERTIES = "properties/dao.properties";
	String APPLICATION_PROPERTIES = "properties/application.properties";
	String STS_END_POINT_URI_PROPERTY = "sts.request.end.point.uri";
	String CERTS_TRUSTSTORE_FILE_NAME_PROPERTY = "certs.ssl.trustStore.file.name";
	String CERTS_KEYSTORE_FILE_NAME_PROPERTY = "certs.ssl.keyStore.file.name";
	String CERTS_TRUSTSTORE_PASSWORD_PROPERTY = "certs.ssl.trustStore.password";
	String CERTS_KEYSTORE_PASSWORD_PROPERTY = "certs.ssl.keyStore.password";
	String JAVAX_NET_SSL_TRUSTSTORE = "javax.net.ssl.trustStore";
	String JAVAX_NET_SSL_TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";
	String JAVAX_NET_SSL_KEYSTORE = "javax.net.ssl.keyStore";
	String JAVAX_NET_SSL_KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword";
	String DIMENSION_CODE_VERSION = "DIMENSION_CODE_VERSION";
	String TEST_ENVIRONMENT = "TEST_ENVIRONMENT";
	String SPECIAL_REQUESTS = "N";
	String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	String BOUNCE_TYPE = "Bounce-Hard";
	String BOUNCE_REASON = "JENKINS INITIATED BOUNCE";
	String CLIENT_TYPE = "Jenkins";
	String TIME_ZONE = "UTC";
	String STS_REQUEST_STATUS_LINK = "http://et.dev.qintra.com/stsclient/STS_Status.aspx?ID=";
	String STS_INSTALL_REQUEST_LINK_MESSAGE = "Link to the Install Request Status : ";
	String STS_BOUNCE_REQUEST_LINK_MESSAGE = "Link to the Bounce Request Status : ";
	String SIMPLE_DATE_FORMAT_MONTH = "MMMMM";
	String REQUEST_TYPE = "REQUEST_TYPE";
	String STS_APPLICATION = "STS_APPLICATION";
	String STS_MAILING_LIST = "STS_MAILING_LIST";
	String STS_MONTH = "STS_MONTH";
	String STS_ENVIRONMENT = "STS_ENVIRONMENT";
	String APPLICATION = "APPLICATION";
	String MAILING_LIST = "MAILING_LIST";
	String MONTH = "MONTH";
	String CODE_FREEZE_DATE_FORMAT = "d/MM/yyyy";

}
