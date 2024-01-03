package org.mql.java.models;



import java.util.List;

public class ClassInfo {
    private String className;
    private List<MethodInfo> methods;
    private List<ConstructorInfo> constructors;

    public ClassInfo()
    {
    	
    }
    public ClassInfo(String className, List<MethodInfo> methods, List<ConstructorInfo> constructors) {
        this.className = className;
        this.methods = methods;
        this.constructors = constructors;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    public List<ConstructorInfo> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<ConstructorInfo> constructors) {
        this.constructors = constructors;
    }
}
