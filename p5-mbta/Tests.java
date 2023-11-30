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

  // @Test public void mbtaLoadJSONConfig()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");

  //   mbta.PrintLines();
  //   mbta.PrintJournies();
  // }

  // @Test public void moveRedTrainForwardAndBackTest()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");

  //   Train t = Train.make("red");
  //   Station s1 = Station.make("Davis");
  //   Station s2 = Station.make("Harvard");
  //   Station s3 = Station.make("Kendall");
  //   Station s4 = Station.make("Park");
  //   Station s5 = Station.make("Downtown Crossing");
  //   Station s6 = Station.make("South Station");
  //   Station s7 = Station.make("Broadway");
  //   Station s8 = Station.make("Andrew");
  //   Station s9 = Station.make("JFK");

  //   /* moving forward */
  //   MoveEvent moveEvent1 = new MoveEvent(t, s1, s2);
  //   MoveEvent moveEvent2 = new MoveEvent(t, s2, s3);
  //   MoveEvent moveEvent3 = new MoveEvent(t, s3, s4);
  //   MoveEvent moveEvent4 = new MoveEvent(t, s4, s5);
  //   MoveEvent moveEvent5 = new MoveEvent(t, s5, s6);
  //   MoveEvent moveEvent6 = new MoveEvent(t, s6, s7);

  //   moveEvent1.replayAndCheck(mbta);
  //   moveEvent2.replayAndCheck(mbta);
  //   moveEvent3.replayAndCheck(mbta);
  //   moveEvent4.replayAndCheck(mbta);
  //   moveEvent5.replayAndCheck(mbta);
  //   moveEvent6.replayAndCheck(mbta);
    
  //   /* move train backward while forward moving is not yet complete test*/
  //   // try
  //   // {
  //   //   MoveEvent moveEvent9 = new MoveEvent(t, s7, s6);
  //   //   moveEvent9.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("EXPECTED EXCEPTION: " + e);
  //   // }
  //   MoveEvent moveEvent7 = new MoveEvent(t, s7, s8);
  //   MoveEvent moveEvent8 = new MoveEvent(t, s8, s9);
  //   moveEvent7.replayAndCheck(mbta);
  //   moveEvent8.replayAndCheck(mbta);
    
  //   /* moving backward */
  //   MoveEvent moveEvent10 = new MoveEvent(t, s9, s8);
  //   moveEvent10.replayAndCheck(mbta);

  //   MoveEvent moveEvent11 = new MoveEvent(t, s8, s7);
  //   moveEvent11.replayAndCheck(mbta);

  //   MoveEvent moveEvent12 = new MoveEvent(t, s7, s6);
  //   moveEvent12.replayAndCheck(mbta);

  //   MoveEvent moveEvent13 = new MoveEvent(t, s6, s5);
  //   moveEvent13.replayAndCheck(mbta);

  //   MoveEvent moveEvent14 = new MoveEvent(t, s5, s4);
  //   moveEvent14.replayAndCheck(mbta);

  //   MoveEvent moveEvent15 = new MoveEvent(t, s4, s3);
  //   moveEvent15.replayAndCheck(mbta);

  //   MoveEvent moveEvent16 = new MoveEvent(t, s3, s2);
  //   moveEvent16.replayAndCheck(mbta);

  //   MoveEvent moveEvent17 = new MoveEvent(t, s2, s1);
  //   moveEvent17.replayAndCheck(mbta);
    
  //   /* moving forward */
  //   MoveEvent moveEvent18 = new MoveEvent(t, s1, s2);
  //   moveEvent18.replayAndCheck(mbta);
    
  //   System.out.println("Lines Before: " + mbta.trainAndStationsKVP);
  //   MoveEvent moveEvent19 = new MoveEvent(t, s2, s3);
  //   moveEvent19.replayAndCheck(mbta);
  //   System.out.println("Lines After: " + mbta.trainAndStationsKVP);
  //   /* unidentified station name test */
  //   // Station s4 = Station.make("Turkey");
  //   // try
  //   // {
  //   //   MoveEvent moveEvent3 = new MoveEvent(t, s3, s4);
  //   //   moveEvent3.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }    
  // }

  // @Test public void passengerBoardingTest()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");

  //   Train t = Train.make("red");
  //   Station s1 = Station.make("Davis");
  //   Station s2 = Station.make("Harvard");
  //   Station s3 = Station.make("Kendall");
  //   Station s4 = Station.make("Park");
  //   Station s5 = Station.make("Downtown Crossing");
  //   Station s6 = Station.make("South Station");
  //   Station s7 = Station.make("Broadway");
  //   Station s8 = Station.make("Andrew");
  //   Station s9 = Station.make("JFK");

  //   Passenger p1 = Passenger.make("John");
  //   BoardEvent be1 = new BoardEvent(p1, t, s1);
  //   be1.replayAndCheck(mbta);
      
  //   /* non-unique passenger test */
  //   MoveEvent moveEvent1 = new MoveEvent(t, s1, s2);
  //   moveEvent1.replayAndCheck(mbta);
  //   Passenger p2 = Passenger.make("Jake");
  //   BoardEvent be2 = new BoardEvent(p2, t, s2);
  //   be2.replayAndCheck(mbta);

  //   /* same passenger board from station differ from current station test */
  //   // try
  //   // {
  //   //   Passenger p3 = Passenger.make("John");
  //   //   BoardEvent be3 = new BoardEvent(p3, t, s1);
  //   //   be3.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }
    

  //   /* same passenger boarding the train twice test */
  //   // try
  //   // {
  //   //   BoardEvent be4 = new BoardEvent(p1, t, s1);
  //   //   be2.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }
  //   //System.out.println("Boarded People: " + mbta.trainToBoardedPassengers);
  // }

  // @Test public void passengerDeBoardingTest()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");

  //   Train t = Train.make("red");
  //   Station s1 = Station.make("Davis");
  //   Station s2 = Station.make("Harvard");
  //   Station s3 = Station.make("Kendall");
  //   Station s4 = Station.make("Park");
  //   Station s5 = Station.make("Downtown Crossing");
  //   Station s6 = Station.make("South Station");
  //   Station s7 = Station.make("Broadway");
  //   Station s8 = Station.make("Andrew");
  //   Station s9 = Station.make("JFK");

  //   Passenger p1 = Passenger.make("John");
  //   BoardEvent be1 = new BoardEvent(p1, t, s1);
  //   be1.replayAndCheck(mbta);
      
  //   /* non-unique passenger test */
  //   MoveEvent moveEvent1 = new MoveEvent(t, s1, s2);
  //   moveEvent1.replayAndCheck(mbta);
  //   Passenger p2 = Passenger.make("Jake");
  //   BoardEvent be2 = new BoardEvent(p2, t, s2);
  //   be2.replayAndCheck(mbta);

  //   Passenger p3 = Passenger.make("Jill");
  //   BoardEvent be3 = new BoardEvent(p3, t, s2);
  //   be3.replayAndCheck(mbta);

  //   // try
  //   // {
  //   //   be3.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   MoveEvent moveEvent2 = new MoveEvent(t, s2, s3);
  //   moveEvent2.replayAndCheck(mbta);
  //   // System.out.println("Passengers Before Deboard: " + mbta.trainToBoardedPassengers);
  //   DeboardEvent dbe3 = new DeboardEvent(p3, t, s3);
  //   dbe3.replayAndCheck(mbta);
  //   // System.out.println("Passengers After Deboard: " + mbta.trainToBoardedPassengers);
  //   // System.out.println("Forward: " + mbta.trainForwardStations);
  // }

  //////////////////////OTHER PEOPLE TEST CASES////////////////////////////////
  @Test public void others1() {
    MBTA mbta = new MBTA();
  
    mbta.addLine("red", List.of("Braintree", "Alewife"));
    mbta.addLine("green", List.of("Tufts", "Government Center"));
  
    Train rTrain = Train.make("red");
    Train gTrain = Train.make("green");
  
    Station braintree = Station.make("Braintree");
    Station alewife = Station.make("Alewife");
    Station tufts = Station.make("Tufts");
    Station govCenter = Station.make("Government Center");
  
    Log log = new Log(List.of(
            new MoveEvent(rTrain, braintree, alewife),
            new MoveEvent(gTrain, tufts, govCenter)
    ));
  
    Verify.verify(mbta, log);
  }

  @Test public void others2() {
    MBTA sim = new MBTA();
    Train red = Train.make("red");
    Station stop1 = Station.make("stop1");
    Station stop2 = Station.make("stop2");
    Station stop3 = Station.make("stop3");
    Passenger p1 = Passenger.make("p1");

    sim.addLine("red", List.of("stop1", "stop2", "stop3"));
    sim.addJourney("p1", List.of("stop1", "stop5"));

    List<Event> events = new LinkedList<>();
    events.add(new BoardEvent(p1, red, stop1));
    events.add(new MoveEvent(red, stop1, stop2));
    events.add(new MoveEvent(red, stop2, stop3));
    events.add(new DeboardEvent(p1, red, stop3));

    Log log = new Log(events);
    Verify.verify(sim, log);
  }

  @Test public void others3() {
    MBTA test_mbta = new MBTA();
    Log log= new Log();
    Train red_line= Train.make("Red");
    Passenger test_passenger = Passenger.make("test");
    Station station_1 = Station.make("Davis");
    Station station_2 =Station.make("Harvard");
    test_mbta.addLine("test", List.of("Davis", "Harvard"));
    test_mbta.addJourney("test", List.of("Davis", "Harvard"));
  }
  @Test public void testInvalidBoarding() {
    // Setup
    MBTA mbta = new MBTA();
    Station stationA = Station.make("StationA");
    Station stationB = Station.make("StationB");
    Train trainA = Train.make("TrainA");
    Passenger passengerX = Passenger.make("PassengerX");

    mbta.addLine("LineA", List.of("StationA", "StationB"));
    mbta.addJourney("PassengerX", List.of("StationA", "StationB", "StationA"));

    Log log = new Log(List.of(
            new BoardEvent(passengerX, trainA, stationA),
            new MoveEvent(trainA, stationA, stationB),
            new DeboardEvent(passengerX, trainA, stationB),
            new MoveEvent(trainA, stationB, stationA),
            new BoardEvent(passengerX, trainA, stationB)  // This should fail
    ));

    Verify.verify(mbta, log);
  }



















  // @Test public void loadConfigAndBoardAndDeboardingPassengerTest() {
  //   Passenger John = Passenger.make("John");
      
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");

  //   Train t = Train.make("red");
  //   Station s1 = Station.make("Davis");
  //   Station s2 = Station.make("Harvard");
  //   Station s3 = Station.make("Kendall");
  //   Station s4 = Station.make("Park");
  //   Station s5 = Station.make("Downtown Crossing");
  //   Station s6 = Station.make("South Station");
  //   Station s7 = Station.make("Broadway");
  //   Station s8 = Station.make("Andrew");
  //   Station s9 = Station.make("JFK");

  //   MoveEvent moveEvent1 = new MoveEvent(t, s1, s2);
  //   MoveEvent moveEvent2 = new MoveEvent(t, s2, s3);
  //   MoveEvent moveEvent3 = new MoveEvent(t, s3, s4);
  //   MoveEvent moveEvent4 = new MoveEvent(t, s4, s5);
  //   MoveEvent moveEvent5 = new MoveEvent(t, s5, s6);
  //   MoveEvent moveEvent6 = new MoveEvent(t, s6, s7);

  //   moveEvent1.replayAndCheck(mbta);
  //   moveEvent2.replayAndCheck(mbta);
  //   moveEvent3.replayAndCheck(mbta);
  //   moveEvent4.replayAndCheck(mbta);
  //   moveEvent5.replayAndCheck(mbta);
  //   moveEvent6.replayAndCheck(mbta);

  //   Log l = new Log(List.of(  new BoardEvent(John, t, s1),
  //                             new MoveEvent(t, s1, s2),
  //                             new MoveEvent(t, s2, s3),
  //                             new DeboardEvent(John, t, s3)
  //                           ));
  //   Verify.verify(mbta, l);
  // }
}
