import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class Unit {
    public static Map<String, Throwable> testClass(String name) {
        Map<String, Throwable> testCaseAndErrorKVP = new HashMap<>();
        try
        {
            Class<?> instanceOfGivenClass = Class.forName(name);
            //Constructor<?> testConstructor = instanceOfGivenClass.getConstructor(Test.class);
            Method[] allMethodsOfGivenClass = instanceOfGivenClass.getMethods();
            Method[] testMethods = getAllMethodsWithGivenAnnotation(Test.class, allMethodsOfGivenClass);
            //assert testMethods.length == 2 TODO: for testing purposes;


            Method[] beforeClassMethods = getAllMethodsWithGivenAnnotation(BeforeClass.class, allMethodsOfGivenClass);
            //assert beforeClassMethods.length == 1 TODO: for testing purposes;

            Method[] afterClass = getAllMethodsWithGivenAnnotation(AfterClass.class, allMethodsOfGivenClass);
            //assert afterClass.length == 1 TODO: for testing purposes;
            
            Method[] beforeTestMethods = getAllMethodsWithGivenAnnotation(Before.class, allMethodsOfGivenClass);
            //assert beforeTestMethods.length == 1 TODO: for testing purposes;
            
            Method[] afterTestMethods = getAllMethodsWithGivenAnnotation(After.class, allMethodsOfGivenClass);
            //assert afterTestMethods.length == 2 TODO: for testing purposes;
            
            /* Psuedocode
             * get the instance of the class
             * get and store all the methods of the class
             * iterate through the list methods and invoke them
                * if the method throw an exception or error, store the name of the method and the exception/error the method thros
                * else, store the method's name and null
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
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return testCaseAndErrorKVP;
    }

    //TODO: make private
    private static Method[] getAllMethodsWithGivenAnnotation(Class<? extends Annotation> targetAnnotation, Method[] allMethods) {
        List<Method> methodsWithGivenAnnotation = new ArrayList<>();
        for (Method method : allMethods)
        {
            /* get all methods that have annotation matches with what callee provides */
            Annotation a = method.getAnnotation(targetAnnotation);
            if (a != null)
            {
                methodsWithGivenAnnotation.add(method);
            }
        }

        return methodsWithGivenAnnotation.toArray(new Method[0]); // Specify the type parameter
    }

    //TODO: make private
    public static Method[] sortMethodsInAlphabeticalOrder(Method[] listOfMethodsToSort, Class<?> instanceOfGivenClass )
    {
        /* store the mapping for each method and their string representation */
        Map<String, Method> StringRepesentationToMethodMapping = new HashMap<>();
        List<String> stringRepresentationOfMethods = new ArrayList<>();
        for (Method m : listOfMethodsToSort)
        {
            /* only get functions of the given class excluding the java ones */
            if (m.toString().contains(instanceOfGivenClass.getName()))
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

    public static Map<String, Object[]> quickCheckClass(String name) {
	throw new UnsupportedOperationException();
    }
}