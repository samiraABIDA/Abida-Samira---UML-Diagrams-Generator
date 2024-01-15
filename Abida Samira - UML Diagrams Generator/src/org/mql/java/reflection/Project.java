package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<ClassI> classes = new ArrayList<>();

    public void addClass(ClassI classInfo) {
        classes.add(classInfo);
    }

    public List<ClassI> getClasses() {
        return classes;
    }
}
