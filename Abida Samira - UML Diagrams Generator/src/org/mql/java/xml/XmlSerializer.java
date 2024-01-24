package org.mql.java.xml;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class XmlSerializer {

    public void serializeToXml(ProjectInfo project, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("project");
            rootElement.setAttribute("name", project.getProjectName());
            doc.appendChild(rootElement);
            serializePackages(doc, rootElement, project.getPackages());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("Structure de données sérialisée avec succès dans le fichier XML : " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void serializeAggregationRelations(Document doc, Element parentElement, List<String> aggregationRelations) {
        Element aggregationRelationsElement = doc.createElement("aggregationRelations");

        for (String aggregationRelation : aggregationRelations) {
            Element relationElement = doc.createElement("relation");
            relationElement.appendChild(doc.createTextNode(aggregationRelation));
            aggregationRelationsElement.appendChild(relationElement);
        }

        parentElement.appendChild(aggregationRelationsElement);
    }

    private void serializeUsageRelations(Document doc, Element parentElement, List<String> usageRelations) {
        Element usageRelationsElement = doc.createElement("usageRelations");

        for (String usageRelation : usageRelations) {
            Element relationElement = doc.createElement("relation");
            relationElement.appendChild(doc.createTextNode(usageRelation));
            usageRelationsElement.appendChild(relationElement);
        }

        parentElement.appendChild(usageRelationsElement);
    }

    private void serializeConstructors(Document doc, Element parentElement, List<Constructor<?>> constructors) {
        Element constructorsElement = doc.createElement("constructors");

        for (Constructor<?> constructor : constructors) {
            Element constructorElement = doc.createElement("constructor");
            constructorElement.setAttribute("name", constructor.getName());
            constructorElement.setAttribute("visibility", getVisibilityModifier(constructor.getModifiers()));
            constructorsElement.appendChild(constructorElement);
        }

        parentElement.appendChild(constructorsElement);
    }

    private void serializeProperties(Document doc, Element parentElement, List<String> properties) {
        Element propertiesElement = doc.createElement("properties");

        for (String property : properties) {
            Element propertyElement = doc.createElement("property");
            propertyElement.appendChild(doc.createTextNode(property));
            propertiesElement.appendChild(propertyElement);
        }

        parentElement.appendChild(propertiesElement);
    }

    private void serializeMethods(Document doc, Element parentElement, List<Method> methods) {
        Element methodsElement = doc.createElement("methods");

        for (Method method : methods) {
            Element methodElement = doc.createElement("method");
            methodElement.setAttribute("name", method.getName());
            methodElement.setAttribute("returnType", method.getReturnType().getName());
            methodElement.setAttribute("visibility", getVisibilityModifier(method.getModifiers()));
            methodsElement.appendChild(methodElement);
        }

        parentElement.appendChild(methodsElement);
    }

    private String getVisibilityModifier(int modifiers) {
        if (java.lang.reflect.Modifier.isPublic(modifiers)) {
            return "public";
        } else if (java.lang.reflect.Modifier.isProtected(modifiers)) {
            return "protected";
        } else if (java.lang.reflect.Modifier.isPrivate(modifiers)) {
            return "private";
        } else {
            return "default";
        }
    }

    private void serializeClasses(Document doc, Element parentElement, List<ClassInfo> classes) {
        for (ClassInfo classInfo : classes) {
            Element classElement = doc.createElement("class");
            classElement.setAttribute("name", classInfo.getClassName());

            // Serialize properties
            serializeProperties(doc, classElement, classInfo.getProperties());

            // Serialize constructors
            serializeConstructors(doc, classElement, classInfo.getConstructors());

            // Serialize methods
            serializeMethods(doc, classElement, classInfo.getMethods());

            // Serialize aggregation relations
            serializeAggregationRelations(doc, classElement, classInfo.getAggregationRelations());

            // Serialize usage relations
            serializeUsageRelations(doc, classElement, classInfo.getUsageRelations());

            // Serialize inner classes
            serializeInternClasses(doc, classElement, classInfo.getInternClass());

            parentElement.appendChild(classElement); // Add the class element to the parent element
        }
    }

    private void serializeInternClasses(Document doc, Element parentElement, List<String> internClasses) {
        if (!internClasses.isEmpty()) {
            Element internClassesElement = doc.createElement("internClasses");
            for (String internClass : internClasses) {
                Element internClassElement = doc.createElement("internClass");
                internClassElement.appendChild(doc.createTextNode(internClass));
                internClassesElement.appendChild(internClassElement);
            }
            parentElement.appendChild(internClassesElement);
        }
    }

    private void serializePackages(Document doc, Element parentElement, List<PackageInfo> packages) {
        for (PackageInfo packageInfo : packages) {
            Element packageElement = doc.createElement("package");
            packageElement.setAttribute("name", packageInfo.getPackageName());
            parentElement.appendChild(packageElement);
            serializeClasses(doc, packageElement, packageInfo.getClasses());
            serializePackages(doc, packageElement, packageInfo.getSubPackages());
        }
    }
}
