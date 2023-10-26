import java.util.*;

public class Testing
{
    public static void main(String[] args)
    {
        //testClassTest();
        assertionObjectProvidedTest();
    }

    public static void test1()
    {
        Unit.testClass("p3TestClass1");
    }

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

    public static void assertionObjectProvidedTest()
    {
        Unit u = new Unit();
        Unit u2 = u;
        Assertion.assertThat(u).isNotNull().isInstanceOf(Unit.class).isInstanceOf(Unit.class).isEqualTo(u2);
    }
}

