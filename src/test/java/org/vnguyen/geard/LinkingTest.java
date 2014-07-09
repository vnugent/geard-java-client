package org.vnguyen.geard;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

@Test
public class LinkingTest {
	private  GearAPI api = null; 
	
	@BeforeTest
	public void setup() throws Exception {
		api = ClientFactory.createAPI("http://10.16.23.108:43273/", null, null);
	}
	
	@Test
	public void testLinkingGear() throws Exception {
		GearDefinition gearPSQL = new GearDefinition();	
		gearPSQL.image="vnguyen/rhq-psql";
		gearPSQL.started=true;
		gearPSQL.ports = ImmutableList.of(new PortsDefinition(5432, 30020));
		
		api.install("test-psql30020", gearPSQL);
		
		GearDefinition gearJONServer = new GearDefinition();	
		gearJONServer.image="docker-registry.usersys.redhat.com/jon_qe/jon32-nodb";
		gearJONServer.started=false;
		gearJONServer.ports = ImmutableList.of(new PortsDefinition(7080, 57080));
		gearJONServer.links =  ImmutableList.of(GearDefinition.links()
																.from("127.0.0.1", 5432)
																.to("10.16.23.108", 30020));
		
		api.install("test-jon57080", gearJONServer);		
		api.start("test-jon57080");
		
		
	}

}
