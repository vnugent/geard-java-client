package org.vnguyen.geard;

public interface ServiceEndpoint {
	String name();
	String uniqueID();
	String ip();
	int publicPort();
	int internalPort();
}
