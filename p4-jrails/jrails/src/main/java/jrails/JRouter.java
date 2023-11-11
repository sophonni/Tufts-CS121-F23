package jrails;

import java.util.*;

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
            System.err.println("Verb: " + verbAndpathConcatenation);
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
        throw new UnsupportedOperationException();
    }
}
