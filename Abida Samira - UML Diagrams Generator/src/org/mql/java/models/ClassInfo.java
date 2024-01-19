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
	private List<String> interfaces;
	private String superClass;
	private List<String> internClass;
	private List<String> aggregationRelations;
	private List<String> usageRelations;

	public ClassInfo() {
		this.properties = new Vector<String>();
		this.constructors = new Vector<Constructor<?>>();
		this.methods = new Vector<Method>();
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

	public void addMethods(List<Method> methods) {
		this.methods.addAll(methods);
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

	public ClassInfo clone() {
		ClassInfo clonedClass = new ClassInfo(this.className);

		clonedClass.setProperties(new ArrayList<>(this.properties));
		clonedClass.setConstructors(new ArrayList<>(this.constructors));
		clonedClass.setMethods(new ArrayList<>(this.methods));
		clonedClass.setInterfaces(new ArrayList<>(this.interfaces));
		clonedClass.setSuperClass(this.superClass);
		clonedClass.setInternClass(new ArrayList<>(this.internClass));
		clonedClass.setAggregationRelations(new ArrayList<>(this.aggregationRelations));
		clonedClass.setUsageRelations(new ArrayList<>(this.usageRelations));
		return clonedClass;
	}
}

