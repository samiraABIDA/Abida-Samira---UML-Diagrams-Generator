package org.mql.java.examples;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.PackageInfo;
import org.mql.java.models.ProjectInfo;
import org.mql.java.reflection.PackageExplorer;

public class Examples {


	
    public Examples() {
        exp01();
    }

    void exp01() {
 
    	        String projectName = "Abida Samira - UML Diagrams Generator";
    	        PackageExplorer packageExplorer = new PackageExplorer(projectName);
    	        ProjectInfo projectInfo = packageExplorer.getProject();
    	        System.out.println("Nom du projet: " + projectInfo.getProjectName());
    	        for (PackageInfo packageInfo : projectInfo.getPackages()) {
    	            System.out.println("\nPackage: " + packageInfo.getPackageName());
    	            for (ClassInfo classInfo : packageInfo.getClasses()) {
    	                System.out.println("   Classe: " + classInfo.getClassName());
    	             
    	            }
    	        }
    	    }
    	

    public static void main(String[] args) {
    	
        new Examples();
        
    }
    
}