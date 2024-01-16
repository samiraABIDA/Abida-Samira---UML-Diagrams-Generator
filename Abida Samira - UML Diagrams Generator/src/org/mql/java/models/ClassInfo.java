package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class ClassInfo {
    private String className;
    private List<Property> properties;
    private List<Constructor> constructors;
    private List<Method> methods;
    private List<String> interfaces;
    private String superClass;
    private List<String> internClass;

    public ClassInfo() {
        this.properties = new Vector<Property>();
        this.constructors = new Vector<Constructor>();
        this.methods = new Vector<Method>();
    }

    public ClassInfo(String className) {
        this();
        this.className = className;
    }
    public ClassInfo(Class cls) {
    	
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public void setConstructors(List<Constructor> constructors) {
        this.constructors = constructors;
    }

    public void addConstructor(Constructor constructor) {
        this.constructors.add(constructor);
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method m) {
        this.methods.add(m);
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public List<String> getInternClass() {
        return internClass;
    }

    public void setInternClass(List<String> internClass) {
        this.internClass = internClass;
    }

    @Override
    public String toString() {
        return "ClassInfo [className=" + className + ", properties=" + properties + ", constructors=" + constructors
                + ", methods=" + methods + ", interfaces=" + interfaces + ", superClass=" + superClass
                + ", internClass=" + internClass + "]";
    }
}
