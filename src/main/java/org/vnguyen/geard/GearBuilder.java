package org.vnguyen.geard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.vnguyen.geard.DockerHelperService;
import org.vnguyen.geard.Gear;
import org.vnguyen.geard.ServiceEndpoint;

import com.google.common.collect.ImmutableList;

public class GearBuilder {

	protected GearDefinition gearDefinition;
	protected GeardClient geardClient;
	protected String gearName = RandomStringUtils.randomAlphanumeric(7);
	protected List<Gear> linkDestinations = new ArrayList<Gear>();
	protected Environment env = new Environment();
	protected DockerHelperService dockerSvc;
	
	
	public GearBuilder setDockerService(DockerHelperService docker) {
		dockerSvc = docker;
		return this;
	}
	public GearBuilder withNamePrefix(String prefix) {
		this.gearName = prefix + this.gearName;
		return this;
	}
	
	public GearBuilder withEnvironment(Environment env) {
		this.env = env;
		return this;
	}
	
	public GearBuilder setGearDefinition(GearDefinition def) {
		this.gearDefinition = def;
		return this;
	}
	
	public GearBuilder linkTo(Gear destGear) {
		linkDestinations.add(destGear);
		return this;
	}
	
	public Gear build() {
		gearDefinition.env = env;
		doLinkGear();
		geardClient.install(this.gearName, this.gearDefinition);
		geardClient.start(this.gearName);

		Map<Integer, ServiceEndpoint> endpoints = dockerSvc.getServiceEndpointsFromContainer(this.gearName, 30000);
		Gear gear = new Gear(gearName, gearDefinition, endpoints);
		return gear;
	}
	
	private void doLinkGear() {
		for (Gear destGear : linkDestinations) {
			for(NetworkLinksDefinition srcNet : gearDefinition.links) {
				if (destGear.endpoints().containsKey(srcNet.fromPort)) {
					ServiceEndpoint destEndpoint = destGear.endpoints().get(srcNet.fromPort);
					srcNet.toHost = destEndpoint.ip();
					srcNet.toPort = destEndpoint.publicPort();
				}
			}
		}
	}

	public GearBuilder setGeardClient(GeardClient geardClient) {
		this.geardClient = geardClient;
		return this;
	}
	
}
