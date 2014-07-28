package org.vnguyen.geard;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.client.DockerClient;
import com.google.common.collect.ImmutableList;

import org.testng.Assert;
import org.testng.Reporter;


@Test
public class NewGearAPITest {
	private  GearAPI api; 
	private DockerHelperService docker;
	
	@BeforeTest
	public void setup() throws Exception {
		String geardHost = System.getProperty("GEARD_HOST", "http://127.0.0.1:43273");
		
		api = RESTFactory.createAPI(geardHost, null, null);
		
		docker = new DockerHelperService();
	}

	
	@Test
	public void testNewGearCreation() throws Exception {
		GearDefinition gear = new GearDefinition();
		
		gear.image="openshift/busybox-http-app";
		gear.started=true;
		gear.ports = ImmutableList.of(new PortsDefinition(8080, 0));
		String gearName = "geard-java-" + RandomStringUtils.randomAlphanumeric(5) ;
		api.install(gearName, gear);
		
		Map<Integer, ServiceEndpoint> endpoints = docker.getServiceEndpointsFromContainer(gearName, 10000);
		Assert.assertNotNull(endpoints);
		ServiceEndpoint busyboxEndpoint = endpoints.get(8080);
		Assert.assertNotNull(busyboxEndpoint);
		Assert.assertTrue(busyboxEndpoint.publicPort() > 0, "public http port");
		Reporter.log("busybox endpoint: "+ busyboxEndpoint);
	}
}
