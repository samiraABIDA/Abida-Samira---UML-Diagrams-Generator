package org.mql.java.ui;

import javax.swing.*;

import org.mql.java.models.ProjectInfo;
import org.mql.java.reflection.PackageExplorer;

import java.awt.*;
import java.util.List;

public class UMLDiagram extends JFrame {

    private ProjectInfo project;

    public UMLDiagram(ProjectInfo project) {
        this.project = project;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("UML Diagram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        UMLPanel umlPanel = new UMLPanel(project);
        JScrollPane scrollPane = new JScrollPane(umlPanel);
        add(scrollPane);

        setVisible(true);
    }


    public static void main(String[] args) {
      
       String projectPath = "C:\\Users\\asus\\git\\repository7\\Abida Samira - UML Diagrams Generator";
       PackageExplorer packageExplorer = new PackageExplorer(projectPath);
       ProjectInfo project = packageExplorer.getProject();

       SwingUtilities.invokeLater(() -> new UMLDiagram(project));
    }
}