package org.vnguyen.geard;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class DefaultServiceEndpointImpl implements ServiceEndpoint {

	protected String name;
	protected String id;
	protected String ip;
	protected int publicPort;
	protected int internalPort;
	protected String internalIP;
	
	public String name() {
		return name;
	}

	public String uniqueID() {
		return id;
	}

	public String ip() {
		return ip;
	}

	public int publicPort() {
		return publicPort;
	}

	public int internalPort() {
		return internalPort;
	}

	public String toString() {
		return String.format("[ip:%s->%s, port %s->%s]", internalIP, ip, internalPort, publicPort );
	}
	
	public String toStringSimple() {
		return String.format("[%s:%s]", ip, publicPort);
	}
	
	public int hashCode() {
	     return new HashCodeBuilder(13, 37).
	       append(ip).
	       append(publicPort).
	       append(internalPort).
	       append(id).
	       toHashCode();
	}
	
	public boolean equals(Object obj) {
	       if (!(obj instanceof DefaultServiceEndpointImpl))
	            return false;
	        if (obj == this)
	            return true;

	        DefaultServiceEndpointImpl rhs = (DefaultServiceEndpointImpl) obj;
	        return new EqualsBuilder().
	            append(id, rhs.id).
	            append(ip, rhs.ip).
	            append(publicPort, rhs.publicPort).
	            append(internalPort, rhs.internalPort).
	            isEquals();
	  }

	public String internalIP() {
		return internalIP;
	}	
}
