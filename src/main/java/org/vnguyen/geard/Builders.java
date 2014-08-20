package org.vnguyen.geard;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.dockerjava.core.DockerClientImpl;

public class Builders {

	protected GeardClient geardClient;
	protected DockerHelperService dockerService;
	
	public Builders(GeardClient geard) {
		this.geardClient = geard;
		final String nodeHost = geard.getURI().getHost();

		System.setProperty("docker.io.version", "1.12");
		dockerService = new DockerHelperService(new DockerClientImpl("http://" + nodeHost + ":2375"));
	}
	
	/**
	 * Create a GearBuilder from gear definition json.  Sample json:
	 * <pre>
	 *{@code
	 *{"Image": "openshift/busybox-http-app", 
	 * "Started":true, 
	 * "Ports":[{"Internal":8080}], 
	 * "NetworkLinks": [{
	 *		"FromHost": "127.0.0.1",
	 *	 	"FromPort": 8081, 
	 *		"ToHost": "9.8.23.14",
	 *		"ToPort": 8080}]
	 * }
	 *}
	 * </pre>
	 * @param json
	 * @return GearBuilder
	 */
	public GearBuilder buildFromJSON(String json) {
		GearDefinition gearDef = load(GearDefinition.class, json);

		GearBuilder builder = new GearBuilder()
									.setGeardClient(geardClient)
									.setDockerService(dockerService)
									.setGearDefinition(gearDef);
		return builder;
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
	    mapper.setPropertyNamingStrategy(new  PropertyNamingStrategy.PascalCaseStrategy());
	    try {
			return mapper.readValue(json, clz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 		
	}	
}
