package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;

public class PackageExplorer {

	private ProjectInfo project;

	public PackageExplorer(String projectName) {
		project = new ProjectInfo(projectName);

		String binPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator\\bin";
		File directory = new File(binPath);

		getPackages(directory.listFiles());
		addClassesToPackages();
	}

	private void getPackages(File[] files) {
		if (files == null) {
			return;
		}

		for (File file : files) {
			if (file.isDirectory()) {
				String packageName = getPackageName(file);
				PackageInfo packageInfo = new PackageInfo(packageName);
				project.addPackage(packageInfo);
				getClasses(file, packageInfo);
				getPackages(file.listFiles());
			}
		}
	}

	private String getPackageName(File directory) {
		String packagePath = directory.getAbsolutePath();
		String basePath = new File("C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator\\bin").getAbsolutePath();
		String packageName = packagePath.replace(basePath, "").replace(File.separator, ".");
		return packageName.substring(1); 
	}

	private void getClasses(File directory, PackageInfo packageInfo) {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".class")) {

				String className = file.getName().replace(".class", "");
				packageInfo.addClass(new ClassInfo(className));
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

				for (URL url : Collections.list(resources)) {
					String fileName = new File(url.toURI()).getName();

					if (fileName.endsWith(".class")) {
						String className = fileName.replace(".class", "");
						loadAndAddClassToPackage(className, packageInfo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void loadAndAddClassToPackage(String className, PackageInfo packageInfo) {
		System.out.println("Loading class: " + className);
		packageInfo.addClass(new ClassInfo(className));
	}

	public ProjectInfo getProject() {
		return project;
	}
}
