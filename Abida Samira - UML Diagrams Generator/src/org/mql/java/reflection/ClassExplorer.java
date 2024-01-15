package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;

import org.mql.java.models.ClassInfo;
import org.mql.java.models.Constructor;
import org.mql.java.models.Method;
import org.mql.java.models.Property;

public class ClassExplorer {

    private ClassInfo classInfo;
    private Class<?> cls;

    public ClassExplorer() {

    }

    public ClassExplorer(String className) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[]{}, this.getClass().getClassLoader());
            cls = classLoader.loadClass(className);

            classInfo = new ClassInfo(cls.getSimpleName());
            classInfo.setProperties(getProperties());
            classInfo.setConstructors(getConstructors());
            classInfo.setMethods(getMethods());

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public List<Property> getProperties() {
        List<Property> properties = new Vector<>();

        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            String modifier = getModifier(field.getModifiers());
            Property p = new Property(name, type, modifier);
            properties.add(p);
        }
        return properties;
    }

    public List<Constructor> getConstructors() {
        List<Constructor> constructors = new Vector<>();
        java.lang.reflect.Constructor<?>[] cstrs = cls.getDeclaredConstructors();

        for (java.lang.reflect.Constructor<?> constructor : cstrs) {
            String name = cls.getSimpleName();
            String modifier = getModifier(constructor.getModifiers());
            Constructor c = new Constructor(modifier, name);
            java.lang.reflect.Parameter[] params = constructor.getParameters();
            for (java.lang.reflect.Parameter p : params) {
                String paramName = p.getName();
                Class<?> paramType = p.getType();
                c.addParameter(new Property(paramName, paramType));
            }
            constructors.add(c);
        }
        return constructors;
    }

    public List<Method> getMethods() {
        List<Method> methods = new Vector<>();

        java.lang.reflect.Method[] allMethods = cls.getDeclaredMethods();
        for (java.lang.reflect.Method method : allMethods) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            String modifier = getModifier(method.getModifiers());
            Method m = new Method(modifier, returnType, name);
            java.lang.reflect.Parameter[] params = method.getParameters();
            for (java.lang.reflect.Parameter p : params) {
                m.addParameter(new Property(p.getName(), p.getType()));
            }
            methods.add(m);
        }
        return methods;
    }

    public String getSuperClass() {
        return cls.getSuperclass().getSimpleName();
    }

    public List<String> getInterfaces() {
        List<String> interfaces = new Vector<>();
        Class<?>[] inters = cls.getInterfaces();

        for (Class<?> inter : inters) {
            interfaces.add(inter.getSimpleName());
        }
        return interfaces;
    }

    public List<String> getNestedClasses() {
        List<String> nestedClasses = new Vector<>();
        Class<?>[] nested = cls.getDeclaredClasses();

        for (Class<?> nestedClass : nested) {
            nestedClasses.add(nestedClass.getSimpleName());
        }
        return nestedClasses;
    }

    public String getModifier(int m) {
        return Modifier.toString(m);
    }

    public ClassInfo getSkeleton() {
        return classInfo;
    }

    public void setSkeleton(ClassInfo skeleton) {
        this.classInfo = classInfo;
    }
}
