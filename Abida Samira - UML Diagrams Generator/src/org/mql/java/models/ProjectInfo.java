package org.mql.java.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProjectInfo {

	private String projectName;
	private String path;
	private Vector<PackageInfo> packages;

	public ProjectInfo() {
		this.packages = new Vector<>();
	}

	public ProjectInfo(String projectName) {
		this();
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<PackageInfo> getPackages() {
		return packages;
	}

	public void setPackages(Vector<PackageInfo> packages) {
		this.packages = packages;
	}

	public void addPackage(PackageInfo packageInfo) {


		this.packages.add(packageInfo);
	}

	public Vector<ClassInfo> getAllClasses() {
		Vector<ClassInfo> allClasses = new Vector<>();
		for (PackageInfo p : packages) {
			allClasses.addAll(p.getClasses());
		}
		return allClasses;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public void display() {
        System.out.println("Project: " + projectName);
        for (PackageInfo p : packages) {
            p.display(1);
        }
    }
	
}

