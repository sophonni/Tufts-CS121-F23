import java.util.*;

public class Testing
{
    public static void main(String[] args)
    {
        //testClassTest();
        assertionObjectProvidedTest();
        assertionStringProvidedTest();
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

    public static void assertionStringProvidedTest()
    {
        String s1 = "panda";
        String s2 = "hippa";
        String s5 = "parrot";
        String s8 = "parrot";

        String s3 = "pan - ";
        String s4 = "";
        String s7 = "";
        String s6 = "a";
        
        Assertion.assertThat(s1).isNotNull();
        Assertion.assertThat(s1).contains(s4);
        Assertion.assertThat(s1).contains(s7);

        try
        {
            Assertion.assertThat(s1).startsWith(s5);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }

        try
        {
            Assertion.assertThat(s1).isEqualTo(s2);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }

        Assertion.assertThat(s5).isEqualTo(s8);

        Assertion.assertThat(s1).contains(s6).startsWith("pa").contains(s4);
    }
}

