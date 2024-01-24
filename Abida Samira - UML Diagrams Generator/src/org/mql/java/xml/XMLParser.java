package org.mql.java.xml;


import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;

public class XMLParser {

    public ProjectInfo parseXml(String filePath) {
        ProjectInfo projectInfo = new ProjectInfo();

        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element rootElement = doc.getDocumentElement();
            projectInfo.setProjectName(rootElement.getAttribute("name"));

            NodeList packageNodes = rootElement.getElementsByTagName("package");
            for (int i = 0; i < packageNodes.getLength(); i++) {
                Node packageNode = packageNodes.item(i);
                if (packageNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element packageElement = (Element) packageNode;
                    PackageInfo packageInfo = parsePackage(packageElement);
                    projectInfo.addPackage(packageInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectInfo;
    }

    private PackageInfo parsePackage(Element packageElement) {
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.setPackageName(packageElement.getAttribute("name"));

        NodeList classNodes = packageElement.getElementsByTagName("class");
        for (int i = 0; i < classNodes.getLength(); i++) {
            Node classNode = classNodes.item(i);
            if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                Element classElement = (Element) classNode;
                ClassInfo classInfo = parseClass(classElement);
                packageInfo.addClass(classInfo);
            }
        }

        NodeList subPackageNodes = packageElement.getElementsByTagName("package");
        for (int i = 0; i < subPackageNodes.getLength(); i++) {
            Node subPackageNode = subPackageNodes.item(i);
            if (subPackageNode.getNodeType() == Node.ELEMENT_NODE) {
                Element subPackageElement = (Element) subPackageNode;
                PackageInfo subPackageInfo = parsePackage(subPackageElement);
                packageInfo.addSubPackage(subPackageInfo);
            }
        }

        return packageInfo;
    }

 // ...

    private ClassInfo parseClass(Element classElement) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName(classElement.getAttribute("name"));

        // Parse properties
        List<String> properties = parseElements(classElement.getElementsByTagName("property"));
        classInfo.setProperties(properties);

        // Parse aggregation relations
        List<String> aggregationRelations = parseElements(classElement.getElementsByTagName("aggregationRelations"));
        classInfo.setAggregationRelations(aggregationRelations);

        // Parse usage relations
        List<String> usageRelations = parseElements(classElement.getElementsByTagName("usageRelations"));
        classInfo.setUsageRelations(usageRelations);

        // Parse inner classes
        List<String> internClasses = parseElements(classElement.getElementsByTagName("internClass"));
        classInfo.setInternClass(internClasses);

        // Parse methods
        NodeList methodNodes = classElement.getElementsByTagName("method");
        List<Method> methods = new ArrayList<>();
        for (int i = 0; i < methodNodes.getLength(); i++) {
            Node methodNode = methodNodes.item(i);
            if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element methodElement = (Element) methodNode;
                Method method = parseMethod(methodElement);

                // Filter out null methods and methods not belonging to ClassInfo
                if (method != null && method.getDeclaringClass().equals(ClassInfo.class)) {
                    methods.add(method);
                }
            }
        }
        classInfo.setMethods(methods);

        // Parse constructors
        NodeList constructorNodes = classElement.getElementsByTagName("constructor");
        List<Constructor<?>> constructors = new ArrayList<>();
        for (int i = 0; i < constructorNodes.getLength(); i++) {
            Node constructorNode = constructorNodes.item(i);
            if (constructorNode.getNodeType() == Node.ELEMENT_NODE) {
                Element constructorElement = (Element) constructorNode;
                Constructor<?> constructor = parseConstructor(constructorElement);
                constructors.add(constructor);
            }
        }
        classInfo.setConstructors(constructors);

        return classInfo;
    }

    private Method parseMethod(Element methodElement) {
        String methodName = methodElement.getAttribute("name");

        try {
    
            
            Method[] methods = ClassInfo.class.getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
          
                    return method;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    private Constructor<?> parseConstructor(Element constructorElement) {
    
        Constructor<?> constructor = null;

        try {
            Class<?>[] parameterTypes = new Class<?>[0]; 
            constructor = ClassInfo.class.getConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return constructor;
    }

  


    private List<String> parseElements(NodeList nodeList) {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                elements.add(element.getTextContent());
            }
        }
        return elements;
    }
}
