package org.mql.java.reflection;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<PackageExplorer> packageExplorers;

    public Project() {
        this.packageExplorers = new ArrayList<>();
    }

    public void addPackageExplorer(PackageExplorer packageExplorer) {
        packageExplorers.add(packageExplorer);
    }

    public List<PackageExplorer> getPackageExplorers() {
        return packageExplorers;
    }
}
