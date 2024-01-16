package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class MyClassLoader extends URLClassLoader {

    public MyClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public static MyClassLoader createCustomClassLoader(String projectPath) {
        try {
            URL projectUrl = new File(projectPath).toURI().toURL();
            return new MyClassLoader(new URL[]{projectUrl}, MyClassLoader.class.getClassLoader());
        } catch (Exception e) {
            System.out.println("Erreur lors de la création du MyClassLoader: " + e.getMessage());
            return null;
        }
    }

    public List<Class<?>> loadAllClasses(String basePackage) {
        List<Class<?>> classes = new ArrayList<>();
        File projectDirectory = new File(getURLs()[0].getPath());
        loadClassesRecursively(projectDirectory, basePackage, classes);
        return classes;
    }

    private void loadClassesRecursively(File directory, String basePackage, List<Class<?>> classes) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    loadClassesRecursively(file, basePackage + "." + file.getName(), classes);
                } else if (file.getName().endsWith(".class")) {
                    String className = basePackage + '.' + file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(loadClass(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


