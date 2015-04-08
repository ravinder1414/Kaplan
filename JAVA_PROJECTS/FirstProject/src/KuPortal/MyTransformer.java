package KuPortal;

import org.testng.IAnnotationTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.annotations.ITestAnnotation;

public class MyTransformer implements IAnnotationTransformer{
	@Override
	  public void transform(ITestAnnotation annotation, @SuppressWarnings("rawtypes") Class testClass, @SuppressWarnings("rawtypes") Constructor testConstructor, Method testMethod)
	  {	 
		System.out.println(annotation.getGroups().toString());
		System.out.println(testMethod.getName());
		
		  int i=9;
		  if(i==9)
		  {
		    if ("CheckMyAccount".equals(testMethod.getName()))
		    {
		    	annotation.setEnabled(false);
	        }
		  }
	  }
	
}