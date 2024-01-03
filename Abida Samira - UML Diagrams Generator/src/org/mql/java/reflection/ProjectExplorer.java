package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.mql.java.models.ClassInfo;
import org.mql.java.models.ConstructorInfo;
import org.mql.java.models.MethodInfo;

import org.mql.java.models.ParameterInfo;

public class ProjectExplorer {
    private Project project;

    public ProjectExplorer(String projectPath) {
        this.project = new Project();
        exploreProject(new File(projectPath));
    }

    private void exploreProject(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    PackageInfo packageInfo = new PackageInfo();
                    packageInfo.setPackageName(file.getName());
                    explorePackage(file, packageInfo);
                    project.getPackages().add(packageInfo);
                }
            }
        }
    }

    private void explorePackage(File packageDir, PackageInfo packageInfo) {
        File[] files = packageDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    ClassInfo classInfo = new ClassInfo(file.getName(), new ArrayList<>(), new ArrayList<>());
                    exploreClass(file, classInfo);
                    packageInfo.getClasses().add(classInfo);
                }
            }
        }
    }

    private void exploreClass(File classFile, ClassInfo classInfo) {
        try {
            // Charger la classe à partir du fichier .class
        	
            String className = classFile.getName().replace(".class", "");
            Class<?> loadedClass = Class.forName(className);

            // Récupérer les méthodes
            Method[] methods = loadedClass.getDeclaredMethods();
            List<MethodInfo> methodList = new ArrayList<>();
            for (Method method : methods) {
                String methodName = method.getName();
                Class<?> returnType = method.getReturnType();
                int modifiers = method.getModifiers();
                List<ParameterInfo> parameters = new ArrayList<>();
                
                // Récupérer les paramètres
                for (java.lang.reflect.Parameter parameter : method.getParameters()) {
                    parameters.add(new ParameterInfo(parameter.getName(), parameter.getType()));
                }

                MethodInfo methodInfo = new MethodInfo(methodName, returnType, modifiers, parameters);
                methodList.add(methodInfo);
            }
            classInfo.setMethods(methodList);

            // Récupérer les constructeurs
            Constructor<?>[] constructors = loadedClass.getDeclaredConstructors();
            List<ConstructorInfo> constructorList = new ArrayList<>();
            for (Constructor<?> constructor : constructors) {
                int modifiers = constructor.getModifiers();
                List<ParameterInfo> parameters = new ArrayList<>();

                // Récupérer les paramètres
                for (java.lang.reflect.Parameter parameter : constructor.getParameters()) {
                    parameters.add(new ParameterInfo(parameter.getName(), parameter.getType()));
                }

                ConstructorInfo constructorInfo = new ConstructorInfo(modifiers, parameters);
                constructorList.add(constructorInfo);
            }
            classInfo.setConstructors(constructorList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Project getProject() {
        return project;
    }

    public static void main(String[] args) {
        String projectPath = "E:\\Data\\workspace\\Abida Samira - UML Diagrams Generator\\bin";

        ProjectExplorer projectExplorer = new ProjectExplorer(projectPath);

        Project project = projectExplorer.getProject();

        List<PackageInfo> packages = project.getPackages();
        for (PackageInfo packageInfo : packages) {
            System.out.println("Package: " + packageInfo.getPackageName());

            List<ClassInfo> classes = packageInfo.getClasses();
            for (ClassInfo classInfo : classes) {
                System.out.println("  Class: " + classInfo.getClassName());

                List<MethodInfo> methods = classInfo.getMethods();
                for (MethodInfo method : methods) {
                    System.out.println("    Method: " + method.getMethodName());
                }

                List<ConstructorInfo> constructors = classInfo.getConstructors();
                for (ConstructorInfo constructor : constructors) {
                    System.out.println("    Constructor: " + constructor.toString());
                }
            }
        }
    }
}
