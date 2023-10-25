import java.lang.reflect.Method;

public class Testing
{
    public static void main(String[] args)
    {
        test1();
        methodsInAlphabeticalOrderTest();
    }

    public static void test1()
    {
        Unit.testClass("myTestClass1");
    }

    public static void methodsInAlphabeticalOrderTest()
    {
        try
        {

            Class<?> instanceOfGivenClass = Class.forName("myTestClass2");
            Method[] methods = instanceOfGivenClass.getMethods();
            Method[] methodsInSortedOrder =  Unit.sortMethodsInAlphabeticalOrder(methods, instanceOfGivenClass);
            for (Method m : methodsInSortedOrder)
            {
                System.out.println(m.toString());
            }
        }
        catch (Exception e)
        {
            System.out.println("Unexpected: " + e.toString());
        }
    }
}

