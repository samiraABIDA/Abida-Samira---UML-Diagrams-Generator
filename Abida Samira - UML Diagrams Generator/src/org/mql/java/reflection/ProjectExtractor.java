package org.mql.java.reflection;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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
        File rootDirectory = new File(rootPath);
        String relativePath = rootDirectory.toURI().relativize(file.toURI()).getPath();

        // Modification pour obtenir le nom du package sans le chemin complet
        String[] packageSegments = relativePath.split("\\\\");  // Utilisation de double barre oblique inversée
        StringBuilder packageNameBuilder = new StringBuilder();

        for (String segment : packageSegments) {
            if (!segment.isEmpty()) {
                if (packageNameBuilder.length() > 0) {
                    packageNameBuilder.append(".");
                }
                packageNameBuilder.append(segment);
            }
        }

        return packageNameBuilder.toString();
    }
    
    private String extractPackageName(String fullPath) {
        String[] segments = fullPath.split(File.separator);
        return segments[segments.length - 1];
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

                // Appel à une méthode pour extraire les méthodes, interfaces et annotations
              /*
                extractMethodsInterfacesAndAnnotations(classInfo);
*/
                packageExplorer.addClass(classInfo);
            }
        }
    }
/*
    private void extractMethodsInterfacesAndAnnotations(ClassInfo classInfo) {
        try {
            Class<?> clazz = Class.forName(classInfo.getName());

            // Extraire les méthodes
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                MethodInfo methodInfo = new MethodInfo(method.getName());

                // Extraire les annotations des méthodes
                Annotation[] methodAnnotations = method.getAnnotations();
                for (Annotation annotation : methodAnnotations) {
                    methodInfo.addAnnotation(annotation.annotationType().getName());
                }

                classInfo.addMethod(methodInfo);
            }

            // Extraire les interfaces
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> iface : interfaces) {
                classInfo.addInterface(iface.getName());
            }

            // Extraire les annotations de la classe
            Annotation[] classAnnotations = clazz.getAnnotations();
            for (Annotation annotation : classAnnotations) {
                classInfo.addAnnotation(annotation.annotationType().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


*/

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
