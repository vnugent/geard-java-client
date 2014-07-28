package org.vnguyen.geard;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.vnguyen.geard.ServiceEndpoint;

/**
 * Run time representation of a Gear
 * @author vnguyen
 *
 */
public class Gear {
	private GeardClient geardClient;
	private GearDefinition gearDefinition;
	private Map<Integer, ServiceEndpoint> endpoints;
	private String containerName;
	
	public Gear(String containerName, GearDefinition gearDefinition, Map<Integer, ServiceEndpoint> endpoints) {
		this.gearDefinition = gearDefinition;
		this.endpoints = endpoints;
		this.containerName = containerName;
	}

	public Gear setGeardClient(GeardClient client) {
		this.geardClient = client;
		return this;
	}

	public Map<Integer, ServiceEndpoint> endpoints() {
		return this.endpoints;
	}

	public GearDefinition gearDefinition() {
		return this.gearDefinition;
	}
	
	public Gear start() {
		geardClient.start(containerName);
		return this;
	}
	
	public Gear stop() {
		geardClient.stop(containerName);
		return this;
	}
	

	
	
	public String toString() {
		return new ToStringBuilder(this).append("name", containerName).toString();
	}
}
