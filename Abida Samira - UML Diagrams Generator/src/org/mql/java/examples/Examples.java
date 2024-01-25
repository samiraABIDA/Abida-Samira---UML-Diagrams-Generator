package org.mql.java.examples;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.mql.java.reflection.PackageExplorer;
import org.mql.java.ui.UMLDiagram;
import org.mql.java.xml.XMIGenerator;
import org.mql.java.xml.XMIParser;
import org.mql.java.xml.XMLParser;
import org.mql.java.xml.XmlSerializer;

public class Examples {

	public Examples() {
		exp05();
	}

	void exp01() {
		String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		ProjectInfo projectInfo = packageExplorer.getProject();
		displayProjectInfo(projectInfo);
	}

	void exp02() {
		ProjectInfo myProject = new ProjectInfo("uml-diagrams-generator");

		String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		ProjectInfo projectInfo = packageExplorer.getProject();

		for (PackageInfo packageInfo : projectInfo.getPackages()) {
			PackageInfo clonedPackage = new PackageInfo(packageInfo.getPackageName());

			for (ClassInfo classInfo : packageInfo.getClasses()) {
				ClassInfo clonedClass = classInfo.clone();

				for (String internClass : classInfo.getInternClass()) {
					clonedClass.addInternClass(internClass);
				}

				clonedPackage.addClass(clonedClass);
			}

			myProject.addPackage(clonedPackage);
		}

		XmlSerializer xmlSerializer = new XmlSerializer();
		String xmlFilePath = "resources/output.xml";
		xmlSerializer.serializeToXml(myProject, xmlFilePath);

		displayProjectInfo(myProject);
	}

	void exp03() {

		XMLParser xmlParser = new XMLParser();
		ProjectInfo projectInfo = xmlParser.parseXml("resources/output.xml");

		if (projectInfo != null) {
			System.out.println("Le fichier XML a été correctement analysé.");
			displayProjectInfo(projectInfo);
		} else {
			System.out.println("Erreur lors de l'analyse du fichier XML. Assurez-vous que le fichier est correct.");
		}


	}


	void exp04() {

		String projectPath = "C:\\Users\\asus\\\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		ProjectInfo projectInfo = packageExplorer.getProject();

		String xmiFilePath = "resources/output.xmi";

		XMIGenerator.generateXMI(projectInfo, xmiFilePath);

		System.out.println("Fichier XMI généré avec succès à l'emplacement : " + xmiFilePath);
	}

	void exp05() {

			      
		       String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
		       PackageExplorer packageExplorer = new PackageExplorer(projectPath);
		       ProjectInfo project = packageExplorer.getProject();

		       SwingUtilities.invokeLater(() -> new UMLDiagram(project));
		  

	}

	private static void displayProjectInfo(ProjectInfo projectInfo) {
		System.out.println("Project Name: " + projectInfo.getProjectName());
		List<PackageInfo> packages = projectInfo.getPackages();

		for (PackageInfo packageInfo : packages) {
			System.out.println("Package: " + packageInfo.getPackageName());
			List<ClassInfo> classes = packageInfo.getClasses();

			for (ClassInfo classInfo : classes) {
				System.out.println("  Class: " + classInfo.getClassName());

				System.out.println("    Methods:");
				classInfo.getMethodNames().forEach(methodName -> {

					int lastDotIndex = methodName.lastIndexOf('.');
					String shortMethodName = (lastDotIndex != -1) ? methodName.substring(lastDotIndex + 1) : methodName;
					System.out.println("      " + shortMethodName);
				});


				System.out.println("    Constructors:");
				classInfo.getConstructors().forEach(constructor -> {
					System.out.print("      " + extractConstructorName(constructor.getName()) + " (");
					Class<?>[] paramTypes = constructor.getParameterTypes();
					for (int i = 0; i < paramTypes.length; i++) {
						System.out.print(paramTypes[i].getSimpleName());
						if (i < paramTypes.length - 1) {
							System.out.print(", ");
						}
					}
					System.out.println(")");
				});


				System.out.println("    Aggregation Relations: " + classInfo.getAggregationRelations());


				System.out.println("    Usage Relations: " + classInfo.getUsageRelations());


				System.out.println("    Properties: " + classInfo.getProperties());


				List<String> internClasses = classInfo.getInternClass();
				if (!internClasses.isEmpty()) {
					System.out.println("    Inner Classes: " + internClasses);
				}

				System.out.println();
			}
		}
	}

	private static String extractConstructorName(String fullName) {
		int lastDotIndex = fullName.lastIndexOf(".");
		return (lastDotIndex != -1) ? fullName.substring(lastDotIndex + 1) : fullName;
	}



	public static void main(String[] args) {
		new Examples();
	}
}
