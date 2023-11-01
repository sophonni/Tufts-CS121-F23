import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;
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
    public static void handleMethodExecutionForTestClass(Method[] alphabeticalOrdedMethodsToExecute, Map<String, Throwable> testCaseAndErrorKVP, Object instanceOfGivenClass)
    {
        Annotation annotationOfCurrMethod = null;
        /* iterate over and invoke each methods */
        for (Method method : alphabeticalOrdedMethodsToExecute)
        {
            //System.out.println("Curr Meth: " + method.getName());
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
            executeAndStoreResultForMethodForTestClass(method, instanceOfGivenClass, testCaseAndErrorKVP);
        }
    }

    private static void executeAndStoreResultForMethodForTestClass(Method method, Object instanceOfGivenClass, Map<String, Throwable> testCaseAndErrorKVP) {
        try
        {
            //System.out.println("Curr Metho: " + method.getName());
            // int lastDotIndex = method.toString().lastIndexOf('.');
            // int parentheses = method.toString().indexOf(')');
    
            // String functionName = method.toString().substring(lastDotIndex + 1, parentheses + 1);
    
            /* invoke methods and store their error/exception is any */
            method.invoke(instanceOfGivenClass);
            //System.out.println(method.getName());

            if (method.getAnnotation(Test.class) != null)
            {
                testCaseAndErrorKVP.put(method.getName(), null);
            }
        }
        catch (Exception e)
        {
            /* add the error or exception if there are any error encounter */
            if (method.getAnnotation(Test.class) != null)
            {
                Throwable originalException = e.getCause();
                testCaseAndErrorKVP.put(method.getName(), originalException);
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

        /* execute all methods with @BeforeClass Annotation */
        handleMethodExecutionForTestClass(beforeClassMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);

        for (Method testMethod : testMethodsSortedOrder)
        {
            /* execute all methods with @Before Annotation */
            handleMethodExecutionForTestClass(beforeTestMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);

            /* execute current test method with @Test Annotation */
            executeAndStoreResultForMethodForTestClass(testMethod, instanceOfGivenClass, testCaseAndErrorKVP);
        
            /* execute all methods with @After Annotation */
            handleMethodExecutionForTestClass(afterTestMethodsSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);
        }

        /* execute all methods with @AfterClass Annotation */
        handleMethodExecutionForTestClass(afterClassSortedOrder, testCaseAndErrorKVP, instanceOfGivenClass);
    }

/*********************************************************************************************************************************************/

    public static Map<String, Object[]> quickCheckClass(String name) {
        Map<String, Object[]> propertyToFailArgListKVP = new HashMap<>();
        assert propertyToFailArgListKVP != null;
        try
        {
            Class<?> runtimeClassOfGivenClass = Class.forName(name);
            Constructor<?> constructorOfGivenClass = runtimeClassOfGivenClass.getDeclaredConstructor();
            Object instanceOfGivenClass = constructorOfGivenClass.newInstance();

            Method[] allMethodsOfGivenClass = runtimeClassOfGivenClass.getMethods();            
            Method[] propertyMethods = getAllMethodsWithGivenAnnotation(Property.class, allMethodsOfGivenClass, instanceOfGivenClass);
            Method[] propertyMethodsSortedOrder = sortMethodsInAlphabeticalOrder(propertyMethods, runtimeClassOfGivenClass);
            handleMethodExecutionForQuickCheckClass(propertyMethodsSortedOrder, propertyToFailArgListKVP, instanceOfGivenClass);
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return propertyToFailArgListKVP;
    }

    public static void handleMethodExecutionForQuickCheckClass(Method[] alphabeticalOrderedMethodsToExecute, Map<String, Object[]> propertyToFailArgListKVP, Object instanceOfGivenClass) {
        for (Method method : alphabeticalOrderedMethodsToExecute)
        {
            Annotation annotationOfCurrMethod = method.getAnnotation(Property.class);
            if (annotationOfCurrMethod != null)
            {
                System.out.println("Curr Meth: " + method.getName());
    
                Parameter[] methodParameters = method.getParameters();
                ArrayList<Object> failParams = new ArrayList<>();
                generateAndInvokeCombinations(method, instanceOfGivenClass, methodParameters, 0, new Object[methodParameters.length], failParams);
    
                // Handle the results, e.g., adding to propertyToFailArgListKVP.
                if (failParams.isEmpty())
                {
                    propertyToFailArgListKVP.put(method.getName(), null);
                } else
                {
                    propertyToFailArgListKVP.put(method.getName(), failParams.toArray());
                    System.out.println();
                }
            }
        }
    }
    
    public static boolean generateAndInvokeCombinations(Method method, Object instanceOfGivenClass, Parameter[] methodParameters, int paramIndex, Object[] currentParams, ArrayList<Object> failParams) {
        if (paramIndex == methodParameters.length)
        {
            try
            {
                /* invoke the method with currentParams */
                boolean resultOfFunc = (boolean) method.invoke(instanceOfGivenClass, currentParams);
    
                /* store parameters that cause the method to fail */
                if (!resultOfFunc)
                {
                    for(Object param : currentParams)
                    {
                        failParams.add(param);
                    }
                    //failParams.add(currentParams.clone());

                    /* move to a new function */
                    return false;
                }
            }
            catch (Exception e)
            {
                /* store parameters that cause the method to throw exception */
                for(Object param : currentParams)
                {
                    failParams.add(param);
                }
                // failParams.add(currentParams.clone());
    
                /* move to a new function */
                return false;
            }
            return true;
        }
    
        Parameter parameter = methodParameters[paramIndex];
        Annotation[] paramAnnotations = parameter.getAnnotations();
    
        for (Annotation annotation : paramAnnotations)
        {
            /* generate possible values for the current parameter's annotation */
            Object[] allPossibleParams = generateAllPossibleValuesForAllParams(annotation, method);
    
            /* recursively iterate through the possible values */
            for (Object paramValue : allPossibleParams)
            {
                currentParams[paramIndex] = paramValue;
                boolean success = generateAndInvokeCombinations(method, instanceOfGivenClass, methodParameters, paramIndex + 1, currentParams, failParams);
                if (!success)
                {
                    /* if an exception occurs, move to a new function */
                    return false;
                }
            }
        }
        return true;
    }
    
    public static Object[] generateAllPossibleValuesForAllParams(Annotation annotaion, Method method)
    {
        Object[] allPossibleParams = null;
        Class<?> currAnnotation = annotaion.annotationType();
        switch(currAnnotation.getSimpleName())
        {
            case "ListLength":
                /* generate random number for the length of the list */
                ListLength ll = (ListLength) annotaion;
                int minLen = ll.min();
                int maxLen = ll.max();

                /* get a list to store elements */
                List<Object> list = new ArrayList<>();

                /* get annotaion of the parameterize type of the list */
                var annotaionType = method.getAnnotatedParameterTypes()[0];
                var annotatedParametrizedType = ((AnnotatedParameterizedType)annotaionType).getAnnotatedActualTypeArguments()[0];
                Annotation[] parameterizedAnnotations = annotatedParametrizedType.getAnnotations();

                for (Annotation a : parameterizedAnnotations)
                {
                    allPossibleParams = generateAllPossibleArguments(a);
                }
                if (minLen < 0)
                {
                    throw new IllegalArgumentException("Error: List length can't be less than 0.");
                }
                else
                {
                    ArrayList<Object[]> allPossLists = new ArrayList<>();

                    for (int i = 0; i <= maxLen; i++)
                    {
                        generateAllPossibleLists(i, allPossibleParams, allPossLists);
                    }
                    System.out.println("Size: " + allPossLists.size());
                }
                break;
            case "IntRange":
            case "StringSet":
                /* generate random string or int and store them as parameter to pass to method upon invoking */
                allPossibleParams = generateAllPossibleArguments(annotaion);
                break;
            case "ForAll":
                // ForAll fa = (ForAll) annotaion;
                // int numIteration = fa.times();

                // if (numIteration > 100)
                // {
                //     for (int i = 0; i < 100; i++)
                //     {
                //         paramsToPassIn.add(handleForAll(annotaion, instanceOfGivenClass));
                //     }
                // }
                // else
                // {
                //     for (int i = 0; i < numIteration; i++)
                //     {
                //         paramsToPassIn.add(handleForAll(annotaion, instanceOfGivenClass));
                //     }
                // }
                break;
            default:
                break;
        }
        return allPossibleParams;
    }
    private static void generateAllPossibleLists(int targetListLen, Object[] allPossibleElementsForList, ArrayList<Object[]> allPossLists) {
        // Use a Set to store unique lists
        Set<List<Object>> uniqueLists = new HashSet<>();
        
        /* list of length 0 */
        if (targetListLen == 0) {
            List<Object> listsWithLen0 = new ArrayList<>();
            uniqueLists.add(listsWithLen0);
        }
        /* list of length 1 */
        else if (targetListLen == 1) {
            for (Object element : allPossibleElementsForList) {
                /* create a length 1 list for each element out of all the possible elements */
                List<Object> listsWithLen1 = new ArrayList<>();
                listsWithLen1.add(element);
                uniqueLists.add(listsWithLen1);
            }
        } else {
            /* iterate through the existing lists */
            for (Object[] onePossList : allPossLists) {

                /* create a new list by adding each possible element to the existing possible list */
                for (Object element : allPossibleElementsForList) {
                    List<Object> newList = new ArrayList<>(Arrays.asList(onePossList));
                    newList.add(element);
    
                    if (newList.size() == targetListLen) {
                        /* if the new list has reached the desired length, add it to uniqueLists */
                        uniqueLists.add(newList);
                    }
                }
            }
        }
        
        /* add all unique lists to allPossLists */
        for (List<Object> uniqueList : uniqueLists) {
            //System.out.println("Here: " + uniqueLists);
            allPossLists.add(uniqueList.toArray());
        }
    }

    private static Object[] generateAllPossibleArguments(Annotation annotationOfParam)
    {
        ArrayList<Object> allPossibleArguments = new ArrayList<Object>();
        
        switch(annotationOfParam.annotationType().getSimpleName())
        {
            case "IntRange":
                /* get int range for parameter */
                IntRange ir = (IntRange) annotationOfParam;
                int min = ir.min();
                int max = ir.max();

    
                if (max < min)
                {
                    throw new IllegalArgumentException("Error: Max < Min");
                }
                /* generate random number within range */
                for (int i = min; i <= max; i++)
                {
                    allPossibleArguments.add(i);
                }
                return allPossibleArguments.toArray(new Object[allPossibleArguments.size()]);
            case "StringSet":
                /* get the set provided containing all the possible string to use as parameter */
                StringSet ss = (StringSet) annotationOfParam;
                String[] setOfStrings = ss.strings();
    
                for (String s : setOfStrings)
                {
                    allPossibleArguments.add(s);
                }
                return allPossibleArguments.toArray();
            default:
                return null;
        }
    }
}