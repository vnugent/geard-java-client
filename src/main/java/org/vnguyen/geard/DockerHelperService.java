package org.vnguyen.geard;

import java.util.Map;
import java.util.Map.Entry;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.NotFoundException;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports.Binding;
import com.github.dockerjava.core.DockerClientImpl;
import com.google.common.collect.Maps;

public class DockerHelperService {

	private DockerClient dockerClient;
	private String nodePublicAddress = System.getProperty("geard.node.address", "localhost");

	public DockerHelperService() {
		dockerClient = new DockerClientImpl("http://127.0.0.1:2375");
	}
	
	public DockerHelperService(DockerClient client) {
		this.dockerClient = client;
	}
	
	public Map<Integer, ServiceEndpoint> getServiceEndpointsFromContainer(String containerName) {
		Map<Integer, ServiceEndpoint> endpoints = Maps.newHashMap();

		InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(containerName).exec();
		for(Entry<ExposedPort, Binding> e: containerInfo.getHostConfig().getPortBindings().getBindings().entrySet()) {
			DefaultServiceEndpointImpl endpoint = new DefaultServiceEndpointImpl();
			endpoint.internalIP = containerInfo.getNetworkSettings().getIpAddress();
			endpoint.name = containerName;
		
			endpoint.ip = nodePublicAddress; // need a better way to figure out host IP here
			endpoint.internalPort = e.getKey().getPort();
			endpoint.publicPort = e.getValue().getHostPort();
			
			endpoints.put(new Integer(endpoint.internalPort), endpoint);
		}			
		return endpoints;
		
	}
	
	public Map<Integer, ServiceEndpoint>  getServiceEndpointsFromContainer(final String containerName, long timeout) {
		long timerCounter = 0;
		Map<Integer, ServiceEndpoint> result = null;
		while(timerCounter <= timeout) {
			try {
				result = getServiceEndpointsFromContainer(containerName);
				return result;
			} catch (NotFoundException e) {
				try {
					Thread.sleep(500);
					timerCounter = timerCounter + 500;
				} catch (InterruptedException e2) {
					throw new RuntimeException("Interrupted");
				}
			} 	
		}
		
		if (result == null) {
			throw new RuntimeException("Can't get container info from Docker");
		} 
			
		return result;
		
	}
}
