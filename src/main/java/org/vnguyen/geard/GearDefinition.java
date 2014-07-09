package org.vnguyen.geard;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Sample Gear definition json
 * 
 * {"Image": "vnguyen/rhq-psql", 
 * 	"Started":true, 
 * 	"Ports":[{"Internal":5432, "External":30000}],
 * 	"NetworkLinks": [
 *   {
 *     "FromHost": "127.0.0.1",
 *     "FromPort": 5432,
 *     "ToHost": "10.16.23.108",
 *     "ToPort": 30010
 *   }
 *   ]
 *  }
 *
 */
public class GearDefinition {

	@JsonProperty("image")
	public String image;
	
	@JsonProperty("started")
	public boolean started;
	
	@JsonProperty("ports")
	public List<PortsDefinition> ports;

	@JsonProperty("networklinks")
	public List<NetworkLinksDefinition> links;
	
	
	public static NetworkLinksDefinition links() {
		return new NetworkLinksDefinition();
	}
	/**
	 * Link between containers
	 *
	 */
	public static class NetworkLinksDefinition {
		
		@JsonProperty("FromHost")
		public String fromHost;
		
		@JsonProperty("FromPort")
		public int fromPort;
		
		@JsonProperty("ToHost")
		public String toHost;

		@JsonProperty("ToPort")
		public int toPort;
				
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
		
	}
}
