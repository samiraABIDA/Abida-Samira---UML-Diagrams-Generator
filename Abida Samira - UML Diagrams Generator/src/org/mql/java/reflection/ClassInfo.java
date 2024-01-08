package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class ClassInfo {
    private String name;
    private List<MethodInfo> methods;
    private List<String> interfaces;
    private List<String> annotations;

    public ClassInfo(String name) {
        this.name = name;
        this.methods = new ArrayList<>();
        this.interfaces = new ArrayList<>();
        this.annotations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public List<String> getInterfaces() {
        return interfaces;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addMethod(MethodInfo method) {
        methods.add(method);
    }

    public void addInterface(String iface) {
        interfaces.add(iface);
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }
}
