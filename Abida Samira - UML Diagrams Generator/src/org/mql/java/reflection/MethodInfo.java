package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class MethodInfo {
    private String name;
    private List<String> annotations;

    public MethodInfo(String name) {
        this.name = name;
        this.annotations = new ArrayList<>();
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
}
