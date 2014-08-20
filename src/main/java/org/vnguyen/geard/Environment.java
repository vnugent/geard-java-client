package org.vnguyen.geard;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Environment {
	public String id;
	
	public List<EnvVariable> vars;
	
	@JsonCreator
	public static Environment create(@JsonProperty("Id") String id,
									 @JsonProperty("Variables") List<EnvVariable> vars) {
		Environment env = new Environment();
		env.id = id;
		env.vars =vars;
		return env;
	}
	
	public int hashCode() {
	     return new HashCodeBuilder(7, 37).
	       append(id).
	       append(vars).
	       toHashCode();
	}
	
	public boolean equals(Object obj) {
	       if (!(obj instanceof Environment))
	            return false;
	        if (obj == this)
	            return true;

	        Environment rhs = (Environment) obj;
	        return new EqualsBuilder().
	            append(id, rhs.id).
	            append(vars, rhs.vars).
	            isEquals();
	  }	
}
