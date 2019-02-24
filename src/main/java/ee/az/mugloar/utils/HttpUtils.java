package ee.az.mugloar.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpUtils {
	private static Logger logger = Logger.getLogger(HttpUtils.class);

	private static CloseableHttpClient httpClient;

	public static String post(String addr) throws Exception {
		return post(addr, null);
	}

	public static String post(String addr, Map<String, String> params) throws Exception {
		logger.debug("POST: Connecting to " + addr);
		
		CloseableHttpClient httpclient = getHttpClient();
		HttpPost httpPost = new HttpPost(addr);
		if (params != null) {
			List <NameValuePair> nvps = new ArrayList <NameValuePair>(params.size());
			for (String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key, params.get(key)));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		}
		CloseableHttpResponse response = httpclient.execute(httpPost);
		StringBuilder sb = new StringBuilder();
		try {
			logger.debug("Server responded " + response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	logger.debug(line);
		    	sb.append(line).append("\n");
		    }
			EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return sb.toString();
	}

	public static String get(String addr) throws Exception {
		return get(addr, null);
	}
	
	public static String get(String addr, Map<String, String> params) throws Exception {
		logger.debug("GET: Connecting to " + addr);

		URI address;
		if (params != null) {
			URIBuilder ub = new URIBuilder(addr);
			for (String key : params.keySet()) {
				ub.addParameter(key, params.get(key));
			}
			address = ub.build();
		} else {
			address = new URIBuilder(addr).build();
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(address);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		StringBuilder sb = new StringBuilder();
		try {
			logger.debug("Server responded " + response.getStatusLine());
		    HttpEntity entity = response.getEntity();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	logger.debug(line);
		    	sb.append(line).append("\n");
		    }
			EntityUtils.consume(entity);
		} finally {
		    response.close();
		}
		return sb.toString();
	}
	
	private static CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
		return httpClient;
	}
}
