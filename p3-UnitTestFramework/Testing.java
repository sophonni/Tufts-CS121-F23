import java.lang.reflect.Array;
import java.util.*;

public class Testing
{
    public static void main(String[] args)
    {
        //test1();
        // testClassTest();
        // assertionObjectProvidedTest();
        // assertionStringProvidedTest();
        // assertionBoolProvidedTest();
        // assertionIntProvidedTest();
        quickCheckTest();
    }

    public static void test1()
    {
        Map<String, Throwable> testCaseAndErrorKVP = Unit.testClass("p3TestClass1");
        for (String key : testCaseAndErrorKVP.keySet())
        {
            System.out.println("Key: " + key + "--> " + testCaseAndErrorKVP.get(key));
        }
    }

    //TODO: might need to ensure that functions gets executed in order
    public static void testClassTest()
    {
        Map<String, Throwable> testCaseAndErrorKVP = Unit.testClass("p3TestClass2");
        for (String key : testCaseAndErrorKVP.keySet())
        {
            System.out.println("Key: " + key + "--> " + testCaseAndErrorKVP.get(key));
        }
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

    public static void assertionBoolProvidedTest()
    {
        boolean b1 = true;
        boolean b2 = false;

        Assertion.assertThat(b1).isEqualTo(true);

        try
        {
            Assertion.assertThat(b1).isEqualTo(false);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }

        try
        {
            Assertion.assertThat(b1).isFalse();
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }
        Assertion.assertThat(b2).isFalse();
    }

    public static void assertionIntProvidedTest()
    {
        Assertion.assertThat(9).isEqualTo(9);
        try
        {
            Assertion.assertThat(9).isEqualTo(-9);
            
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }
        
        Assertion.assertThat(0).isEqualTo(-0);
        Assertion.assertThat(9).isEqualTo(Math.abs(-9));
        
        try
        {
            Assertion.assertThat(9).isLessThan(-9);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }

        try
        {
            Assertion.assertThat(9).isLessThan(9);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }

        try
        {
            Assertion.assertThat(9).isGreaterThan(9);
        }
        catch (Exception e)
        {
            System.out.println("Expected: " + e.toString());
        }
    }

    public static void quickCheckTest()
    {
        Map<String, Object[]> propertyToFailArgListKVP = Unit.quickCheckClass("p3TestClassIntArg");
        for (String key : propertyToFailArgListKVP.keySet())
        {
            //List<Object> onePossListToPassIn = new ArrayList<>(Arrays.asList(propertyToFailArgListKVP.get(key)));
            //System.out.println(onePossListToPassIn);
            // for (Object o : failParams)
            // {
            //     System.out.println("Function {" + key + "} fails with {" + o + "}");
            // }
        }
    }
}

