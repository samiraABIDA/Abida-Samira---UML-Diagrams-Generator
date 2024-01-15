package org.mql.java.models;

import java.util.List;
import java.util.Vector;

public class PackageInfo {
    
    private String packageName;
    private List<ClassInfo> classes;

    public PackageInfo() {
        this.classes = new Vector<ClassInfo>();
    }

    public PackageInfo(String packageName) {
        this();
        this.packageName = packageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ClassInfo> getClasses() {
        return classes;
    }

    public void setClass(List<ClassInfo> classes) {
        this.classes= classes;
    }

    public void addClass(ClassInfo classInfo) {
        this.classes.add(classInfo);
    }
}
