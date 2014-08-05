package org.vnguyen.geard;

public interface ServiceEndpoint {
	String name();
	String uniqueID();
	String ip();
	String internalIP();
	int publicPort();
	int internalPort();
	String toStringSimple();
}
