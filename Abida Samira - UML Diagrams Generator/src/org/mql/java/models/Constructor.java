package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class Constructor {
	
	private String modifier;
	private String name;
	private List<Property> parameters;


	public Constructor() {
		this.parameters= new Vector<Property>();
	}
	
	public Constructor(String modifier, String name) {
		this();
		this.modifier= modifier;
		this.name = name;
	} 
	
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Property> getParameters() {
		return parameters;
	}
	public void setParameters(List<Property> parameters) {
		this.parameters = parameters;
	}
	public void addParameter(Property parameter) {
		this.parameters.add(parameter);
	}

	@Override
	public String toString() {
		return "Constructor [modifier=" + modifier + ", name=" + name + ", parameters=" + parameters + "]";
	}
	

}
