package org.vnguyen.geard;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class LoadGearDefinitionTest {

	@Test
	public void loadJSON() throws Exception {
		GearDefinition def = Builders.load(GearDefinition.class, new File("src/test/resources/gear/busybox-http.json"));
		Assert.assertEquals(def.image, "openshift/busybox-http-app");
		Assert.assertTrue(def.started);
		Assert.assertEquals(def.links.size(), 1, "network links");
		NetworkLinksDefinition net = def.links.get(0);
		
//		"FromHost": "127.0.0.1",
//		"FromPort": 8081, 
//		"ToHost": "9.8.23.14",
//		"ToPort": 8080
		final NetworkLinksDefinition expected = NetworkLinksDefinition.create("127.0.0.1", 8081, "9.8.23.14", 8080);
		Assert.assertEquals(net, expected, "network links");
	}
}
