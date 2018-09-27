/**
 * 
 */
package com.ctl.ci.sts;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import com.ctl.ci.components.STSInput;
import com.ctl.ci.components.STSOutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author AB40286
 *
 */
public abstract class AbstractSTSService implements STSService {

	private final STS_TYPE type;

	/**
	 * @param requestType
	 */
	protected AbstractSTSService(final STS_TYPE type) {
		this.type = type;
	}

	/**
	 * @return HttpURLConnection
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 */
	protected HttpsURLConnection prepareHttpURLConnection() throws IOException, KeyStoreException,
			NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
		final URL url = new URL(STS_REQUEST_END_POINT_URI + type.getType());
		final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		httpsURLConnection.setRequestMethod(HttpMethod.POST.name());
		httpsURLConnection.setDoOutput(Boolean.TRUE);
		httpsURLConnection.setRequestProperty("Content-Type", MimeType.APPLICATION_JSON_UTF8.getValue());
		httpsURLConnection.setRequestProperty("Accept", MimeType.APPLICATION_JSON.getValue());
		final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(new FileInputStream(CERTS_KEYSTORE_FILE_NAME), CERTS_KEYSTORE_PASSWORD.toCharArray());
		final KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		keyManagerFactory.init(keyStore, CERTS_KEYSTORE_PASSWORD.toCharArray());
		final KeyStore trustStoreCACert = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStoreCACert.load(new FileInputStream(CERTS_TRUSTSTORE_FILE_NAME), CERTS_TRUSTSTORE_PASSWORD.toCharArray());
		final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
		trustManagerFactory.init(trustStoreCACert);
		final SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
		final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
		return httpsURLConnection;
	}

	/**
	 * @param httpURLConnection
	 * @return
	 * @throws IOException
	 */
	protected DataOutputStream prepareDataOutputStream(final HttpsURLConnection httpsURLConnection) throws IOException {
		return new DataOutputStream(httpsURLConnection.getOutputStream());
	}

	@Override
	public HttpsURLConnection callSTSService(final STSInput stsInput) throws IOException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
		final HttpsURLConnection httpsURLConnection = prepareHttpURLConnection();
		final DataOutputStream dataOutputStream = prepareDataOutputStream(httpsURLConnection);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(dataOutputStream, stsInput);
		dataOutputStream.flush();
		dataOutputStream.close();
		return httpsURLConnection;
	}

	@Override
	public String getSTSResponse(final HttpsURLConnection httpsURLConnection) throws IOException {
		if (httpsURLConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
			final BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(httpsURLConnection.getInputStream()));
			String inputLine;
			final StringBuffer response = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			bufferedReader.close();
			return response.toString();
		}
		return "";
	}

	@Override
	public abstract STSOutput processResponse(final String response)
			throws JsonParseException, JsonMappingException, IOException;

}
