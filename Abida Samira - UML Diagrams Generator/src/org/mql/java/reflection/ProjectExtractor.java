package org.mql.java.reflection;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ProjectExtractor {

    public Project extractProject(String binPath) {
        Project project = new Project();
        File binDirectory = new File(binPath);
        extractPackages(binDirectory, project);
        return project;
    }
    
    private String getPackageName(File file, String rootPath) {
        Path root = FileSystems.getDefault().getPath(rootPath);
        Path filePath = file.toPath();
        Path relativePath = root.relativize(filePath);

        // Modification pour obtenir le nom du package sans le chemin complet
        String[] packageSegments = relativePath.toString().split("\\\\");  // Utilisation de double barre oblique inversée
        StringBuilder packageNameBuilder = new StringBuilder();

        for (int i = 0; i < packageSegments.length - 1; i++) {
            if (i > 0) {
                packageNameBuilder.append(".");
            }
            packageNameBuilder.append(packageSegments[i]);
        }

        return packageNameBuilder.toString();
    }

    private void extractPackages(File directory, Project project) {
        PackageExplorer packageExplorer = new PackageExplorer(getPackageName(directory, directory.getAbsolutePath()));
        project.addPackageExplorer(packageExplorer);
        extractClasses(directory, packageExplorer);
        extractSubPackages(directory, packageExplorer, project);
    }


    private void extractSubPackages(File directory, PackageExplorer parentPackage, Project project) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                PackageExplorer packageExplorer = new PackageExplorer(file.getAbsolutePath());
                parentPackage.addPackageExplorer(packageExplorer);
                extractClasses(file, packageExplorer);
                extractSubPackages(file, packageExplorer, project);
            }
        }
    }

    private void extractClasses(File directory, PackageExplorer packageExplorer) {
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                String className = file.getName().replace(".class", "");
                ClassInfo classInfo = new ClassInfo(className);
                packageExplorer.addClass(classInfo);
            }
        }
    }

    public void displayProject(Project project) {
        for (PackageExplorer packageExplorer : project.getPackageExplorers()) {
            displayPackage(packageExplorer, "");
        }
    }

    private void displayPackage(PackageExplorer packageExplorer, String indent) {
        System.out.println(indent + "Package: " + packageExplorer.getPackageName()); // Modification ici pour utiliser getPackageName()
        
        for (ClassInfo classInfo : packageExplorer.getClasses()) {
            System.out.println(indent + "  Class: " + classInfo.getName());
        }

        for (PackageExplorer subPackage : packageExplorer.getSubPackages()) {
            displayPackage(subPackage, indent + "  ");
        }
    }
    
 

}
