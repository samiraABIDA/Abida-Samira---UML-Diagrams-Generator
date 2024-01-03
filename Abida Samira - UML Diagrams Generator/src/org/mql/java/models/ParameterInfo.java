package org.mql.java.models;

public class ParameterInfo {
    private String name;
    private Class<?> type;

    public ParameterInfo() {
    }

    public ParameterInfo(String name, Class<?> type) {
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

	@Override
    public String toString() {
        return "ParameterInfo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
