package org.mql.java.models;

import java.util.ArrayList;
import java.util.List;

public class ProjectInfo {

    private String projectName;
    private List<PackageInfo> packages;

    public ProjectInfo() {
        this.packages = new ArrayList<>();
    }

    public ProjectInfo(String projectName) {
        this();
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<PackageInfo> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageInfo> packages) {
        this.packages = packages;
    }

    public void addPackage(PackageInfo pckg) {
        this.packages.add(pckg);
    }

    public List<ClassInfo> getAllClasses() {
        List<ClassInfo> allClasses = new ArrayList<>();
        for (PackageInfo p : packages) {
            allClasses.addAll(p.getClasses());
        }
        return allClasses;
    }
}
