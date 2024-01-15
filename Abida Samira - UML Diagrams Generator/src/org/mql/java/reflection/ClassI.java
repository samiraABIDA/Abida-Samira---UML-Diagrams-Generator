package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class ClassI {
    private String name;
    private List<String> annotations = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private List<String> methods = new ArrayList<>();

    public ClassI(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getAnnotations() {
        return annotations;
    }

    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    public List<String> getFields() {
        return fields;
    }

    public void addField(String field) {
        fields.add(field);
    }

    public List<String> getMethods() {
        return methods;
    }

    public void addMethod(String method) {
        methods.add(method);
    }
}
