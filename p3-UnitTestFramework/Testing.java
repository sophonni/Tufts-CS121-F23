import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.util.*;

public class Testing
{
    public static void main(String[] args)
    {
        // test1();
        // methodsInAlphabeticalOrderTest();
        // methodExecutionTest();
        testClassTest();
    }

    public static void test1()
    {
        Unit.testClass("p3TestClass1");
    }

    // public static void methodsInAlphabeticalOrderTest()
    // {
    //     try
    //     {
    //         Class<?> instanceOfGivenClass = Class.forName("p3TestClass2");
    //         Method[] methods = instanceOfGivenClass.getMethods();
    //         Method[] methodsInSortedOrder =  Unit.sortMethodsInAlphabeticalOrder(methods, instanceOfGivenClass);
    //         // for (Method m : methodsInSortedOrder)
    //         // {
    //         //     System.out.println(m.toString());
    //         // }
    //     }
    //     catch (Exception e)
    //     {
    //         System.out.println("Unexpected: " + e.toString());
    //     }
    // }

    // public static void methodExecutionTest()
    // {
    //     try
    //     {
    //         Class<?> instanceOfGivenClass = Class.forName("p3TestClass2");
    //         Constructor<?> constructorOfGivenClass = instanceOfGivenClass.getConstructor();
    //         Object givenClassInstance = constructorOfGivenClass.newInstance();

    //         Method[] methods = instanceOfGivenClass.getMethods();
    //         Method[] methodsInSortedOrder =  Unit.sortMethodsInAlphabeticalOrder(methods, instanceOfGivenClass);
    //         Map<String, Throwable> testCaseAndErrorKVP = new HashMap<>();

    //         Unit.handleMethodExecution(methodsInSortedOrder, testCaseAndErrorKVP, givenClassInstance);
    //         // assert testCaseAndErrorKVP.get(testCaseAndErrorKVP)

    //     }
    //     catch (Exception e)
    //     {
    //         System.out.println("Unexpected: " + e.toString());
    //     }
    // }

    //TODO: might need to ensure that functions gets executed in order
    public static void testClassTest()
    {
        Map<String, Throwable> testCaseAndErrorKVP = Unit.testClass("p3TestClass2");
        for (String key : testCaseAndErrorKVP.keySet())
        {
            System.out.println("Key: " + key);
            System.out.println(testCaseAndErrorKVP.get(key));
        }

        //System.out.println("size: " + testCaseAndErrorKVP.size());
    }
}

