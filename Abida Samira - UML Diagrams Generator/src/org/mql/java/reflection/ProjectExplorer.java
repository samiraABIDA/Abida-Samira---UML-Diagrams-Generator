package org.mql.java.reflection;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ProjectExplorer {
    private Set<String> packageNames = new HashSet<>();
    private String rootPath;

    public ProjectExplorer(String projectName) {
        String classpath = System.getProperty("java.class.path");
        File dir = new File(classpath);
        rootPath = dir.getPath();
        getPackage(dir);
    }

    public void getPackage(File dir) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                String packageName = file.getPath().replace(rootPath, "").replace("\\", ".");
                packageNames.add(packageName.substring(1)); // Remove leading dot
                getPackage(file);
            }
        }
    }

    public Set<String> getPackageNames() {
        return packageNames;
    }
}
