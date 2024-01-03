package org.mql.java.reflection;



import java.util.ArrayList;
import java.util.List;

public class Project {
    private List<PackageInfo> packages;

    public Project() {
        this.packages = new ArrayList<>();
    }

    public List<PackageInfo> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageInfo> packages) {
        this.packages = packages;
    }
}
