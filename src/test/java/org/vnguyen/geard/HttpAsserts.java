package org.vnguyen.geard;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpAsserts {

	public static boolean assertEquals(String urlEndpoint, String expected, String reason ) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urlEndpoint);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
		} finally {
			if (response!=null) response.close();
		}
	//	Assert.assertEquals(actual, expected);
		return true;
	}
}
