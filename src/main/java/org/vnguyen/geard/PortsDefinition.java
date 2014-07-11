package org.vnguyen.geard;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PortsDefinition {

	@JsonProperty
	public int internal;
	
	@JsonProperty
	public int external;
	
	PortsDefinition(int internal, int external) {
		this.internal = internal;
		this.external = external;
	}
}
