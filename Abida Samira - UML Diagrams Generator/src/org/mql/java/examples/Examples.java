package org.mql.java.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.mql.java.models.Property;
import org.mql.java.reflection.PackageExplorer;

public class Examples {

	public Examples() {
		exp01();
	}

	void exp01() {

		String projectName = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator\\bin";
		PackageExplorer packageExplorer = new PackageExplorer(projectName);
		ProjectInfo projectInfo = packageExplorer.getProject();
		displayProjectInfo(projectInfo);
	}
	private static void displayProjectInfo(ProjectInfo projectInfo) {
		
		System.out.println("Project Name: " + projectInfo.getProjectName());
		List<PackageInfo> packages = projectInfo.getPackages();
		for (PackageInfo packageInfo : packages) {
			System.out.println("Package: " + packageInfo.getPackageName());
			List<ClassInfo> classes = packageInfo.getClasses();
			for (ClassInfo classInfo : classes) {
				System.out.println("  Class: " + classInfo.getClassName());

			}
		}
	}



	public static void main(String[] args) {
		new Examples();
	}
}
