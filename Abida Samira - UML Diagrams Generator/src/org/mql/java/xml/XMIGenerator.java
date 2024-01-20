package org.mql.java.xml;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

public class XMIGenerator {

    public static void generateXMI(ProjectInfo projectInfo, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("xmi:Project");
            rootElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
            rootElement.setAttribute("xmlns:uml", "http://www.omg.org/spec/UML/20090901");
            doc.appendChild(rootElement);

            for (PackageInfo packageInfo : projectInfo.getPackages()) {
                Element packageElement = doc.createElement("xmi:Package");
                packageElement.setAttribute("name", packageInfo.getPackageName());
                rootElement.appendChild(packageElement);

                for (ClassInfo classInfo : packageInfo.getClasses()) {
                    Element classElement = doc.createElement("xmi:Class");
                    classElement.setAttribute("name", classInfo.getClassName());
                    packageElement.appendChild(classElement);

                 
                    addAttributes(doc, classElement, classInfo.getProperties());
                    addMethods(doc, classElement, classInfo.getMethods());

                    addNewLineElement(doc, packageElement);
                }

                addNewLineElement(doc, rootElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);

            System.out.println("XMI généré avec succès dans " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addAttributes(Document doc, Element classElement, List<String> properties) {
        for (String property : properties) {
            Element attributeElement = doc.createElement("xmi:Attribute");
            attributeElement.setAttribute("xmi:type", "uml:Property");
            attributeElement.setAttribute("name", property);
            classElement.appendChild(attributeElement);
            addNewLineElement(doc, classElement);
        }
    }

    private static void addMethods(Document doc, Element classElement, List<Method> methods) {
        for (Method method : methods) {
            Element operationElement = doc.createElement("xmi:methode");
            operationElement.setAttribute("xmi:type", "uml:methode");
            operationElement.setAttribute("name", method.getName());
            classElement.appendChild(operationElement);
            addNewLineElement(doc, classElement);
        }
    }

    private static void addNewLineElement(Document doc, Element parentElement) {
        parentElement.appendChild(doc.createTextNode("\n"));
    }
}
