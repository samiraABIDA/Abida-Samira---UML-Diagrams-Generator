package org.mql.java.xml;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

public class XMIParser {
/*
	public ProjectInfo parseXMI(String filePath) {
	    try {
	        File file = new File(filePath);
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(file);

	        doc.getDocumentElement().normalize();

	        Element projectElement = doc.getDocumentElement();
	        ProjectInfo projectInfo = new ProjectInfo();

	      
	        System.out.println("Debug: Project Name from XML: " + projectElement.getAttribute("name"));
	        projectInfo.setProjectName(projectElement.getAttribute("name"));

	        NodeList packageNodes = projectElement.getElementsByTagName("package");
	        for (int i = 0; i < packageNodes.getLength(); i++) {
	            Node packageNode = packageNodes.item(i);
	            if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
	                PackageInfo packageInfo = parsePackage((Element) packageNode);
	                projectInfo.addPackage(packageInfo);
	            }
	        }

	        printProjectInfo(projectInfo);
	        return projectInfo;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}





    private PackageInfo parsePackage(Element packageElement) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setPackageName(packageElement.getAttribute("name"));

        NodeList classNodes = packageElement.getElementsByTagName("class");
        for (int i = 0; i < classNodes.getLength(); i++) {
            Node classNode = classNodes.item(i);
            if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                ClassInfo classInfo = parseClass((Element) classNode);
                packageInfo.addClass(classInfo);
            }
        }

        NodeList subPackageNodes = packageElement.getElementsByTagName("package");
        for (int i = 0; i < subPackageNodes.getLength(); i++) {
            Node subPackageNode = subPackageNodes.item(i);
            if (subPackageNode.getNodeType() == Node.ELEMENT_NODE) {
                PackageInfo subPackageInfo = parsePackage((Element) subPackageNode);
                packageInfo.addSubPackage(subPackageInfo);
            }
        }

        return packageInfo;
    }

    private ClassInfo parseClass(Element classElement) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName(classElement.getAttribute("name"));

   

        return classInfo;
    }

    private void printProjectInfo(ProjectInfo projectInfo) {
        System.out.println("Project Name: " + projectInfo.getProjectName());
        List<PackageInfo> packages = projectInfo.getPackages();
        for (PackageInfo packageInfo : packages) {
            printPackageInfo(packageInfo, 1);
        }
    }


    private void printPackageInfo(PackageInfo packageInfo, int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation + "Package: " + packageInfo.getPackageName());

        List<ClassInfo> classes = packageInfo.getClasses();
        for (ClassInfo classInfo : classes) {
            printClassInfo(classInfo, depth + 1);
        }

        List<PackageInfo> subPackages = packageInfo.getSubPackages();
        for (PackageInfo subPackageInfo : subPackages) {
            printPackageInfo(subPackageInfo, depth + 1);
        }
    }

 // Nouvelle méthode pour imprimer les détails de la classe
    private void printClassInfo(ClassInfo classInfo, int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation + "Class: " + classInfo.getClassName());

 
        List<String> properties = classInfo.getProperties();
        if (!properties.isEmpty()) {
            System.out.println(indentation + "  Properties:");
            for (String property : properties) {
                System.out.println(indentation + "    " + property);
            }
        }

     
        List<Method> methods = classInfo.getMethods();
        if (!methods.isEmpty()) {
            System.out.println(indentation + "  Methods:");
            for (Method method : methods) {
                System.out.println(indentation + "    " + getMethodSignature(method));
            }
        }
    }


    private String getMethodSignature(Method method) {
        StringBuilder signature = new StringBuilder();
        signature.append(method.getReturnType().getSimpleName()).append(" ");
        signature.append(method.getName()).append("(");

        Class<?>[] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            signature.append(paramTypes[i].getSimpleName());
            if (i < paramTypes.length - 1) {
                signature.append(", ");
            }
        }

        signature.append(")");

        return signature.toString();
    }*/
}

