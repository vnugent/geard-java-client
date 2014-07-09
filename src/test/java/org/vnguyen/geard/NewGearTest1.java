package org.vnguyen.geard;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;


@Test
public class NewGearTest1 {
	private  GearAPI api = null; 
	
	@BeforeTest
	public void setup() throws Exception {
		api = ClientFactory.createAPI("http://10.16.23.108:43273/", null, null);
	}

	
	@Test
	public void testNewPSQL() throws Exception {
		GearDefinition psqlGear = new GearDefinition();
		
		psqlGear.image="vnguyen/rhq-psql";
		psqlGear.started=true;
		psqlGear.ports = ImmutableList.of(new PortsDefinition(5432, 30010));
		
		api.install("test-psql30010", psqlGear);
	}
}
