package org.vnguyen.geard;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Test
public class GearBuilderLinkingTest {
	private Builders builder;
	
	@BeforeTest
	public void setup() throws Exception {
		GeardClient geardClient = new GeardClient("http://localhost:43273");
		builder = new Builders(geardClient);
	}
	

	@Test
	public void loadTest() throws Exception {
		Gear mongodb = builder.fromTemplate("src/test/resources/gear/mongodb.json")
								.withNamePrefix("mongodb-")
								.build();

		
		Gear rockmongo = builder.fromTemplate("src/test/resources/gear/rockmongo.json")
							.withNamePrefix("rockmongo-")
							.linkTo(mongodb)
							.build();
		
		Assert.assertNotNull(mongodb.endpoints());
		Assert.assertNotNull(rockmongo.endpoints());		
	}
}
