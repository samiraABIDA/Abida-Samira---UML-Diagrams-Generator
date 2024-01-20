package org.mql.java.reflection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.mql.java.models.ClassInfo;

public class RelationDetector {

	public RelationDetector() {
	
	}
	   public void detectRelations(ClassInfo classInfo, Class<?> cls) {
	        Field[] fields = cls.getDeclaredFields();
	        for (Field field : fields) {
	            if (!field.getType().isPrimitive()) {
	                classInfo.addAggregationRelation(field.getType().getSimpleName());
	            }
	        }

	        Method[] methods = cls.getDeclaredMethods();
	        for (Method method : methods) {
	            Class<?> returnType = method.getReturnType();
	            Class<?>[] parameterTypes = method.getParameterTypes();

	            if (!returnType.isPrimitive()) {
	                classInfo.addAggregationRelation(returnType.getSimpleName());
	            }
	            for (Class<?> paramType : parameterTypes) {
	                if (!paramType.isPrimitive()) {
	                    classInfo.addAggregationRelation(paramType.getSimpleName());
	                }
	            }
	        }

	        Class<?> superClass = cls.getSuperclass();
	        if (superClass != null && !superClass.equals(Object.class)) {
	            classInfo.addAggregationRelation(superClass.getSimpleName());
	        }

	        Class<?>[] interfaces = cls.getInterfaces();
	        for (Class<?> inter : interfaces) {
	            classInfo.addAggregationRelation(inter.getSimpleName());
	        }

	        Class<?>[] innerClasses = cls.getDeclaredClasses();
	        for (Class<?> innerClass : innerClasses) {
	            classInfo.addAggregationRelation(innerClass.getSimpleName());
	        }
	    }

	   public void detectUsageRelations(ClassInfo classInfo, String binPath, String classNameQl) {
		    try {
		        String sourceFilePath = getSourceFilePath(binPath, classNameQl);

		        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath))) {
		            String line;
		            while ((line = reader.readLine()) != null) {
		                if (line.startsWith("import ")) {
		                    // Récupérer uniquement le nom de la classe depuis l'import
		                    String importedClass = line.substring("import ".length(), line.length() - 1).trim().replace(";", "");
		                    
		                    // Extraire le nom de la classe à partir du chemin complet
		                    int lastDotIndex = importedClass.lastIndexOf(".");
		                    if (lastDotIndex != -1) {
		                        importedClass = importedClass.substring(lastDotIndex + 1);
		                    }

		                    classInfo.addUsageRelation(importedClass);
		                }
		            }
		        }
		    } catch (IOException e) {
		        System.out.println("Erreur lors de la détection des relations d'utilisation : " + e.getMessage());
		    }
		}


	    private String getSourceFilePath(String binPath, String classNameQl) {
	      
	        String sourceFilePath = binPath + File.separator + ".." + File.separator + "src" + File.separator +
	                classNameQl.replace(".", File.separator) + ".java";
	        return sourceFilePath;
	    }
	    
}
