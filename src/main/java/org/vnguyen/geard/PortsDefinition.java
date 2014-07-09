package org.vnguyen.geard;

import org.codehaus.jackson.annotate.JsonProperty;

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
