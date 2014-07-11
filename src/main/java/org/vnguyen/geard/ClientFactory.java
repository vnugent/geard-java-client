package org.vnguyen.geard;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;


public class ClientFactory {
	static {
		ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
		factory.registerProvider(JacksonJaxbJsonProvider.class);
		RegisterBuiltin.register(factory);		
	}
	public static GearAPI createAPI(URI uri, String userName, String password) {
		HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		HttpClient httpClient =  HttpClientBuilder.create().setConnectionManager(cm).build();
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(httpClient);
        ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine).build();
		client.register(JacksonJaxbJsonProvider.class);
    
        return client.target(uri).proxy(GearAPI.class);
	}
	
	public static GearAPI createAPI(String url, String userName, String password) throws URISyntaxException {
		URI uri = new URI(url);
		return createAPI(uri, userName, password);
	}
}
