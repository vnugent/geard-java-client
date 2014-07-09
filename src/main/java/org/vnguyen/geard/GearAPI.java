package org.vnguyen.geard;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

public interface GearAPI {
	
	@PUT
	@Path("/container/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String install(@PathParam("name") String name, GearDefinition def);
	
	
	
	
	@PUT 
	@Path("/container/{name}/started")
	@Consumes(MediaType.APPLICATION_JSON)
	public String start(@PathParam("name") String name);	
	
	
}
