package org.vnguyen.geard;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GearBuilderTest {

	private Builders builder;
	@BeforeTest
	public void setup() throws Exception {
		GeardClient geardClient = new GeardClient("http://localhost:43273");
		builder = new Builders(geardClient);
	}
	

	@Test
	public void loadTest() throws Exception {

		Gear psqlGear = builder.fromTemplate("src/test/resources/gear/busybox-http.json")
								.withNamePrefix("http-")
								.build();
		
		Map<Integer, ServiceEndpoint> endpoints = psqlGear.endpoints();
		ServiceEndpoint e = endpoints.get(8080);
		Assert.assertEquals(e.internalPort(), 8080, "internal port");
		Assert.assertNotNull(e.publicPort(), "external port");
		Assert.assertNotNull(e.ip(), "container ip");
		Assert.assertNotNull(e.name(), "container name");
	}
}
