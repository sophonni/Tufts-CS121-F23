package jrails;

import java.util.*;
import java.lang.reflect.*;

public class JRouter {
    Map<String, String> verbAndPath_to_classAndMethod_Mapping = new LinkedHashMap<String, String>();
    public void addRoute(String verb, String path, Class clazz, String method) {
        /* ensure that parameters aren't null or empty */
        if (verb == null || path == null || clazz == null || method == null || verb.isEmpty() || path.isEmpty() || method.isEmpty())
        {
            throw new IllegalArgumentException("Error in {addRoute} function: parameter/s can't be null or empty string.");
        }
        else
        {
            /* concatenate verd and path */
            String verbAndpathConcatenation = verb + "#" + path;

            /* ensure that no duplicate verb and path is added */
            if (verbAndPath_to_classAndMethod_Mapping.containsKey(verbAndpathConcatenation))
            {
                throw new IllegalArgumentException("Error in {addRoute} function: key {" + verbAndpathConcatenation + "} already exist. Please provide a unique key.");
            }
            if (path.charAt(0) != '/')
            {
                throw new IllegalArgumentException("Error in {addRoute} function: first character of path {" + path + "} must be {/}.");
            }
            //System.err.println("Verb: " + verbAndpathConcatenation);
            /* concatenate class type and method's name */
            String classAndMethodConcatenation = clazz.getName() + "#" + method;
            System.err.println("Class: " + classAndMethodConcatenation);
            /* add the KVP to the map */
            verbAndPath_to_classAndMethod_Mapping.put(verbAndpathConcatenation, classAndMethodConcatenation);
        }
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        /* ensure that parameters aren't null or empty */
        if (verb == null || path == null || verb.isEmpty() || path.isEmpty())
        {
            throw new IllegalArgumentException("Error in {getRoute} function: parameter/s can't be null or empty string.");
        }
        else
        {
            String verbAndpathConcatenation = verb + "#" + path;
            return this.verbAndPath_to_classAndMethod_Mapping.get(verbAndpathConcatenation);
        }
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String verbAndpathConcatenation = verb + "#" + path;
        String classAndMethodConcatenation = this.verbAndPath_to_classAndMethod_Mapping.get(verbAndpathConcatenation);
        Html html = null;
        if (classAndMethodConcatenation != null)
        {
            try
            {
                /* parse for class name and method name */
                int indexOfFirstPoundSymbol = classAndMethodConcatenation.indexOf("#");
                String className = classAndMethodConcatenation.substring(0, indexOfFirstPoundSymbol);
                String methodName = classAndMethodConcatenation.substring(indexOfFirstPoundSymbol + 1, classAndMethodConcatenation.length());
                System.err.println("Class: " + className);
                System.err.println("Method: " + methodName);
    
                /* get access to the class */
                Class<?> runtimeClass = Class.forName(className);
                Constructor<?> constructorOfClass = runtimeClass.getDeclaredConstructor();
                Object instanceOfClass = constructorOfClass.newInstance();

                //SD TODO: in order for this function to work, whatever method "methodToInvoke" is, we need to implement that
                /* get the method from the class */
                Method methodToInvoke = runtimeClass.getMethod(methodName);
                System.err.println("Method: " + methodToInvoke);

                /* invoke the method */
                Object[] methodArgs = params.entrySet().toArray();
                html = (Html) methodToInvoke.invoke(instanceOfClass, methodArgs);
                return html;
            }
            catch (Exception e)
            {
                System.err.println("Error in {route} function: " + e);
            }
            return html;
        }
        else
        {
            throw new UnsupportedOperationException("Error in {route}: verb {" + verb + "} with path {" + path +"} route is unknown.");
        }
    }
}
