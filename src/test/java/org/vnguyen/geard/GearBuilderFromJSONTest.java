package org.vnguyen.geard;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GearBuilderFromJSONTest {

	private Builders builder;
	@BeforeTest
	public void setup() throws Exception {
		GeardClient geardClient = new GeardClient("http://localhost:43273");
		builder = new Builders(geardClient);
	}
	

	@Test
	public void loadFromJSON() throws Exception {
		final String json="{\"Image\": \"openshift/busybox-http-app\", \"Started\":true, \"Ports\":[{\"Internal\":8080}]}";
		Gear psqlGear = builder.buildFromJSON(json)
								.build();
		
		Map<Integer, ServiceEndpoint> endpoints = psqlGear.endpoints();
		
		ServiceEndpoint e = endpoints.get(8080);
		Assert.assertEquals(e.internalPort(), 8080, "internal port");
		Assert.assertNotNull(e.publicPort(), "external port");
		Assert.assertNotNull(e.internalIP(), "internal ip");
		Assert.assertNotNull(e.name(), "container name");
		
		HttpAsserts.assertEquals("http://" + e.internalIP()+":"+e.internalPort(), "Hello world!", "verify using internal ip/port");
		HttpAsserts.assertEquals("http://localhost:" + e.publicPort(), "Hello world!", "verify using public ip/port");

	}
}
