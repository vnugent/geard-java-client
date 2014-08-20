package org.vnguyen.geard;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvVariable {
	@JsonProperty("Name")
	public String name;
	
	@JsonProperty("Value")
	public String value;
	
	public EnvVariable() {}
	
	public EnvVariable(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public int hashCode() {
	     return new HashCodeBuilder(7, 37).
	       append(name).
	       append(value).
	       toHashCode();
	}	
	
	
	public boolean equals(Object obj) {
	       if (!(obj instanceof EnvVariable))
	            return false;
	        if (obj == this)
	            return true;

	        EnvVariable rhs = (EnvVariable) obj;
	        return new EqualsBuilder().
	            append(name, rhs.name).
	            append(value, rhs.value).
	            isEquals();
	  }	
}
