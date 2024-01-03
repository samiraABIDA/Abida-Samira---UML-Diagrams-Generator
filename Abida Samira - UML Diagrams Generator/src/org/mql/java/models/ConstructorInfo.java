package org.mql.java.models;

import java.util.List;

public class ConstructorInfo {
    private int modifiers;
    private List<ParameterInfo> parameters;

    public ConstructorInfo() {
    }

    public ConstructorInfo(int modifiers, List<ParameterInfo> parameters) {
        this.modifiers = modifiers;
        this.parameters = parameters;
    }



    public int getModifiers() {
		return modifiers;
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}

	public List<ParameterInfo> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterInfo> parameters) {
		this.parameters = parameters;
	}

	@Override
    public String toString() {
        return "ConstructorInfo{" +
                "modifiers=" + modifiers +
                ", parameters=" + parameters +
                '}';
    }
}
