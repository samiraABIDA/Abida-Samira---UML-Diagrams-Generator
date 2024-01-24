package org.mql.java.ui;

import javax.swing.*;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.ProjectInfo;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.List;



public class UMLPanel extends JPanel {

    private ProjectInfo project;
    private int classWidth = 300;  // Ajustez selon vos besoins
    private int minHeight = 100;  // Hauteur minimale pour chaque classe
    private int padding = 11;    // Marge intérieure
    private Font font = new Font("Arial", Font.PLAIN, 12);  // Police pour les textes

    public UMLPanel(ProjectInfo project) {
        this.project = project;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);

        List<ClassInfo> classes = project.getAllClasses();
        int x = 50;
        int y = 50;

        for (ClassInfo classInfo : classes) {
            drawClass(g2d, classInfo, x, y);
            x += classWidth + 50;  // Ajustez selon vos besoins
        }
    }

    private void drawClass(Graphics2D g2d, ClassInfo classInfo, int x, int y) {
        // Calculer la hauteur en fonction du nombre total d'attributs et de méthodes
        int totalAttributesAndMethods = classInfo.getProperties().size() + classInfo.getMethods().size();
        int classHeight = Math.max(minHeight, totalAttributesAndMethods * 20 + padding * 4);

        g2d.drawRect(x, y, classWidth, classHeight);

        // Dessiner le nom de la classe en haut
        g2d.drawString("Class: " + classInfo.getClassName(), x + padding, y + padding * 2);

        // Dessiner une ligne pour séparer le nom des attributs
        int lineY1 = y + padding * 4;
        g2d.drawLine(x, lineY1, x + classWidth, lineY1);

        // Dessiner les attributs
        List<String> attributes = classInfo.getProperties();
        int attributeY = lineY1 + padding; // Ajustez selon vos besoins pour l'espacement
        for (String attribute : attributes) {
            g2d.drawString("- " + attribute, x + padding, attributeY);
            attributeY += 15;
        }

        // Dessiner une ligne pour séparer les attributs des méthodes
        int lineY2 = attributeY + padding;
        g2d.drawLine(x, lineY2, x + classWidth, lineY2);

        // Dessiner les méthodes en bas
        List<Method> methods = classInfo.getMethods();
        int methodY = lineY2 + padding; // Ajustez selon vos besoins pour l'espacement
        for (Method method : methods) {
            g2d.drawString("+ " + method.getName() + "()", x + padding, methodY);
            methodY += 13;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int numClasses = project.getAllClasses().size();
        int width = numClasses * (classWidth + 50) + 50;  // Ajustez selon vos besoins
        return new Dimension(width, 600);
    }
}
