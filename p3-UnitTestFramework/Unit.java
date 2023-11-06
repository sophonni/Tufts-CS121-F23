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
        try
        {
            Class<?> runtimeClassOfGivenClass = Class.forName(name);
            Constructor<?> constructorOfGivenClass = runtimeClassOfGivenClass.getDeclaredConstructor();
            Object instanceOfGivenClass = constructorOfGivenClass.newInstance();

            Method[] allMethodsOfGivenClass = runtimeClassOfGivenClass.getMethods();            
            Method[] propertyMethods = getAllMethodsWithGivenAnnotation(Property.class, allMethodsOfGivenClass, instanceOfGivenClass);
            Method[] propertyMethodsSortedOrder = sortMethodsInAlphabeticalOrder(propertyMethods, runtimeClassOfGivenClass);
            handleMethodExecutionForQuickCheckClass(propertyMethodsSortedOrder, propertyToFailArgListKVP, instanceOfGivenClass);
            System.out.println("Size: " + propertyToFailArgListKVP.size());
            
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Error: " + e);
        }
        return propertyToFailArgListKVP;
    }

    public static void handleMethodExecutionForQuickCheckClass(Method[] alphabeticalOrderedMethodsToExecute, Map<String, Object[]> propertyToFailArgListKVP, Object instanceOfGivenClass) {
        Class<?> givenClass = instanceOfGivenClass.getClass();

        Map<String, List<Object>> paramNameAndTheirPossValsKVP = new LinkedHashMap<>();
        for (Method method : alphabeticalOrderedMethodsToExecute)
        {
            /* ensure that the method with @Property as annotation returns a 'bool' */
            if (method.getReturnType().equals(Boolean.TYPE))
            {
                List<Object> failParams = new ArrayList<>();
                List<Object> ForAllExceptions = new ArrayList<>();
                ArrayList<Object[]> allPossLists = new ArrayList<>();
                Annotation annotationOfCurrMethod = method.getAnnotation(Property.class);
                if (annotationOfCurrMethod != null)
                {
                    System.out.println("Curr Meth: " + method.getName());
        
                    Parameter[] methodParameters = method.getParameters();
    
                    /* only 1 parameter is passed in */
                    if (methodParameters.length == 1)
                    {
                        Parameter theOneParam = methodParameters[0];
                        Annotation[] annotations = theOneParam.getAnnotations();
                        Annotation annotaionOfTheOneParam = annotations[0];
                        Class<?> annotaionOfTheOneParamClass = annotaionOfTheOneParam.annotationType();
                        Object[] allPossValsForParam = generateAllPossibleValuesForCurrParam(annotaionOfTheOneParam, method, allPossLists, givenClass, instanceOfGivenClass, ForAllExceptions);
                        
                        /* the 1 parameter that is passed in has @ListLength as annotation */
                        if (allPossLists.size() != 0)
                        {
                            int maxIteration = 1;
                            /* iterate through all the possible lists */
                            for (Object[] onePossList : allPossLists)
                            {
                                /* ensure to only invoke the current method <= 100 times */
                                if (maxIteration <= 100)
                                {
                                    /* create a new list using the current possible list, to pass as parameter */
                                    List<Object> onePossListToPassIn = new ArrayList<>(Arrays.asList(onePossList));

                                    try
                                    {
                                        /* invoking the current method with the current possible list */
                                        boolean resultAfterInvoking = (boolean)method.invoke(instanceOfGivenClass, onePossListToPassIn);
                                        
                                        /* store the current possible list if the method returns false */
                                        if (!resultAfterInvoking)
                                        {
                                            failParams.add(onePossListToPassIn);
                                            break;
                                        }
                                    }
                                    catch (Exception e)
                                    {
                                        /* store the current possible list if the method throw an exception */
                                        failParams.add(onePossListToPassIn);
                                        break;
                                    }
                                }
                                else
                                {
                                    break;
                                }
                                maxIteration++;
                            }
                        }
                        /* the 1 parameter that is passed in has either @ForAll as parameter*/
                        else if (annotaionOfTheOneParamClass.getSimpleName().equals("ForAll"))
                        {
                            /* the method with the provided name executed '0 <= n <= 100' times with no exception thrown */
                            if (ForAllExceptions.isEmpty())
                            {
                                int maxIteration = (allPossValsForParam.length) > 100 ? 100 : allPossValsForParam.length;
                                handleInvokingAndStoringParameterThatCauseFailure(failParams, null, allPossValsForParam, maxIteration, instanceOfGivenClass, method);
                            }
                            else
                            {
                                throw new IllegalArgumentException("Error: Exception encounter upon invoking the method with name provided via parameter");
                            }
                        }
                        /* the 1 parameter that is passed in has either @Stringset or @Inrange as parameter*/
                        else
                        {
                            int maxIteration = (allPossValsForParam.length) > 100 ? 100 : allPossValsForParam.length;
                            handleInvokingAndStoringParameterThatCauseFailure(failParams, null, allPossValsForParam, maxIteration, instanceOfGivenClass, method);
                        }
                    }
                    /* current method takes in 'n' number of parameter where n > 1 */
                    else
                    {
                        List<Object> allPossListsList = new ArrayList<>();
                        for (Parameter currParam : methodParameters)
                        {
                            Annotation[] annotations = currParam.getAnnotations();
                            if (annotations.length != 1)
                            {
                                throw new IllegalArgumentException("Error: Each parameter should have 1 annotation.");
                            }
                            else
                            {
                                // List<Object> po = new ArrayList<>(Arrays.asList(annotations)); 
                                //System.out.print("Annotation: " + po);

                                /* get the annotation of the current parameter */
                                Annotation annotaionOfCurrParam = annotations[0];        
                                
                                /* get all the possible values for the current parameter */
                                Object[] allPossValsForParam = generateAllPossibleValuesForCurrParam(annotaionOfCurrParam, method, allPossLists, givenClass, instanceOfGivenClass, ForAllExceptions);
                                
                                handleStoringAllPossValsForParameter(currParam, annotaionOfCurrParam, paramNameAndTheirPossValsKVP, allPossValsForParam, allPossListsList);
                            }
                        }
                        /* generate all possible combination of parameters */
                        List<List<Object>> possCombinationsOfParams = generateAllCombinations(paramNameAndTheirPossValsKVP);
    
                        /* ensure to only invoke the current method <= 100 times */
                        int maxIteration = possCombinationsOfParams.size() > 100 ? 100 : possCombinationsOfParams.size();
                        handleInvokingAndStoringParameterThatCauseFailure(failParams, possCombinationsOfParams, null, maxIteration, instanceOfGivenClass, method);
                    }
                }

                /* store a mapping of method name to 'null' since it has been exceuted with no failure */
                if (failParams.isEmpty())
                {
                    propertyToFailArgListKVP.put(method.getName(), null);
                }
    
                /* store a mapping of method name to a list of parameter/s that cause the method to fail or throw exception */
                else
                {
                    propertyToFailArgListKVP.put(method.getName(), failParams.toArray());
                }
            }
            else
            {
                throw new IllegalArgumentException("Error: Method {" + method.getName() + "} should returns a bool.");
            }
        }
    }

    private static void handleStoringAllPossValsForParameter(Parameter param, Annotation annotationOfParam, Map<String, List<Object>> paramNameAndTheirPossValsKVP, Object[] allPossValForParam, List<Object> allPossListsList)
    {
        Class<?> annotaionOfParamClass = annotationOfParam.annotationType();

        /* current parameter of the current method is @ListLength */
        if (annotaionOfParamClass.getSimpleName().equals("ListLength"))
        {
            /* store all the possible combinations of list with all possible size */
            paramNameAndTheirPossValsKVP.put(param.toString(), allPossListsList);
        }
        /* current parameter of the current method is @ForAll */
        else if (annotaionOfParamClass.getSimpleName().equals("ForAll"))
        {
            List<Object> listOfPossVal = new ArrayList<>(Arrays.asList(allPossValForParam)); 
            /* store all the possible combinations of object */
            paramNameAndTheirPossValsKVP.put(param.toString(), listOfPossVal);
        }
        /* current parameter of the current method is either @StringSet or @IntRange */
        else
        {
            List<Object> listOfPossVal = new ArrayList<>(Arrays.asList(allPossValForParam));
            /* store all the possible values for current parameter */
            paramNameAndTheirPossValsKVP.put(param.toString(), listOfPossVal);
        }
    }

    private static void handleInvokingAndStoringParameterThatCauseFailure(List<Object> failParams, List<List<Object>> possCombinationsOfParams, Object[] allPossValsForParam, int maxIteration, Object instanceOfGivenClass, Method methodToInvoke)
    {
        int currIteration = 1;
        if (possCombinationsOfParams != null)
        {
            for (List<Object> combinationOfParams : possCombinationsOfParams)
            {
                /* ensure to only invoke the method a maximum of 100 times */
                if (currIteration <= maxIteration)
                {
                    try
                    {
                        /* invoke the method using the current combination of parameters */
                        boolean resultAfterInvoking = (boolean)methodToInvoke.invoke(instanceOfGivenClass, combinationOfParams.toArray());
                        
                        /* store each parameter's value from the combination of parameters that cause the function to fail */
                        if (!resultAfterInvoking)
                        {
                            for (Object paramVal : combinationOfParams)
                            {
                                failParams.add(paramVal);
                            }
                            break;
                        }
                    }
                    catch (Exception e)
                    {
                        /* store each parameter's value from the combination of parameters that cause the function to throw exception */
                        for (Object paramVal : combinationOfParams)
                        {
                            failParams.add(paramVal);
                        }
                        break;
                    }
                }
                currIteration++;
            }
        }
        else
        {
            //int currIteration = 1;
            for (Object onePossVal : allPossValsForParam)
            {
                /* ensure to only invoke the method a maximum of 100 times */
                if (currIteration <= maxIteration)
                {
                    try
                    {
                        boolean resultAfterInvoking = (boolean)methodToInvoke.invoke(instanceOfGivenClass, onePossVal);
                        /* store the current possible values if the method returns false */
                        if (!resultAfterInvoking)
                        {
                            failParams.add(onePossVal);
                            break;
                        }
                    }
                    catch (Exception e)
                    {
                        /* store the current possible values if the method throw an exception */
                        failParams.add(onePossVal);
                        break;
                    }
                }
                currIteration++;
            }
        }
    }

    private static List<List<Object>> generateAllCombinations(Map<String, List<Object>> parameterMap)
    {
        List<List<Object>> combinations = new ArrayList<>();
        List<List<Object>> parameterValues = new ArrayList<>(parameterMap.values());
        Object[] currentCombination = new Object[parameterMap.size()];
        int paramIndex = 0;
        int currIteration = 1;
    
        /* generate a list of all possible combinations of parameters */
        generateCombinations(parameterValues, combinations, currentCombination, paramIndex, currIteration);
        
        /* return all possible combinations of parameters */
        return combinations;
    }
    
    private static void generateCombinations(List<List<Object>> parameters, List<List<Object>> combinations, Object[] currentCombination, int paramIndex, int currIteration)
    {
        if (paramIndex == parameters.size())
        {
            List<Object> possVal = new ArrayList<>(Arrays.asList(currentCombination));
            /* we have a complete combination */
            combinations.add(possVal);
            if (currIteration == 100)
            {
                return;
            }
        }
        else
        {
            List<Object> currentParameterValues = parameters.get(paramIndex);
            for (Object paramValue : currentParameterValues)
            {
                currentCombination[paramIndex] = paramValue;
                generateCombinations(parameters, combinations, currentCombination, paramIndex + 1, currIteration + 1);
            }
        }
    }
    
    public static Object[] generateAllPossibleValuesForCurrParam(Annotation annotaion, Method method, ArrayList<Object[]> allPossLists, Class<?> givenClass, Object instanceOfGivenClass, List<Object> ForAllAnnotationExceptions)
    {
        Object[] paramPossVal = null;
        Class<?> currAnnotation = annotaion.annotationType();
        switch(currAnnotation.getSimpleName())
        {
            case "ListLength":
                /* generate random number for the length of the list */
                ListLength ll = (ListLength) annotaion;
                int minLen = ll.min();
                int maxLen = ll.max();

                /* get annotaion of the parameterize type of the list */
                var annotaionType = method.getAnnotatedParameterTypes()[0];
                var annotatedParametrizedType = ((AnnotatedParameterizedType)annotaionType).getAnnotatedActualTypeArguments()[0];
                Annotation[] parameterizedAnnotations = annotatedParametrizedType.getAnnotations();

                /* parameterize type can either be @IntRange or @StringSet */
                for (Annotation a : parameterizedAnnotations)
                {
                    paramPossVal = getPossValsForIntRangeOrStrSet(a);
                }

                /* edge case consideration */
                if (minLen < 0)
                {
                    throw new IllegalArgumentException("Error: List length can't be less than 0.");
                }
                else
                {
                    for (int i = 0; i <= maxLen; i++)
                    {
                        generateAllPossibleLists(i, paramPossVal, allPossLists);
                    }
                }
                break;
            case "IntRange":
            case "StringSet":
                /* generate random string or int and store them as parameter to pass to method upon invoking */
                paramPossVal = getPossValsForIntRangeOrStrSet(annotaion);
                break;
            case "ForAll":
                ForAll fa = (ForAll) annotaion;
                int numIteration = fa.times();
                String functionsName = fa.name();
                List<Object> allPossObj = new ArrayList<>();

                Method methodToGenrateVals = null;                
                try
                {
                    methodToGenrateVals = givenClass.getMethod(functionsName);

                    /* ensure the name of the method provided as arg is non-static */
                    if (Modifier.isStatic(methodToGenrateVals.getModifiers()))
                    {
                        throw new IllegalArgumentException("Error: Provided method to generate values should not be static.");
                    }

                }
                catch (Exception e)
                {
                    ForAllAnnotationExceptions.add(e);
                }
                
                if (ForAllAnnotationExceptions.isEmpty())
                {
                    /* ensure to only invoke the name of the method passed in as parameter <= 100 times */
                    int totalNumOfMethodCall = (numIteration > 100) ? 100 : numIteration;
                    for (int i = 1; i <= totalNumOfMethodCall; i++)
                    {
                        try
                        {
                            /* invoke on the method with the provided name */
                            Object returnValOfProvidedMethodName = methodToGenrateVals.invoke(instanceOfGivenClass);
                            allPossObj.add(returnValOfProvidedMethodName);
    
                        }
                        catch (Exception e)
                        {
                            ForAllAnnotationExceptions.add(e);
                        }
                    }
                    return allPossObj.toArray();
                }
                else
                {
                    return null;
                }
            default:
                break;
        }
        return paramPossVal;
    }

    private static void generateAllPossibleLists(int targetListLen, Object[] allPossibleElementsForList, ArrayList<Object[]> allPossLists) {
        /* use a Set to store unique lists */
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

    private static Object[] getPossValsForIntRangeOrStrSet(Annotation annotationOfParam)
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