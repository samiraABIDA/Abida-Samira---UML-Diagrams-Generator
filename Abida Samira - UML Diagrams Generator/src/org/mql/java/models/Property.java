package org.mql.java.models;

public class Property {
	
	private String name;
	private Class<?> type;
	private String modifier;
	public Property() {
	
	}
	public Property(String name, Class<?> type, String modifier) {
		super();
		this.name = name;
		this.type = type;
		this.modifier = modifier;
	}
	
	public Property(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getType() {
		return type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	@Override
	public String toString() {
		return "Property [name=" + name + ", type=" + type + ", modifier=" + modifier + "]";
	}
	
	


}