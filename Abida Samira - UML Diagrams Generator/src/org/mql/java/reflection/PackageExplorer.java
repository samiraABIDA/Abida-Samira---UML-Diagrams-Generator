package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class PackageExplorer {
    private String packageName; // Ajout d'une nouvelle propriété pour stocker le nom complet du package
    private List<ClassInfo> classes;
    private List<PackageExplorer> subPackages;

    public PackageExplorer(String packageName) {
        this.packageName = packageName;
        this.classes = new ArrayList<>();
        this.subPackages = new ArrayList<>();
    }

    public void addClass(ClassInfo classInfo) {
        classes.add(classInfo);
    }

    public void addPackageExplorer(PackageExplorer packageExplorer) {
        subPackages.add(packageExplorer);
    }

    public String getPackageName() { // Ajout de la méthode pour obtenir le nom complet du package
        return packageName;
    }

    public List<ClassInfo> getClasses() {
        return classes;
    }

    public List<PackageExplorer> getSubPackages() {
        return subPackages;
    }

    public void display() {
        for (ClassInfo classInfo : classes) {
            System.out.println("  Class: " + classInfo.getName());
        }

        for (PackageExplorer subPackage : subPackages) {
            System.out.println("  Subpackage: " + subPackage.getPackageName()); // Modification ici pour afficher le nom complet du package
            subPackage.display();
        }
    }
}
