package org.mql.java.models;

import java.util.ArrayList;
import java.util.List;

public class PackageInfo {

    private String packageName;
    private List<ClassInfo> classes;
    private List<PackageInfo> subPackages; 

    public PackageInfo() {
        this.classes = new ArrayList<>();
        this.subPackages = new ArrayList<>(); 
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

    public void setClasses(List<ClassInfo> classes) {
        this.classes = classes;
    }

    public void addClass(ClassInfo classInfo) {
        this.classes.add(classInfo);
    }

    public List<PackageInfo> getSubPackages() {
        return subPackages;
    }

    public void setSubPackages(List<PackageInfo> subPackages) {
        this.subPackages = subPackages;
    }

    public void addSubPackage(PackageInfo subPackage) {
        this.subPackages.add(subPackage);
    }
    
 
    public void addPackage(PackageInfo packageInfo) {
        this.subPackages.add(packageInfo);
    }
    
    public PackageInfo clone() {
        PackageInfo clonedPackage = new PackageInfo(this.packageName);
       
        clonedPackage.setClasses(new ArrayList<>());
        clonedPackage.setSubPackages(new ArrayList<>());

        for (ClassInfo classInfo : this.classes) {
            clonedPackage.addClass(classInfo.clone());
        }

        for (PackageInfo subPackage : this.subPackages) {
            clonedPackage.addSubPackage(subPackage.clone());
        }

        return clonedPackage;
    }

    public void display(int indentationLevel) {
        String indentation = "  ".repeat(indentationLevel); 
        System.out.println(indentation + "Package: " + packageName);
        for (ClassInfo c : classes) {
            c.display(indentationLevel + 1); 
        }
        for (PackageInfo p : subPackages) {
            p.display(indentationLevel + 1);
        }
    }
}
    
    




