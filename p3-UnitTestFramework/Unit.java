import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class Unit {
    public static Map<String, Throwable> testClass(String name) {
        Map<String, Throwable> testCaseAndErrorKVP = new HashMap<>();
        try
        {
            Class<?> runtimeClassOfGivenClass = Class.forName(name);
            Constructor<?> constructorOfGivenClass = runtimeClassOfGivenClass.getDeclaredConstructor();
            Object instanceOfGivenClass = constructorOfGivenClass.newInstance();
            processFunctionsAndStoreResultAccordingToSpec(testCaseAndErrorKVP, instanceOfGivenClass);
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return testCaseAndErrorKVP;
    }

    //TODO: make private
    private static Method[] getAllMethodsWithGivenAnnotation(Class<? extends Annotation> targetAnnotation, Method[] allMethods, Object instanceOfGivenClass) {
        List<Method> methodsWithGivenAnnotation = new ArrayList<>();
        for (Method method : allMethods)
        {
            if (method.getClass().equals(instanceOfGivenClass.getClass()))
            {
                Annotation[] annotations = method.getAnnotations();
                if (annotations.length != 1)
                {
                    throw new IllegalArgumentException("Error: Method '" + method.toString() + "' should have 1 annotation.");
                }
            }
            /* get all methods that have annotation matches with what callee provides */
            Annotation annotationOfCurrMethod = method.getAnnotation(targetAnnotation);
            if (annotationOfCurrMethod != null)
            {
                methodsWithGivenAnnotation.add(method);
            }
        }

        return methodsWithGivenAnnotation.toArray(new Method[0]); // Specify the type parameter
    }

    //TODO: make private
    public static Method[] sortMethodsInAlphabeticalOrder(Method[] listOfMethodsToSort, Class<?> runtimeClassOfGivenClass)
    {
        /* store the mapping for each method and their string representation */
        Map<String, Method> StringRepesentationToMethodMapping = new HashMap<>();
        List<String> stringRepresentationOfMethods = new ArrayList<>();
        for (Method m : listOfMethodsToSort)
        {
            /* only get functions of the given class excluding the java ones */
            if (m.toString().contains(runtimeClassOfGivenClass.getName()))
            {
                StringRepesentationToMethodMapping.put(m.toString(), m);
                stringRepresentationOfMethods.add(m.toString());
            }
        }

        /* alphabetically/lexicographically sort the list containing string representation of methods */
        Collections.sort(stringRepresentationOfMethods);

        /* get the methods from the mapping and add to the return-array
        using elements in the sorted list as key in order to maintain the
        sort */
        List<Method> methodsInSortedOrder = new ArrayList<>();
        Method currMethod = null;
        for(String strRepOfMethod : stringRepresentationOfMethods)
        {
            currMethod = StringRepesentationToMethodMapping.get(strRepOfMethod);
            methodsInSortedOrder.add(currMethod);
        }
        return methodsInSortedOrder.toArray(new Method[0]);
    }

    //TODO: make private
    public static void handleMethodExecution(Method[] alphabeticalOrdedMethodsToExecute, Map<String, Throwable> testCaseAndErrorKVP, Object instanceOfGivenClass)
    {
        Annotation annotationOfCurrMethod = null;
        /* iterate over and invoke each methods */
        for (Method method : alphabeticalOrdedMethodsToExecute)
        {
            //System.out.println(method.toString());
            /* ensure that methods with annotation 'BeforeClass' and 'AfterClass' only appear on static methods */
            annotationOfCurrMethod  = method.getAnnotation(BeforeClass.class);
            if (annotationOfCurrMethod != null)
            {
                /* throw exception if 'Before' annotation appear on non-static methods */
                if (!Modifier.isStatic(method.getModifiers()))
                {
                    throw new IllegalArgumentException("Error: @BeforeClass annotation appears on non-static method '" + method.toString() + "'.");
                }
            }

            annotationOfCurrMethod  = method.getAnnotation(AfterClass.class);
            if (annotationOfCurrMethod != null)
            {
                /* throw exception if 'AfterClass' annotation appear on non-static methods */
                if (!Modifier.isStatic(method.getModifiers()))
                {
                    throw new IllegalArgumentException("Error: @AfterClass annotation appears on non-static method '" + method.toString() + "'.");
                }
            }
            executeAndStoreResultForMethod(method, instanceOfGivenClass, testCaseAndErrorKVP);
        }
    }

    private static void executeAndStoreResultForMethod(Method method, Object instanceOfGivenClass, Map<String, Throwable> testCaseAndErrorKVP) {
        try
        {
            int lastDotIndex = method.toString().lastIndexOf('.');
            int parentheses = method.toString().indexOf(')');
    
            String functionName = method.toString().substring(lastDotIndex + 1, parentheses + 1);
    
            /* invoke methods and store their error/exception is any */
            method.invoke(instanceOfGivenClass);
            testCaseAndErrorKVP.put(functionName, null);
        }
        catch (Exception e)
        {
            /* add the error or exception if there are any error encounter */
            //System.out.println("Add exception for: " + method.toString());
            if (method.getAnnotation(Test.class) != null)
            {
                Throwable originalException = e.getCause();
                testCaseAndErrorKVP.put(method.toString(), originalException);
            }
        }
    }
    public static void processFunctionsAndStoreResultAccordingToSpec(Map<String, Throwable> testCaseAndErrorKVP, Object instanceOfGivenClass)
    {
        /* prepare methods for processing */
        Class<?> runtimeClassOfGivenClass = instanceOfGivenClass.getClass();
        Method[] allMethodsOfGivenClass = runtimeClassOfGivenClass.getMethods();

        /* 1st */
        Method[] beforeClassMethods = getAllMethodsWithGivenAnnotation(BeforeClass.class, allMethodsOfGivenClass, instanceOfGivenClass);
        Method[] beforeClassMethodsSortedOrder = sortMethodsInAlphabeticalOrder(beforeClassMethods, runtimeClassOfGivenClass);

        /* 2nd */
        Method[] beforeTestMethods = getAllMethodsWithGivenAnnotation(Before.class, allMethodsOfGivenClass, instanceOfGivenClass);
        Method[] beforeTestMethodsSortedOrder = sortMethodsInAlphabeticalOrder(beforeTestMethods, runtimeClassOfGivenClass);
        
        /* 3rd */
        Method[] testMethods = getAllMethodsWithGivenAnnotation(Test.class, allMethodsOfGivenClass, instanceOfGivenClass);
        Method[] testMethodsSortedOrder = sortMethodsInAlphabeticalOrder(testMethods, runtimeClassOfGivenClass);
        
        /* 4th */
        Method[] afterTestMethods = getAllMethodsWithGivenAnnotation(After.class, allMethodsOfGivenClass, instanceOfGivenClass);
        Method[] afterTestMethodsSortedOrder = sortMethodsInAlphabeticalOrder(afterTestMethods, runtimeClassOfGivenClass);

        /* 5th */
        Method[] afterClass = getAllMethodsWithGivenAnnotation(AfterClass.class, allMethodsOfGivenClass, instanceOfGivenClass);
        Method[] afterClassSortedOrder = sortMethodsInAlphabeticalOrder(afterClass, runtimeClassOfGivenClass);
        

        /* Psuedocode
            * 1. execute all the before class methods
            * 2. iterate through all the test cases methods
            *      - iterate through all the beforeTestMethods and execute all of them
            *      - execute the current test methods
            *      - iterate through all the afterTestMethods and execute all of them
            * 3. iterate through all the afterClassMethods and execute all of them
        */
        /* Other important notes
            * test cases are methods annotated with @Test
            * execute each methods in alphabetical other
            * 
            * if there is ONE method annotated with @BeforeClass, execute this method first
            * if there are multiple methods annotated with @BeforeClass, execute them in alphabetical order
            * 
            * if there is ONE method annotated with @Before, execute this method before any test cases methods
            * if there are multiple methods annotated with @Before, execute them in alphabetical order
            * 
            * if there is ONE method annotated with @AfterClass, execute this method last
            * if there are multiple methods annotated with @AfterClass, execute them in alphabetical order
            * 
            * if there is ONE method annotated with @After, execute this method after any test cases methods
            * if there are multiple methods annotated with @After, execute them in alphabetical order
            * 
            * methods annotated with @BeforeClass and @AfterClass gets executed regardless if there are any test cases methods
            * 
            * @BeforeClass and @AfterClass can only appear on methods that are static, testClass throw exception other wise
            * 
            * if a method has 2 annotation, throw exception
            * if a method annoation is not one of these: {@Test, @BeforeClass, @Before, @AfterClass, @After}, throw exception
            */

        /* execute all methods with @BeforeClass Annotation */
        handleMethodExecution(beforeClassMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);

        for (Method testMethod : testMethodsSortedOrder)
        {
            /* execute all methods with @Before Annotation */
            handleMethodExecution(beforeTestMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);

            /* execute current test method with @Test Annotation */
            executeAndStoreResultForMethod(testMethod, instanceOfGivenClass, testCaseAndErrorKVP);
        
            /* execute all methods with @After Annotation */
            handleMethodExecution(afterTestMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);
        }

        /* execute all methods with @AfterClass Annotation */
        handleMethodExecution(afterClassSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);
    }

    public static Map<String, Object[]> quickCheckClass(String name) {
	throw new UnsupportedOperationException();
    }
}