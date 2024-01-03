package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

import org.mql.java.models.ClassInfo;

public class PackageInfo {
    private String packageName;
    private List<ClassInfo> classes;

    public PackageInfo() {
        this.classes = new ArrayList<>();
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

    public void setClasses(List<ClassInfo> classes) {
        this.classes = classes;
    }
}
