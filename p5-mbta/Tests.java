import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Tests {
  @Test public void testPass() {
    assertTrue("true should be true", true);
  }

  @Test public void makePassengerTest()
  {
    Passenger p1 = Passenger.make("John");
    Passenger p2 = Passenger.make("Sam");
    Passenger p3 = Passenger.make("Jake");
    Passenger p6 = Passenger.make("JAke");
    Passenger p7 = Passenger.make("Jake");
    System.out.println("P3: " + p3);
    System.out.println("P7: " + p7);
    Passenger.PrintMapHelper();
    try
    {
      Passenger p4 = Passenger.make("");
      Passenger p5 = Passenger.make(null);
    }
    catch (Exception e)
    {
      System.out.println("Expected Exception: " + e);
    }
  }
}
