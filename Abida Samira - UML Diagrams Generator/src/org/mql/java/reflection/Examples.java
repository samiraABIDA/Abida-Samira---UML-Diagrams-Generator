package org.mql.java.reflection;

public class Examples {

    public static void main(String[] args) {
        String binPath = "E:\\Data\\workspace\\Abida Samira - UML Diagrams Generator\\bin";

        ProjectExtractor projectExtractor = new ProjectExtractor();
        Project project = projectExtractor.extractProject(binPath);
        projectExtractor.displayProject(project);
    }
}
