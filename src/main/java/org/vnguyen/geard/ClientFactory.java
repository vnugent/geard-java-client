package org.vnguyen.geard;

import java.net.URI;
import java.net.URISyntaxException;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;


public class ClientFactory {

	public static GearAPI createAPI(URI uri, String userName, String password) {
        ResteasyClient client = new ResteasyClientBuilder().build();
    
        return client.target(uri).proxy(GearAPI.class);
	}
	
	public static GearAPI createAPI(String url, String userName, String password) throws URISyntaxException {
		URI uri = new URI(url);
		return createAPI(uri, userName, password);
	}
}
