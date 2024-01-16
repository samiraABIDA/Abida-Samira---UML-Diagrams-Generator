package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader  {
	URLClassLoader myClassLoader;
	private Class<?> maClasse = null;
	
	public MyClassLoader(String projectClassPath, String classqualifiedname) {
		 try {
	        	URL[] classPath = {new File(projectClassPath).toURI().toURL()};
	            myClassLoader = new URLClassLoader(classPath, ClassLoader.getSystemClassLoader());
	            maClasse = myClassLoader.loadClass(classqualifiedname); 
	        }
	        catch (Exception e) {
				e.printStackTrace();
			}
	}
	//*********
	public Class<?> getMaClass() {
		return maClasse;
	}
	
	public void closeClassLoader() {
		try {
		myClassLoader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
