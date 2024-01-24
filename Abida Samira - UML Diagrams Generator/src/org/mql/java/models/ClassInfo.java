package org.mql.java.models;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ClassInfo {

	private String className;
	private List<String> properties;
	private List<Constructor<?>> constructors;
	private List<Method> methods;
	private Vector<String> methodNames;
	private List<String> interfaces;
	private String superClass;
	private List<String> internClass;
	private List<String> aggregationRelations;
	private List<String> usageRelations;
	private List<String> methodsNames;

	public ClassInfo() {
		this.properties = new Vector<String>();
		this.constructors = new Vector<Constructor<?>>();
		this.methods = new Vector<Method>();
		this.interfaces = new Vector<>();  
		this.internClass = new Vector<>();
		this.aggregationRelations = new Vector<String>();
		this.usageRelations = new Vector<String>();
	}

	public ClassInfo(String className) {
		this();
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	public void addProperty(String property) {
		this.properties.add(property);
	}

	public List<Constructor<?>> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<Constructor<?>> constructors) {
		this.constructors = constructors;
	}

	public void addConstructor(Constructor<?> constructor) {
		this.constructors.add(constructor);
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public void addMethod(Method method) {
		this.methods.add(method);
	}

	public void addMethodName(String methodName) {
		methodNames.add(methodName);
	}

	public void setMethodsNames(List<String> methodsNames) {
		this.methodsNames = methodsNames;
	}

	public List<String> getMethodNames() {
		List<String> methodNames = new ArrayList<>();
		for (Method method : methods) {
			String methodName = method.getName();
			int lastDotIndex = methodName.lastIndexOf('.');
			methodNames.add((lastDotIndex != -1) ? methodName.substring(lastDotIndex + 1) : methodName);
		}
		return methodNames;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getInternClass() {
		return internClass;
	}

	public void setInternClass(List<String> internClass) {
		this.internClass = internClass;
	}


	public List<String> getAggregationRelations() {
		return aggregationRelations;
	}

	public void setAggregationRelations(List<String> aggregationRelations) {
		this.aggregationRelations = aggregationRelations;
	}

	public void addAggregationRelation(String className) {
		this.aggregationRelations.add(className);
	}

	public List<String> getUsageRelations() {
		return usageRelations;
	}

	public void setUsageRelations(List<String> usageRelations) {
		this.usageRelations = usageRelations;
	}

	public void addUsageRelation(String usageRelation) {
		this.usageRelations.add(usageRelation);
	}

	@Override
	public String toString() {
		return "ClassInfo [className=" + className + ", properties=" + properties + ", constructors=" + constructors
				+ ", methods=" + methods + ", interfaces=" + interfaces + ", superClass=" + superClass
				+ ", internClass=" + internClass + ", aggregationRelations=" + aggregationRelations + "]";
	}



	public void addInternClass(String internClass) {
		if (this.internClass == null) {
			this.internClass = new Vector<>();
		}
		this.internClass.add(internClass);
	}

	public void addInterfaces(List<String> interfaces) {
		if (this.interfaces == null) {
			this.interfaces = new Vector<>();
		}
		this.interfaces.addAll(interfaces);
	}

	public void addInterface(String interfaceName) {
		this.interfaces.add(interfaceName);
	}



	public ClassInfo clone() {
		ClassInfo clonedClass = new ClassInfo(this.className);

		clonedClass.setProperties(new Vector<>(this.properties));
		clonedClass.setConstructors(new Vector<>(this.constructors));
		clonedClass.setMethods(new Vector<>(this.methods));
		clonedClass.setInterfaces(new Vector<>(this.interfaces));
		clonedClass.setSuperClass(this.superClass);
		clonedClass.setInternClass(new Vector<>(this.internClass));
		clonedClass.setAggregationRelations(new Vector<>(this.aggregationRelations));
		clonedClass.setUsageRelations(new Vector<>(this.usageRelations));
		return clonedClass;
	}

	public void display(int indentationLevel) {
		String indentation = "  ".repeat(indentationLevel); 
		System.out.println(indentation + "Class: " + className);


		System.out.println(indentation + "  Properties: " + properties);


		System.out.println(indentation + "  Constructors:");
		for (Constructor<?> constructor : constructors) {
			System.out.println(indentation + "    " + constructor);
		}


		System.out.println(indentation + "  Methods:");
		for (Method method : methods) {
			System.out.println(indentation + "    " + method);
		}

		System.out.println(indentation + "  Aggregation Relations: " + aggregationRelations);


		System.out.println(indentation + "  Usage Relations: " + usageRelations);


		System.out.println(indentation + "  Inner Classes: " + internClass);


	}
}

