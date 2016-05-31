package com.stayhungry.primer.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * NOTE: @Configuration is meta-annotated with @Component, therefore @Configuration classes are candidates for component 
 * scanning and therefore may also take advantage of @Autowired/@Inject at the field and method level 
 * (BUT NOT at the constructor level).
 * 
 * @author dcarrillo
 *
 */
public class HttpClientConfiguration {
	@Value("${3rdparty.webservice.username}") 
	private  String username;
	
	@Value("${3rdparty.webservice.password}")
	private  String password="";
	
	@Value("${3rdparty.webservice.timeout}")
	private  int timeout=2000;
	
	@Bean
	public HttpClient httpClient(){
		System.out.println("USER+"+timeout);
		Credentials creds = new UsernamePasswordCredentials(username, password);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, creds);
		
		RequestConfig requestConfig = 
				RequestConfig.custom()
					.setConnectionRequestTimeout(timeout)
			    	.setConnectTimeout(timeout)
			    	.setSocketTimeout(timeout).build();
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(10);
		
		CloseableHttpClient client = HttpClientBuilder
				.create()
				.setDefaultRequestConfig(requestConfig)
				.setDefaultCredentialsProvider(credsProvider)
				.setConnectionManager(cm)
				.build();
		
		return client;
	}
}