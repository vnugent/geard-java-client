package org.vnguyen.geard;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Sample Gear definition json
 * 
 * {"Image": "openshift/busybox-http-app", 
 * 	"Started":true, 
 * 	"Ports":[{"Internal":8080, "External":30000}],
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class GearDefinition {

	@JsonProperty("Image")
	public String image;
	
	@JsonProperty("Started")
	public boolean started;
	
	@JsonProperty("Ports")
	public List<PortsDefinition> ports;

	@JsonProperty("NetworkLinks")
	public List<NetworkLinksDefinition> links;
	
	public GearDefinition() {}
	
	public static NetworkLinksDefinition makeLinks() {
		return new NetworkLinksDefinition();
	}
	
	public NetworkLinksDefinition findLinkFor(int port) {
		for(NetworkLinksDefinition def : links) {
			if (def.fromPort == port) {
				return def;
			}
		}
		throw new RuntimeException("Can't find network link for port " + port);
	}
	
	public String toString() {
		return this.image;
	}
}
