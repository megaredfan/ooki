package org.kriver.core.common;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class HttpUtils {
	private final static Logger log = Logger.getLogger(HttpUtils.class);
	
    private static final int MAX_TIMEOUT = 7000;
    private static RequestConfig requestConfig;
    private static SSLConnectionSocketFactory sslsf;
    
    static {  
        RequestConfig.Builder configBuilder = RequestConfig.custom();  
        // 设置连接超时  
        configBuilder.setConnectTimeout(MAX_TIMEOUT);  
        // 设置读取超时  
        configBuilder.setSocketTimeout(MAX_TIMEOUT);  
        // 设置从连接池获取连接实例的超时  
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        
        requestConfig = configBuilder.build();
    	try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
  
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
                    return true;  
                }  
            }).build();  
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
        } catch (GeneralSecurityException e) {  
            log.error("GeneralSecurityException", e);
        }  
    }
    
	public static JSONObject sendHttpsMessage(String url, String args,String token){
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(requestConfig).build();  
        HttpPost httpPost = new HttpPost(url);  
        CloseableHttpResponse response = null;  
        String httpStr = null;  
		
        try {  
            httpPost.setConfig(requestConfig);  
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            if(StringUtils.isNotBlank(token)){
            	httpPost.setHeader("Authorization", "Bearer " +token);
            }
            
            StringEntity entity = new StringEntity(args, "utf-8");  
            entity.setContentEncoding("utf-8");    
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);  
            HttpEntity result = response.getEntity();  
            
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
               throw new RuntimeException("httpCode = "+statusCode + ",HttpEntity =="+result);
            }
            httpStr = EntityUtils.toString(result, "utf-8");  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {
            if (response != null) {  
                try {  
                	response.close();
                	httpClient.close();
                } catch (IOException e) {  
                	 e.printStackTrace();  
                }  
            }  
        }
        return (JSONObject)JSONValue.parse(httpStr);
	}
}
