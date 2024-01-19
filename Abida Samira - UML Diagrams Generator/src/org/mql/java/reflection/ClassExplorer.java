package org.mql.java.reflection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.mql.java.models.ClassInfo;

public class ClassExplorer {

	private ClassInfo classInfo;

	public ClassExplorer() {

	}

	public ClassExplorer(String binPath, String classNameQl) {
		try {
			MyClassLoader classLoader = new MyClassLoader(binPath, classNameQl);
			Class<?> cls = classLoader.getMaClass();

			classInfo = new ClassInfo(cls.getSimpleName());
			classInfo.setProperties(getProperties(cls));
			classInfo.setConstructors(getConstructors(cls));
			classInfo.setMethods(getMethods(cls));
			classInfo.setInterfaces(getInterfaces(cls));
			classInfo.setSuperClass(getSuperClass(cls));
			classInfo.setInternClass(getInternClasses(cls));

			RelationDetector relationDetector = new RelationDetector();
			relationDetector.detectRelations(classInfo, cls);
			relationDetector.detectUsageRelations(classInfo, binPath, classNameQl);

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}


	private List<String> getProperties(Class<?> cls) {
		List<String> propertyNames = new ArrayList<>();

		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			propertyNames.add(field.getName());
		}
		return propertyNames;
	}


	private List<Constructor<?>> getConstructors(Class<?> cls) {
		List<Constructor<?>> constructors = new ArrayList<>();
		Constructor<?>[] javaConstructors = cls.getDeclaredConstructors();

		for (Constructor<?> constructor : javaConstructors) {
			constructors.add(constructor);
		}
		return constructors;
	}

	private List<Method> getMethods(Class<?> cls) {
		List<Method> methods = new ArrayList<>();

		java.lang.reflect.Method[] allMethods = cls.getDeclaredMethods();
		for (java.lang.reflect.Method method : allMethods) {
			methods.add(method);
		}
		return methods;
	}

	private String getSuperClass(Class<?> cls) {
		Class<?> superClass = cls.getSuperclass();
		return (superClass != null) ? superClass.getSimpleName() : "Object";
	}


	private List<String> getInterfaces(Class<?> cls) {
		List<String> interfaces = new ArrayList<>();
		Class<?>[] inters = cls.getInterfaces();

		for (Class<?> inter : inters) {
			interfaces.add(inter.getSimpleName());
		}
		return interfaces;
	}

	private List<String> getInternClasses(Class<?> cls) {
		List<String> internClasses = new ArrayList<>();
		Class<?>[] intern = cls.getDeclaredClasses();

		for (Class<?> internClass : intern) {
			internClasses.add(internClass.getSimpleName());
		}
		return internClasses;
	}

	private String getModifier(int m) {
		return Modifier.toString(m);
	}
}
