package org.vnguyen.geard;

import java.net.URI;
import java.net.URISyntaxException;

public class GeardClient implements GearAPI {

	protected GearAPI api;
	protected URI uri;
	
	public GeardClient(String url) {
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
		api = RESTFactory.createAPI(uri, null, null);
	}
	
	public String install(String name, GearDefinition def) {
		return api.install(name, def);
	}

	public String start(String name) {
		return api.start(name);
	}

	public String status(String name) {
		return api.status(name);
	}

	public String list() {
		return api.list();
	}
	
	public URI getURI() {
		return uri;
	}

	public String stop(String name) {
		return api.stop(name);
	}

}
