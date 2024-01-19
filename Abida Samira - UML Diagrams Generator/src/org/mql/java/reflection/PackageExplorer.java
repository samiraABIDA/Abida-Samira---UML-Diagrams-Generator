
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

	public PackageExplorer(String projectPath) {
		project = new ProjectInfo(new File(projectPath).getName());
		File directory = new File(projectPath, "bin"); // Assuming the binary folder is always named "bin"
		project.setPath(directory.getPath());
		System.out.println(directory.getPath());
		explorePackages(directory, "");
		// addClassesToPackages();
	}

	private void explorePackages(File directory, String pack) {
		if (directory == null || !directory.exists()) {
			return;
		}

		PackageInfo packageInfo = new PackageInfo(pack);
		project.addPackage(packageInfo);

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				explorePackages(file, pack.isEmpty() ? file.getName() : pack + "." + file.getName());
			} else {
				packageInfo.addClass(new ClassExplorer(project.getPath(), pack + "." + file.getName().replace(".class", "")).getClassInfo());
			}
		}
	}



	private String getRelativePackageName(File directory) {
		String packagePath = directory.getAbsolutePath();
		String basePath = new File(project.getProjectName()).getAbsolutePath();
		return packagePath.replace(basePath + File.separator + "bin" + File.separator, "").replace(File.separator, ".")
				.substring(1);
	}


	private void addClassesToPackages() {
		List<PackageInfo> packages = project.getPackages();

		for (PackageInfo packageInfo : packages) {
			String packagePath = packageInfo.getPackageName().replace(".", File.separator);
			try {
				Enumeration<URL> resources = this.getClass().getClassLoader().getResources(packagePath);

				for (URL url : Collections.list(resources)) {
					String fileName = new File(url.toURI()).getName();

					if (fileName.endsWith(".class")) {
						loadAndAddClassToPackage(fileName.replace(".class", ""), packageInfo);
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
