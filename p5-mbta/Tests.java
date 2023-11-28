import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Tests {
  @Test public void testPass() {
    assertTrue("true should be true", true);
  }

  // @Test public void makePassengerTest()
  // {
  //   Passenger p1 = Passenger.make("John");
  //   Passenger p2 = Passenger.make("Sam");
  //   Passenger p3 = Passenger.make("Jake");
  //   Passenger p6 = Passenger.make("JAke");
  //   Passenger p7 = Passenger.make("Jake");
  //   System.out.println("P3: " + p3);
  //   System.out.println("P7: " + p7);
  //   Passenger.PrintMapHelper();
  //   try
  //   {
  //     Passenger p4 = Passenger.make("");
  //     Passenger p5 = Passenger.make(null);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void mbtaAddLineTest()
  // {
  //   MBTA mbta = new MBTA();
  //   List<String> redStations = new LinkedList<>();
  //   redStations.add("race");
  //   redStations.add("rain");
  //   redStations.add("roll");
  //   redStations.add("rig");
  //   redStations.add("rack");
  //   redStations.add("rust");
  //   mbta.addLine("redLine", redStations);

  //   List<String> greenStations = new LinkedList<>();
  //   greenStations.add("girl");
  //   greenStations.add("gown");
  //   greenStations.add("grow");
  //   greenStations.add("gate");
  //   greenStations.add("gun");
  //   greenStations.add("great");
  //   mbta.addLine("greenLine", greenStations);

  //   List<String> silverStations = new LinkedList<>();
  //   silverStations.add("sand");
  //   silverStations.add("sock");
  //   silverStations.add("sharp");
  //   silverStations.add("sing");
  //   silverStations.add("scare");
  //   silverStations.add("scone");
  //   mbta.addLine("silverLine", silverStations);

  //   /* duplicate stations for a line test */
  //   try
  //   {
  //     List<String> orangeStations = new LinkedList<>();
  //     orangeStations.add("own");
  //     orangeStations.add("old");
  //     orangeStations.add("oat");
  //     orangeStations.add("oak");
  //     orangeStations.add("OP");
  //     orangeStations.add("OP");
  //     mbta.addLine("orangeLine", orangeStations);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void mbtaAddJourneyTest()
  // {
  //   MBTA mbta = new MBTA();
  //   List<String> johnJourney1 = new LinkedList<>();
  //   johnJourney1.add("race");
  //   johnJourney1.add("rain");
  //   johnJourney1.add("roll");
  //   johnJourney1.add("rig");
  //   johnJourney1.add("rack");
  //   johnJourney1.add("rust");
  //   mbta.addJourney("John", johnJourney1);

  //   /* duplicate person test */
  //   try
  //   {
  //     List<String> johnJourney2 = new LinkedList<>();
  //     johnJourney2.add("race");
  //     johnJourney2.add("rain");
  //     johnJourney2.add("roll");
  //     johnJourney2.add("rig");
  //     johnJourney2.add("rack");
  //     johnJourney2.add("rust");
  //     mbta.addJourney("John", johnJourney2);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  @Test public void mbtaLoadJSONConfig()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");

    mbta.PrintLines();
    mbta.PrintJournies();
  }
}
