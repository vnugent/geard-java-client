package org.vnguyen.geard;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface GearAPI {
	
	@PUT
	@Path("/container/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("text/plain")	
	public String install(@PathParam("name") String name, GearDefinition def);
	
	
	
	
	
	@PUT 
	@Path("/container/{name}/started")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String start(@PathParam("name") String name);	
	
	@GET 
	@Path("/container/{name}/status")
	@Produces(MediaType.APPLICATION_JSON)
	public String status(@PathParam("name") String name);		
	
	@GET 
	@Path("/containers")
	@Produces(MediaType.APPLICATION_JSON)
	public String list();	
	

}
