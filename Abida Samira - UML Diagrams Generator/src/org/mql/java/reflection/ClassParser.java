package org.mql.java.reflection;


import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassParser {

    public static void main(String[] args) {
        try {
            String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator\\bin";
            Project project = extractProjectInfo(projectPath);
            // Utilisez la structure de données en mémoire comme nécessaire
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Project extractProjectInfo(String projectPath) throws IOException {
        Project project = new Project();
        File projectDir = new File(projectPath);

        if (projectDir.isDirectory()) {
            ClassLoader classLoader = getClassLoader(projectDir);

            for (File file : listJavaFiles(projectDir)) {
                String className = getClassName(projectDir, file);
                ClassI classInfo = getClassInfo(classLoader, className);
                project.addClass(classInfo);
            }
        }

        return project;
    }

    private static ClassLoader getClassLoader(File projectDir) throws IOException {
        URL[] urls = {projectDir.toURI().toURL()};
        return new URLClassLoader(urls);
    }

    private static List<File> listJavaFiles(File directory) {
        List<File> javaFiles = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                javaFiles.add(file);
            } else if (file.isDirectory()) {
                javaFiles.addAll(listJavaFiles(file));
            }
        }
        return javaFiles;
    }

    private static String getClassName(File projectDir, File file) {
        String filePath = file.getAbsolutePath();
        String className = filePath.substring(projectDir.getAbsolutePath().length() + 1,
                filePath.length() - ".class".length());
        className = className.replace(File.separatorChar, '.');
        return className;
    }

    private static ClassI getClassInfo(ClassLoader classLoader, String className) {
        ClassI classInfo = new ClassI(className);

        try {
            Class<?> clazz = classLoader.loadClass(className);

            // Obtenez ici les informations nécessaires sur la classe, par exemple les annotations, les champs, les méthodes, etc.
            // Ajoutez ces informations à l'instance de ClassInfo

            // Obtenez les annotations de la classe
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                classInfo.addAnnotation(annotation.annotationType().getSimpleName());
            }

            // Obtenez les champs de la classe
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                classInfo.addField(field.getName());
            }

            // Obtenez les méthodes de la classe
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                classInfo.addMethod(method.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classInfo;
    }
}

