package org.mql.java.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.mql.java.reflection.PackageExplorer;
import org.mql.java.xml.XmlSerializer;

public class Examples {

	public Examples() {
		exp01();
	}

	void exp01() {
		String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		ProjectInfo projectInfo = packageExplorer.getProject();
		displayProjectInfo(projectInfo);
	}

	void exp02() {



		ProjectInfo myProject = new ProjectInfo("MonProjet");

		String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		ProjectInfo projectInfo = packageExplorer.getProject();

		for (PackageInfo packageInfo : projectInfo.getPackages()) {
			PackageInfo clonedPackage = new PackageInfo(packageInfo.getPackageName());

			// Clonez chaque classe dans le package
			for (ClassInfo classInfo : packageInfo.getClasses()) {
				ClassInfo clonedClass = classInfo.clone();  

				clonedPackage.addClass(clonedClass);
			}
			myProject.addPackage(clonedPackage);
		}

		XmlSerializer xmlSerializer = new XmlSerializer();
		String xmlFilePath = "resources/output.xml";
		xmlSerializer.serializeToXml(myProject, xmlFilePath);

		displayProjectInfo(myProject);
	}



	private static void displayProjectInfo(ProjectInfo projectInfo) {
		System.out.println("Project Name: " + projectInfo.getProjectName());
		List<PackageInfo> packages = projectInfo.getPackages();
		for (PackageInfo packageInfo : packages) {
			System.out.println("Package: " + packageInfo.getPackageName());
			List<ClassInfo> classes = packageInfo.getClasses();
			for (ClassInfo classInfo : classes) {
				System.out.println("  Class: " + classInfo.getClassName());

				List<Method> methods = classInfo.getMethods();
				System.out.println("    Methods:");
				for (Method method : methods) {
					System.out.println("      " + method.getReturnType().getSimpleName() + " " + method.getName() + "()");

					Class<?>[] paramTypes = method.getParameterTypes();
					if (paramTypes.length > 0) {
						System.out.print("        Parameters: ");
						for (int i = 0; i < paramTypes.length; i++) {
							System.out.print(paramTypes[i].getSimpleName());
							if (i < paramTypes.length - 1) {
								System.out.print(", ");
							}
						}
						System.out.println();
					}
				}

				List<Constructor<?>> constructors = classInfo.getConstructors();
				System.out.println("    Constructors:");
				for (Constructor<?> constructor : constructors) {
					System.out.print("      " + classInfo.getClassName() + " (");


					Class<?>[] paramTypes = constructor.getParameterTypes();
					for (int i = 0; i < paramTypes.length; i++) {
						System.out.print(paramTypes[i].getSimpleName());
						if (i < paramTypes.length - 1) {
							System.out.print(", ");
						}
					}

					System.out.println(")");
				}


				List<String> aggregationRelations = classInfo.getAggregationRelations();
				System.out.println("    Aggregation Relations:");
				for (String relation : aggregationRelations) {
					System.out.println("      " + relation);
				}


				List<String> usageRelations = classInfo.getUsageRelations();
				System.out.println("    Usage Relations:");
				for (String relation : usageRelations) {
					System.out.println("      " + relation);
				}

				System.out.println(); 
			}
		}
	}



	public static void main(String[] args) {
		new Examples();
	}
}