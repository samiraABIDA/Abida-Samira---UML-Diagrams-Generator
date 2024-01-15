package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.mql.java.models.ClassInfo;

public class PackageExplorer {

    private ProjectInfo project;

    public PackageExplorer(String projectName) {
        project = new ProjectInfo(projectName);

        String classPath = System.getProperty("java.class.path");
        String[] s = classPath.split(File.separator.equals("\\") ? "\\\\" : File.separator);

        String newClassPath = s[0];

        for (int i = 1; i < s.length - 2; i++) {
            newClassPath += File.separator + s[i];
        }

        newClassPath += File.separator + projectName + File.separator + "bin";
        File directory = new File(newClassPath);

        getPackages(directory.listFiles());
        addClassesToPackages();
    }

    private void getPackages(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                int size = file.listFiles().length;

                if (size == 0 || file.listFiles()[0].getName().contains(".class") || file.listFiles()[size - 1].getName().contains(".class")) {
                    PackageInfo packageInfo = new PackageInfo(file.getName());
                    getClasses(file, packageInfo);
                    project.addPackage(packageInfo);
                }

                getPackages(file.listFiles());
            }
        }
    }

    private void getClasses(File directory, PackageInfo packageInfo) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".class")) {
                // Obtenez le nom de la classe à partir du nom du fichier
                String className = file.getName().replace(".class", "");
                packageInfo.addClass(new ClassInfo(className));
            } else if (file.isDirectory()) {
                getClasses(file, packageInfo);
            }
        }
    }

    private void addClassesToPackages() {
        List<PackageInfo> packages = project.getPackages();

        for (PackageInfo packageInfo : packages) {
            String packagePath = packageInfo.getPackageName().replace(".", File.separator);
            Enumeration<URL> resources;
            try {
                resources = this.getClass().getClassLoader().getResources(packagePath);
                while (resources.hasMoreElements()) {
                    URL url = resources.nextElement();
                    String className = extractClassNameFromURL(url);
                    loadAndAddClassToPackage(className, packageInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String extractClassNameFromURL(URL url) {
        String path = url.getPath();
        String className = path.substring(path.lastIndexOf("/") + 1);  // À adapter en fonction de votre structure d'URL
        return className.replace(".class", "");
    }


    private void loadAndAddClassToPackage(String className, PackageInfo packageInfo) {
        try {
            Class<?> loadedClass = Class.forName(className);
            packageInfo.addClass(new ClassInfo(loadedClass.getSimpleName()));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ProjectInfo getProject() {
        return project;
    }
}
