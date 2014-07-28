package org.vnguyen.geard;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.dockerjava.client.DockerClient;

public class Builders {

	protected GeardClient geardClient;
	protected DockerHelperService dockerService;
	
	public Builders(GeardClient geard) {
		this.geardClient = geard;
		final String nodeHost = geard.getURI().getHost();

		System.setProperty("docker.io.version", "1.12");
		dockerService = new DockerHelperService(new DockerClient("http://" + nodeHost + ":2375"));
	}
	
	
	public GearDefinition buildFromJSON(String json) {
		return null;
	}
	
	public GearBuilder fromTemplate(String jsonFile) {
		GearDefinition gearDef = load(GearDefinition.class, new File(jsonFile));
		gearDef.started = false;
		
		GearBuilder builder = new GearBuilder()
									.setGeardClient(geardClient)
									.setDockerService(dockerService)
									.setGearDefinition(gearDef);
		return builder;
	}
		
	
	public static <T> T load(Class<T> clz, File jsonFile) {
	    ObjectMapper mapper = new ObjectMapper();  
	    mapper.setPropertyNamingStrategy(new  PropertyNamingStrategy.PascalCaseStrategy());
	    try {
			return mapper.readValue(jsonFile, clz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 		
	}	
	
	public  static <T> T load(Class<T> clz, String json) {
	    ObjectMapper mapper = new ObjectMapper();  
	    mapper.setPropertyNamingStrategy(new  PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy());
	    try {
			return mapper.readValue(json, clz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 		
	}	
}
