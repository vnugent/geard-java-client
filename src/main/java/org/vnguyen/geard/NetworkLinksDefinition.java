package org.vnguyen.geard;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Link between containers
 *
 */
public class NetworkLinksDefinition {
	
	//@JsonProperty("FromHost")
	public String fromHost;
	
	//@JsonProperty("FromPort")
	public int fromPort;
	
	//@JsonProperty("ToHost")
	public String toHost;

	//@JsonProperty("ToPort")
	public int toPort;
	
	public NetworkLinksDefinition(){}

	@JsonCreator
	public static NetworkLinksDefinition create(@JsonProperty("FromHost") String fromHost, 
												@JsonProperty("FromPort") int fromPort, 
												@JsonProperty("ToHost") String toHost, 
												@JsonProperty("ToPort") int toPort) {
		NetworkLinksDefinition net = new NetworkLinksDefinition();
		net.from(fromHost, fromPort)
			.to(toHost, toPort);
		return net;
	}
	
	public NetworkLinksDefinition from(String host, int port) {
		fromHost = host;
		fromPort = port;
		return this;
	}
	
	public NetworkLinksDefinition to(String host, int port) {
		toHost = host;
		toPort = port;
		return this;
	}
	
	public int hashCode() {
	     return new HashCodeBuilder(17, 37).
	       append(fromHost).
	       append(fromPort).
	       append(toHost).
	       append(toPort).
	       toHashCode();
	}
	
	public boolean equals(Object obj) {
	       if (!(obj instanceof NetworkLinksDefinition))
	            return false;
	        if (obj == this)
	            return true;

	        NetworkLinksDefinition rhs = (NetworkLinksDefinition) obj;
	        return new EqualsBuilder().
	            append(fromHost, rhs.fromHost).
	            append(fromPort, rhs.fromPort).
	            append(toPort, rhs.toPort).
	            append(toHost, rhs.toHost).
	            isEquals();
	  }
	
	public String toString() {
		     return String.format("[%s:%d:%s:%d]", fromHost, fromPort, toHost, toPort );
		   }	
}