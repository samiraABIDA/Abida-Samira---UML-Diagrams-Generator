package org.mql.java.models;

import java.util.List;

public class MethodInfo {
    private String methodName;
    private Class<?> returnType;
    private int modifiers;
    private List<ParameterInfo> parameters;

    public MethodInfo() {
    }

    public MethodInfo(String methodName, Class<?> returnType, int modifiers, List<ParameterInfo> parameters) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.modifiers = modifiers;
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
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
        return "MethodInfo{" +
                "methodName='" + methodName + '\'' +
                ", returnType=" + returnType +
                ", modifiers=" + modifiers +
                ", parameters=" + parameters +
                '}';
    }
}
