import java.lang.annotation.Annotation;
import java.lang.reflect.*;
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
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return propertyToFailArgListKVP;
    }

    public static void handleMethodExecutionForQuickCheckClass(Method[] alphabeticalOrdedMethodsToExecute, Map<String, Object[]> propertyToFailArgListKVP, Object instanceOfGivenClass)
    {
        boolean resultOfFunc = true;
        Annotation annotationOfCurrMethod = null;
        for (Method m : alphabeticalOrdedMethodsToExecute)
        {
            annotationOfCurrMethod  = m.getAnnotation(Property.class);
            if (annotationOfCurrMethod != null)
            {
                System.out.println("Curr Meth: " + m.getName());
                Object[] randParams = randomGeneratedParamsToPassToMethod(m, instanceOfGivenClass);
                try
                {
                    resultOfFunc = (boolean)m.invoke(instanceOfGivenClass, randParams);
                    if (!resultOfFunc)
                    {
                        propertyToFailArgListKVP.put(m.getName(), randParams);
                    }
                }
                catch(Exception e)
                {
                    propertyToFailArgListKVP.put(m.getName(), randParams);
                }
            }
        }
    }

    public static Object[] randomGeneratedParamsToPassToMethod(Method method, Object instanceOfGivenClass)
    {

        /* get parameters of method to be invoke */
        Parameter[] methodParameters = method.getParameters();
        Random rand = new Random();

        ArrayList<Object> paramsToPassIn = new ArrayList<Object>();
        Object generatedParam = null;
        Class<?> currAnnotation = null;

        /* iterate through each parameter */
        for (Parameter parameter : methodParameters)
        {
            /* get all elements of the annotaion of method to be invoke */
            Annotation[] paramAnnotations = parameter.getAnnotations();

            for (Annotation annotaion : paramAnnotations)
            {
                /* get annotation of current parameter */
                currAnnotation = annotaion.annotationType();
                switch(currAnnotation.getSimpleName())
                {
                    case "ListLength":
                        /* generate random number for the length of the list */
                        ListLength ll = (ListLength) annotaion;
                        int minLen = ll.min();
                        int maxLen = ll.max();
                        int listLen = rand.nextInt(maxLen - minLen + 1) + minLen;

                        /* get a list to store elements */
                        List<Object> list = new ArrayList<>();

                        /* get annotaion of the parameterize type of the list */
                        var annotaionType = method.getAnnotatedParameterTypes()[0];
                        var annotatedParametrizedType = ((AnnotatedParameterizedType)annotaionType).getAnnotatedActualTypeArguments()[0];
                        Annotation[] parameterizedAnnotations = annotatedParametrizedType.getAnnotations();

                        for (Annotation a : parameterizedAnnotations)
                        {
                            for (int j = 0; j < listLen; j++)
                            {
                                /* add random generated element into the list */
                                Object listObj = generateArgumentsBasedOnSimpleAnnotation(a);
                                list.add(listObj);

                            }
                        }
                        /* store parameters to pass to method upon invoking */
                        paramsToPassIn.add(list);
                        break;
                    case "IntRange":
                    case "StringSet":
                        /* generate random string or int and store them as parameter to pass to method upon invoking */
                        generatedParam = generateArgumentsBasedOnSimpleAnnotation(annotaion);
                        paramsToPassIn.add(generatedParam);
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
            }
        }
        return paramsToPassIn.toArray(new Object[paramsToPassIn.size()]);
    }

    // private static Object[] handleForAll(Annotation a, Object instanceOfGivenClass)
    // {
    //     ForAll fa = (ForAll) a;
    //     String funcToExecute = fa.name();
    //     Class<?> givenClass = instanceOfGivenClass.getClass();
    //     Method[] methods = givenClass.getMethods();
    //     for (Method m : methods)
    //     {
    //         if (m.getName().equals(funcToExecute))
    //         {
    //             return randomGeneratedParamsToPassToMethod(m, instanceOfGivenClass);
    //         }
    //     }
    //     return null;
    // }

    private static Object generateArgumentsBasedOnSimpleAnnotation(Annotation annotationOfParam)
    {
        Random rand = new Random();
        switch(annotationOfParam.annotationType().getSimpleName())
        {
            case "IntRange":
                /* get int range for parameter */
                IntRange ir = (IntRange) annotationOfParam;
                int min = ir.min();
                int max = ir.max();
    
                /* generate random number within range */
                return rand.nextInt(max - min + 1) + min;
            case "StringSet":
                /* get the set provided containing all the possible string to use as parameter */
                StringSet ss = (StringSet) annotationOfParam;
                String[] setOfStrings = ss.strings();
    
                /* get random string from the set */
                int randomIndexOfStr = rand.nextInt(setOfStrings.length);
                return setOfStrings[randomIndexOfStr];
            default:
                return null;
        }
    }
}