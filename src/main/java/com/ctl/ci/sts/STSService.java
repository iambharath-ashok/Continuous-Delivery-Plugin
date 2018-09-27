/**
 * 
 */
package com.ctl.ci.sts;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.ctl.ci.cause.determinant.BuildUser;
import com.ctl.ci.components.STSInput;
import com.ctl.ci.components.STSOutput;
import com.ctl.ci.properties.ApplicationProperties;
import com.ctl.ci.properties.IProperties;
import com.ctl.ci.stapler.recorder.ContinuousDeliveryRecorder.ContinuousDeliveryOptions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @author AB40286
 *
 */
public interface STSService extends IProperties {

	String STS_REQUEST_END_POINT_URI = ApplicationProperties.getInstance().getProperty(STS_END_POINT_URI_PROPERTY,
			true);
	String CERTS_TRUSTSTORE_FILE_NAME = ApplicationProperties.getInstance()
			.getProperty(CERTS_TRUSTSTORE_FILE_NAME_PROPERTY, true);
	String CERTS_KEYSTORE_FILE_NAME = ApplicationProperties.getInstance().getProperty(CERTS_KEYSTORE_FILE_NAME_PROPERTY,
			true);
	String CERTS_TRUSTSTORE_PASSWORD = ApplicationProperties.getInstance()
			.getProperty(CERTS_TRUSTSTORE_PASSWORD_PROPERTY, true);
	String CERTS_KEYSTORE_PASSWORD = ApplicationProperties.getInstance().getProperty(CERTS_KEYSTORE_PASSWORD_PROPERTY,
			true);

	/**
	 * @param stsInput
	 * @return HttpURLConnection
	 * @throws IOException
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyStoreException 
	 * @throws KeyManagementException 
	 * @throws UnrecoverableKeyException 
	 */
	HttpsURLConnection callSTSService(final STSInput stsInput) throws IOException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException;

	/**
	 * @param httpURLConnection
	 * @return String
	 * @throws IOException
	 */
	String getSTSResponse(final HttpsURLConnection httpsURLConnection) throws IOException;

	/**
	 * @param response
	 * @return STSOutput
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	STSOutput processResponse(final String response) throws JsonParseException, JsonMappingException, IOException;

	/**
	 * @author AB40286
	 *
	 */
	public enum STS_TYPE {
		BOUNCE("bounce"), INSTALL("request");

		private final String type;

		/**
		 * @param requestType
		 */
		private STS_TYPE(final String type) {
			this.type = type;
		}

		/**
		 * @return String
		 */
		public String getType() {
			return type;
		}

		/**
		 * @return STSRequest
		 */
		public STSService getInstance() {
			switch (this) {
			case BOUNCE:
				return new STSBounceService(STS_TYPE.BOUNCE);
			case INSTALL:
				return new STSInstallService(STS_TYPE.INSTALL);
			default:
				return null;
			}
		}
	}

	/**
	 * @param continuousDeliveryOptionsBean
	 * @param buildUser
	 * @param buildMap
	 * @return STSInput
	 */
	STSInput buildRequestBean(final ContinuousDeliveryOptions continuousDeliveryOptionsBean, final BuildUser buildUser,
			final Map<String, String> buildMap);;
}
