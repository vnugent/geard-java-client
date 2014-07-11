package org.vnguyen.geard;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;
import org.testng.Assert;


@Test
public class NewGearTest1 {
	private  GearAPI api = null; 
	
	@BeforeTest
	public void setup() throws Exception {
		String geardHost = System.getProperty("GEARD_HOST");
		Assert.assertNotNull(geardHost, "-DGEARD_HOST=http://<geard host:port>");
		api = ClientFactory.createAPI(geardHost, null, null);
	}

	
	@Test
	public void testNewGearCreation() throws Exception {
		GearDefinition gear = new GearDefinition();
		
		gear.image="openshift/busybox-http-app";
		gear.started=true;
		gear.ports = ImmutableList.of(new PortsDefinition(8080, 0));
		String gearName = "geard-java-" + RandomStringUtils.randomAlphanumeric(5) ;
		api.install(gearName, gear);
		Assert.assertNotNull(api.status(gearName));
	}
}
