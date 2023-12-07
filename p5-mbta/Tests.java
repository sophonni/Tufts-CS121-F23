import java.io.PrintWriter;
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

  // @Test public void passengerDeBoardingFromWrongTrainTest()
  // {
  //   MBTA mbta = new MBTA();

  //   mbta.addLine("a", List.of("A", "B"));
  //   mbta.addLine("d", List.of("J", "K"));
  //   Passenger bob = Passenger.make("Bob");

  //   mbta.addJourney("Bob", List.of("J", "K"));

  //   BoardEvent be1 = new BoardEvent(bob, Train.make("d"), Station.make("J"));
  //   be1.replayAndCheck(mbta);
    
  //   MoveEvent me1 = new MoveEvent(Train.make("a"), Station.make("A"), Station.make("B"));
  //   me1.replayAndCheck(mbta);
    
  //   MoveEvent me2 = new MoveEvent(Train.make("d"), Station.make("J"), Station.make("K"));
  //   me2.replayAndCheck(mbta);

  //   DeboardEvent db1 = new DeboardEvent(bob, Train.make("a"), Station.make("B"));
  //   db1.replayAndCheck(mbta);
  // }

  // @Test public void passengerDeBoardingAtUnassignedStationTest()
  // {
  //   MBTA mbta = new MBTA();

  //   mbta.addLine("a", List.of("A", "B"));
  //   mbta.addLine("d", List.of("J", "K", "L", "M"));
  //   Passenger bob = Passenger.make("Bob");

  //   mbta.addJourney("Bob", List.of("J", "M"));

  //   BoardEvent be1 = new BoardEvent(bob, Train.make("d"), Station.make("J"));
  //   be1.replayAndCheck(mbta);
    
  //   MoveEvent me1 = new MoveEvent(Train.make("a"), Station.make("A"), Station.make("B"));
  //   me1.replayAndCheck(mbta);
    
  //   MoveEvent me2 = new MoveEvent(Train.make("d"), Station.make("J"), Station.make("K"));
  //   me2.replayAndCheck(mbta);

  //   try
  //   {
  //     DeboardEvent db1 = new DeboardEvent(bob, Train.make("d"), Station.make("K"));
  //     db1.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void passengerBoardingAtUnassignedStationTest()
  // {
  //   MBTA mbta = new MBTA();

  //   mbta.addLine("a", List.of("A", "B"));
  //   mbta.addLine("d", List.of("J", "K", "L", "M"));
  //   Passenger bob = Passenger.make("Bob");

  //   mbta.addJourney("Bob", List.of("K", "M"));

  //   try
  //   {
  //     BoardEvent be1 = new BoardEvent(bob, Train.make("d"), Station.make("J"));
  //     be1.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
    
  //   MoveEvent me1 = new MoveEvent(Train.make("a"), Station.make("A"), Station.make("B"));
  //   me1.replayAndCheck(mbta);
    
  //   MoveEvent me2 = new MoveEvent(Train.make("d"), Station.make("J"), Station.make("K"));
  //   me2.replayAndCheck(mbta);
  // }

  // @Test public void passengerBoardingAndDeboardAtPrevStationTest()
  // {
  //   MBTA mbta = new MBTA();

  //   mbta.addLine("d", List.of("J", "K", "L", "M", "N"));
  //   Passenger bob = Passenger.make("Bob");

  //   mbta.addJourney("Bob", List.of("L", "J"));
    
  //   MoveEvent me1 = new MoveEvent(Train.make("d"), Station.make("J"), Station.make("K"));
  //   me1.replayAndCheck(mbta);

  //   MoveEvent me2 = new MoveEvent(Train.make("d"), Station.make("K"), Station.make("L"));
  //   me2.replayAndCheck(mbta);

  //   BoardEvent be1 = new BoardEvent(bob, Train.make("d"), Station.make("L"));
  //   be1.replayAndCheck(mbta);

  //   MoveEvent me3 = new MoveEvent(Train.make("d"), Station.make("L"), Station.make("M"));
  //   me3.replayAndCheck(mbta);

  //   /* deboard at wrong station test */
  //   // try
  //   // {
  //   //   DeboardEvent db1 = new DeboardEvent(bob, Train.make("d"), Station.make("M"));
  //   //   db1.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   /* try moving trian backward before reaching the end test */
  //   // try
  //   // {
  //   //   MoveEvent me4 = new MoveEvent(Train.make("d"), Station.make("M"), Station.make("L"));
  //   //   me4.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   MoveEvent me5 = new MoveEvent(Train.make("d"), Station.make("M"), Station.make("N"));
  //   me5.replayAndCheck(mbta);

  //   MoveEvent me6 = new MoveEvent(Train.make("d"), Station.make("N"), Station.make("M"));
  //   me6.replayAndCheck(mbta);

  //   /* deboard at wrong station test */
  //   // try
  //   // {
  //   //   DeboardEvent db2 = new DeboardEvent(bob, Train.make("d"), Station.make("M"));
  //   //   db2.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   // try
  //   // {
  //   //   DeboardEvent db3 = new DeboardEvent(bob, Train.make("d"), Station.make("M"));
  //   //   db3.replayAndCheck(mbta);
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   MoveEvent me7 = new MoveEvent(Train.make("d"), Station.make("M"), Station.make("L"));
  //   me7.replayAndCheck(mbta);

  //   MoveEvent me8 = new MoveEvent(Train.make("d"), Station.make("L"), Station.make("K"));
  //   me8.replayAndCheck(mbta);

  //   MoveEvent me9 = new MoveEvent(Train.make("d"), Station.make("K"), Station.make("J"));
  //   me9.replayAndCheck(mbta);

  //   DeboardEvent db4 = new DeboardEvent(bob, Train.make("d"), Station.make("J"));
  //   db4.replayAndCheck(mbta);
  // }

  // @Test public void checkStartCheckEndTest()
  // {
  //  MBTA mbta = new MBTA();
  //  mbta.loadConfig("sample.json");

  //   Train red = Train.make("red");
  //   Train orange = Train.make("orange");
  //   Train green = Train.make("green");
  //   Train blue = Train.make("blue");

  //   Station s1 = Station.make("Davis");
  //   Station s2 = Station.make("Harvard");
  //   Station s3 = Station.make("Kendall");
  //   Station s4 = Station.make("Park");
  //   Station s5 = Station.make("Downtown Crossing");
  //   Station s6 = Station.make("South Station");
  //   Station s7 = Station.make("Broadway");
  //   Station s8 = Station.make("Andrew");
  //   Station s9 = Station.make("JFK");

  //   Passenger Bob = Passenger.make("Bob");
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Carol = Passenger.make("Carol");
  //   Passenger John = Passenger.make("John");

  //   mbta.addJourney("John", List.of(s1.toString(), s2.toString(), s3.toString()));
  //   BoardEvent be1 = new BoardEvent(John, red, s1);
  //   be1.replayAndCheck(mbta);

  //   MoveEvent me1 = new MoveEvent(red, s1, s2);
  //   me1.replayAndCheck(mbta);

  //   DeboardEvent db1 = new DeboardEvent(John, red, s2);
  //   db1.replayAndCheck(mbta);

  //   // System.out.println("Boarded People: " + mbta.trainToBoardedPassengers);

  //   try
  //   {
  //     mbta.checkEnd();
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }

  //   MoveEvent me2 = new MoveEvent(red, s2, s3);
  //   me2.replayAndCheck(mbta);

  //   DeboardEvent db2 = new DeboardEvent(John, red, s3);
  //   db2.replayAndCheck(mbta);

  //   MoveEvent me3 = new MoveEvent(red, s3, s4);
  //   me3.replayAndCheck(mbta);

  //   /* train move to next station after train is empty test */
  //   //SD TODO: come back and fix why this section doesn't through exception --> checkEnd is not working properly
  //   try
  //   {
  //     mbta.checkEnd();
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void checkEndValidTest()
  // {

  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Station C = Station.make("C");

  //   Train d = Train.make("d");
  //   Station J = Station.make("J");
  //   Station K = Station.make("K");
  //   Station L = Station.make("L");

  //   mbta.addLine("a", List.of("A", "B", "C"));
  //   mbta.addLine("d", List.of("J", "K", "L"));

  //   mbta.addJourney("Alice", List.of("A", "B"));
  //   mbta.addJourney("Bob", List.of("J", "L"));
  //   mbta.checkStart();

  //   //Passenger Alice boards a at A
  //   BoardEvent AliceBe = new BoardEvent(Alice, a, A);
  //   AliceBe.replayAndCheck(mbta);

  //   //Passenger Bob boards d at J
  //   BoardEvent BobBe = new BoardEvent(Bob, d, J);
  //   BobBe.replayAndCheck(mbta);

  //   //Train a moves from A to B
  //   MoveEvent me1 = new MoveEvent(a, A, B);
  //   me1.replayAndCheck(mbta);

  //   //Passenger Alice deboards a at B
  //   DeboardEvent AliceDe = new DeboardEvent(Alice, a, B);
  //   AliceDe.replayAndCheck(mbta);

  //   //Train a moves from B to C
  //   MoveEvent me2 = new MoveEvent(a, B, C);
  //   me2.replayAndCheck(mbta);

  //   //Train d moves from J to K
  //   MoveEvent me3 = new MoveEvent(d, J, K);
  //   me3.replayAndCheck(mbta);

  //   //Train d moves from K to L
  //   MoveEvent me4 = new MoveEvent(d, K, L);
  //   me4.replayAndCheck(mbta);

  //   // //Passenger Bob deboards d at L
  //   DeboardEvent BobDe = new DeboardEvent(Bob, d, L);
  //   BobDe.replayAndCheck(mbta);
  //   mbta.checkEnd();
    

  //   System.out.println("Board Passenger: " + mbta.trainToBoardedPassengers);

  //   System.out.println("Lines Test: " + mbta.trainAndStationsKVP);
  //   System.out.println("Jouney: " + mbta.passengerAndStationsKVP);
  // }

  // @Test public void checkEndInvalidTest()
  // {
  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Station C = Station.make("C");

  //   Train d = Train.make("d");
  //   Station J = Station.make("J");
  //   Station K = Station.make("K");
  //   Station L = Station.make("L");
  //   mbta.addLine("a", List.of("A", "B", "C"));
  //   mbta.addLine("d", List.of("J", "K", "L"));

  //   mbta.addJourney("Alice", List.of("A", "B"));
  //   mbta.checkStart();

  //   //Passenger Alice boards a at A
  //   BoardEvent AliceBe = new BoardEvent(Alice, a, A);
  //   AliceBe.replayAndCheck(mbta);

  //   //Train a moves from A to B
  //   MoveEvent me1 = new MoveEvent(a, A, B);
  //   me1.replayAndCheck(mbta);

  //   //Passenger Alice deboards a at B
  //   DeboardEvent AliceDe = new DeboardEvent(Alice, a, B);
  //   AliceDe.replayAndCheck(mbta);

  //   //Train a moves from B to C
  //   MoveEvent me2 = new MoveEvent(a, B, C);
  //   me2.replayAndCheck(mbta);

  //   //Train d moves from J to K
  //   MoveEvent me3 = new MoveEvent(d, J, K);
  //   me3.replayAndCheck(mbta);

  //   //Train d moves from K to L
  //   MoveEvent me4 = new MoveEvent(d, K, L);
  //   me4.replayAndCheck(mbta);
  //   System.out.println("Board Passenger: " + mbta.trainToBoardedPassengers);

  /* train moved and end at a station that is different from the initial station when there's no passenger test */
  //   try
  //   {
  //     mbta.checkEnd();
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void checkEndInvalidTest2()
  // {
  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Station C = Station.make("C");

  //   Train d = Train.make("d");
  //   Station J = Station.make("J");
  //   Station K = Station.make("K");
  //   Station L = Station.make("L");

  //   mbta.addLine("a", List.of("A", "B", "C"));
  //   mbta.addLine("d", List.of("J", "K", "L"));

  //   mbta.addJourney("Alice", List.of("A", "B"));
  //   mbta.addJourney("Bob", List.of("J", "L"));
  //   mbta.checkStart();

  //   //Passenger Alice boards a at A
  //   BoardEvent AliceBe = new BoardEvent(Alice, a, A);
  //   AliceBe.replayAndCheck(mbta);

  //   //Passenger Bob boards d at J
  //   BoardEvent BobBe = new BoardEvent(Bob, d, J);
  //   BobBe.replayAndCheck(mbta);

  //   //Train a moves from A to B
  //   MoveEvent me1 = new MoveEvent(a, A, B);
  //   me1.replayAndCheck(mbta);

  //   //Passenger Alice deboards a at B
  //   DeboardEvent AliceDe = new DeboardEvent(Alice, a, B);
  //   AliceDe.replayAndCheck(mbta);

  //   //Train a moves from B to C
  //   MoveEvent me2 = new MoveEvent(a, B, C);
  //   me2.replayAndCheck(mbta);

  //   //Train d moves from J to K
  //   MoveEvent me3 = new MoveEvent(d, J, K);
  //   me3.replayAndCheck(mbta);

  //   //Train d moves from K to L
  //   MoveEvent me4 = new MoveEvent(d, K, L);
  //   me4.replayAndCheck(mbta);

  //   /* checkEnd before all boarded passenger get off the train */
  //   try
  //   {
  //     mbta.checkEnd();
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);

  //   }
  // }

  // @Test public void checkStartAfterLoadConfig()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");
  //   mbta.checkStart();
  // }


  // @Test public void trainAtSameStationTest()
  // {
  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Station C = Station.make("C");

  //   Train d = Train.make("d");
  //   Station J = Station.make("J");
  //   Station B2 = Station.make("B");
  //   Station L = Station.make("L");

  //   mbta.addLine("a", List.of("A", "B", "C"));
  //   mbta.addLine("d", List.of("J", "B", "L"));

  //   mbta.addJourney("Alice", List.of("A", "B"));
  //   mbta.addJourney("Bob", List.of("J", "L"));
  //   mbta.checkStart();

  //   MoveEvent me1 = new MoveEvent(a, A, B);
  //   me1.replayAndCheck(mbta);

  //   /* train can't move to a station that is occupied by another train test */
  //   try
  //   {
  //     MoveEvent me2 = new MoveEvent(d, J, B2);
  //     me2.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void moveTrainTest()
  // {
  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Station C = Station.make("C");
  //   mbta.addLine("a", List.of("A", "B", "C"));

  //   /* start moving from station that is not the current station of the train test */
  //   try
  //   {
  //     MoveEvent me1 = new MoveEvent(a, B, C);
  //     me1.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void moveTrainTest2()
  // {
  //   MBTA mbta = new MBTA();
  //   Passenger Alice = Passenger.make("Alice");
  //   Passenger Bob = Passenger.make("Bob");

  //   Train a = Train.make("a");
  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   mbta.addLine("a", List.of("A", "B"));

  //   /* start moving from station that is not the current station of the train test */
  //   try
  //   {
  //     MoveEvent me1 = new MoveEvent(a, B, A);
  //     me1.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }

  // @Test public void moveTrainTest3()
  // {
  //   MBTA mbta = new MBTA();

  //   Station A = Station.make("A");
  //   Station B = Station.make("B");
  //   Train a = Train.make("a");
  //   mbta.addLine("a", List.of("A", "B"));
    
  //   Station C = Station.make("C");
  //   Station B1 = Station.make("B");
  //   Train d = Train.make("d");
  //   mbta.addLine("d", List.of("C", "B"));

  //   MoveEvent me1 = new MoveEvent(a, A, B);
  //   me1.replayAndCheck(mbta);

  //   try
  //   {
  //     MoveEvent me2 = new MoveEvent(d, C, B1);
  //     me2.replayAndCheck(mbta);
  //   }
  //   catch (Exception e)
  //   {
  //     System.out.println("Expected Exception: " + e);
  //   }
  // }










  @Test public void autoGraderLogInValidTest()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample2.json");

    Train green = Train.make("green");

    Station Tufts = Station.make("Tufts");
    Station EastSommerville = Station.make("East Sommerville");
    Station Lechmere = Station.make("Lechmere");
    Station NorthStation = Station.make("North Station");
    Station GovCen = Station.make("Government Center");
    Station BluePass = Station.make("BLUE_PASS");
    Station Park = Station.make("Park");
    Station Boyston = Station.make("Boylston");
    Station Arlington = Station.make("Arlington");
    Station Copley = Station.make("Copley");

    MoveEvent me1 = new MoveEvent(green, Tufts, EastSommerville);
    me1.replayAndCheck(mbta);

    MoveEvent me2 = new MoveEvent(green, EastSommerville, Lechmere);
    me2.replayAndCheck(mbta);

    MoveEvent me3 = new MoveEvent(green, Lechmere, NorthStation);
    me3.replayAndCheck(mbta);

    MoveEvent me4 = new MoveEvent(green, NorthStation, GovCen);
    me4.replayAndCheck(mbta);

    MoveEvent me5 = new MoveEvent(green, GovCen, BluePass);
    me5.replayAndCheck(mbta);

    MoveEvent me6 = new MoveEvent(green, BluePass, Park);
    me6.replayAndCheck(mbta);

    System.out.println("Line: " + mbta.trainAndStationsKVP);
  }

  @Test public void autoGraderLogValidTestMoveTrainBackAndForth()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample2.json");

    Train blue = Train.make("blue");

    Station Bowdoin = Station.make("Bowdoin");
    Station GovCen = Station.make("Government Center");
    Station OrangePass = Station.make("ORANGE_PASS");
    Station State = Station.make("State");
    Station Aquarium = Station.make("Aquarium");
    Station Maverick = Station.make("Maverick");
    Station Airport = Station.make("Airport");

    MoveEvent me1 = new MoveEvent(blue, Bowdoin, GovCen);
    me1.replayAndCheck(mbta);

    MoveEvent me2 = new MoveEvent(blue, GovCen, OrangePass);
    me2.replayAndCheck(mbta);

    MoveEvent me3 = new MoveEvent(blue, OrangePass, State);
    me3.replayAndCheck(mbta);

    MoveEvent me4 = new MoveEvent(blue, State, Aquarium);
    me4.replayAndCheck(mbta);

    MoveEvent me5 = new MoveEvent(blue, Aquarium, Maverick);
    me5.replayAndCheck(mbta);

    MoveEvent me6 = new MoveEvent(blue, Maverick, Airport);
    me6.replayAndCheck(mbta);

    System.out.println("Start Moving Backward: " + mbta.trainAndStationsKVP);
    MoveEvent me7 = new MoveEvent(blue, Airport, Maverick);
    me7.replayAndCheck(mbta);

    MoveEvent me8 = new MoveEvent(blue, Maverick, Aquarium);
    me8.replayAndCheck(mbta);

    MoveEvent me9 = new MoveEvent(blue, Aquarium, State);
    me9.replayAndCheck(mbta);

    MoveEvent me10 = new MoveEvent(blue, State, OrangePass);
    me10.replayAndCheck(mbta);

    MoveEvent me11 = new MoveEvent(blue, OrangePass, GovCen);
    me11.replayAndCheck(mbta);

    MoveEvent me12 = new MoveEvent(blue, GovCen, Bowdoin);
    me12.replayAndCheck(mbta);

    System.out.println("Start Moving Forward: " + mbta.trainAndStationsKVP);
    MoveEvent me13 = new MoveEvent(blue, Bowdoin, GovCen);
    me13.replayAndCheck(mbta);

    MoveEvent me14 = new MoveEvent(blue, GovCen, OrangePass);
    me14.replayAndCheck(mbta);

    MoveEvent me15 = new MoveEvent(blue, OrangePass, State);
    me15.replayAndCheck(mbta);

    MoveEvent me16 = new MoveEvent(blue, State, Aquarium);
    me16.replayAndCheck(mbta);

    MoveEvent me17 = new MoveEvent(blue, Aquarium, Maverick);
    me17.replayAndCheck(mbta);

    MoveEvent me18 = new MoveEvent(blue, Maverick, Airport);
    me18.replayAndCheck(mbta);
  }

  @Test public void autoGraderShareTrackIsValid()
  {
    MBTA mbta = new MBTA();

    Train red = Train.make("red");
    Station A = Station.make("A");
    Station B = Station.make("B");
    Station D = Station.make("D");
    Station G = Station.make("G");
    Station F = Station.make("F");

    Train orange = Train.make("orange");
    Station L = Station.make("L");
    Station K = Station.make("K");
    Station J = Station.make("J");
    Station I = Station.make("I");
    Station H = Station.make("H");

    Train green = Train.make("green");
    Station H1 = Station.make("H");
    Station G1 = Station.make("G");
    Station E = Station.make("E");
    Station B1 = Station.make("B");
    Station C = Station.make("C");

    Train blue = Train.make("blue");
    Station R = Station.make("R");
    Station S = Station.make("S");
    Station P = Station.make("P");
    Station N = Station.make("N");
    Station M = Station.make("M");
    Station F1 = Station.make("F");

    Train purple = Train.make("purple");
    Station O = Station.make("O");
    Station N1 = Station.make("N");
    Station Q = Station.make("Q");
    Station S1 = Station.make("S");
    Station T = Station.make("T");
    Station L1 = Station.make("L");

    mbta.addLine("green", List.of("H", "G", "E", "B", "C"));
    mbta.addLine("orange", List.of("L", "K", "J", "I", "H"));
    mbta.addLine("red", List.of("A", "B", "D", "G", "F"));
    mbta.addLine("blue", List.of("R", "S", "P", "N", "M", "F"));
    mbta.addLine("purple", List.of("O", "N", "Q", "S", "T", "L"));

    Passenger Frog = Passenger.make("Frog");
    Passenger Koala = Passenger.make("Koala");
    Passenger Elephant = Passenger.make("Elephant");
    Passenger Horse = Passenger.make("Horse");
    Passenger Jaguar = Passenger.make("Jaguar");
    Passenger Cow = Passenger.make("Cow");
    Passenger Lamprey = Passenger.make("Lamprey");
    Passenger Bear = Passenger.make("Bear");
    Passenger Dog = Passenger.make("Dog");
    Passenger Iguana = Passenger.make("Iguana");
    Passenger Aardvark = Passenger.make("Aardvark");
    Passenger Giraffe = Passenger.make("Giraffe");

    mbta.addJourney("Frog", List.of("O", "N", "F", "G", "H"));
    mbta.addJourney("Koala", List.of("L", "T"));
    mbta.addJourney("Elephant", List.of("D", "F", "N", "T"));
    mbta.addJourney("Horse", List.of("M", "N"));
    mbta.addJourney("Jaguar", List.of("H", "L"));
    mbta.addJourney("Cow", List.of("R", "S", "L", "H"));
    mbta.addJourney("Lamprey", List.of("L", "H", "G", "F", "S", "T"));
    mbta.addJourney("Bear", List.of("R", "F", "G", "H"));
    mbta.addJourney("Dog", List.of("A", "B", "G"));
    mbta.addJourney("Iguana", List.of("P", "F", "B", "C"));
    mbta.addJourney("Aardvark", List.of("R", "S", "T"));
    mbta.addJourney("Giraffe", List.of("O", "L", "H"));

    MoveEvent greenMe1 = new MoveEvent(green, H, G);
    greenMe1.replayAndCheck(mbta);
    MoveEvent greenMe2 = new MoveEvent(green, G, E);
    greenMe2.replayAndCheck(mbta);
    MoveEvent greenMe3 = new MoveEvent(green, E, B);
    greenMe3.replayAndCheck(mbta);
    MoveEvent greenMe4 = new MoveEvent(green, B, C);
    greenMe4.replayAndCheck(mbta);

    MoveEvent purpleMe1 = new MoveEvent(purple, O, N);
    purpleMe1.replayAndCheck(mbta);
    MoveEvent purpleMe2 = new MoveEvent(purple, N, Q);
    purpleMe2.replayAndCheck(mbta);
    MoveEvent purpleMe3 = new MoveEvent(purple, Q, S);
    purpleMe3.replayAndCheck(mbta);
    MoveEvent purpleMe4 = new MoveEvent(purple, S, T);
    purpleMe4.replayAndCheck(mbta);

    MoveEvent redMe1 = new MoveEvent(red, A, B);
    redMe1.replayAndCheck(mbta);
    MoveEvent redMe2 = new MoveEvent(red, B, D);
    redMe2.replayAndCheck(mbta);
    BoardEvent redBe1 = new BoardEvent(Elephant, red, D);
    redBe1.replayAndCheck(mbta);
    MoveEvent redMe3 = new MoveEvent(red, D, G);
    redMe3.replayAndCheck(mbta);
    MoveEvent redMe4 = new MoveEvent(red, G, F);
    redMe4.replayAndCheck(mbta);
    DeboardEvent redDb1 = new DeboardEvent(Elephant, red, F);
    redDb1.replayAndCheck(mbta);
    MoveEvent redMe5 = new MoveEvent(red, F, G);
    redMe5.replayAndCheck(mbta);


    MoveEvent blueMe1 = new MoveEvent(blue, R, S);
    blueMe1.replayAndCheck(mbta);
    MoveEvent blueMe2 = new MoveEvent(blue, S, P);
    blueMe2.replayAndCheck(mbta);
    MoveEvent blueMe3 = new MoveEvent(blue, P, N);
    blueMe3.replayAndCheck(mbta);
    MoveEvent blueMe4 = new MoveEvent(blue, N, M);
    blueMe4.replayAndCheck(mbta);
    MoveEvent blueMe5 = new MoveEvent(blue, M, F);
    blueMe5.replayAndCheck(mbta);
    BoardEvent blueBe1 = new BoardEvent(Elephant, blue, F);
    blueBe1.replayAndCheck(mbta);

    MoveEvent orangeMe1 = new MoveEvent(orange, L, K);
    orangeMe1.replayAndCheck(mbta);
    MoveEvent orangeMe2 = new MoveEvent(orange, K, J);
    orangeMe2.replayAndCheck(mbta);
    MoveEvent orangeMe3 = new MoveEvent(orange, J, I);
    orangeMe3.replayAndCheck(mbta);
    MoveEvent orangeMe4 = new MoveEvent(orange, I, H);
    orangeMe4.replayAndCheck(mbta);

    System.out.println("Line: " + mbta.trainAndStationsKVP);
  }

  @Test public void autograderCheckEndTest()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");
    Train red = Train.make("red");
    Train orange = Train.make("orange");
    Train green = Train.make("green");
    Train blue = Train.make("blue");

    Station s1 = Station.make("Davis");
    Station s2 = Station.make("Harvard");
    Station s3 = Station.make("Kendall");
    Station s4 = Station.make("Park");
    Station s5 = Station.make("Downtown Crossing");
    Station s6 = Station.make("South Station");
    Station s7 = Station.make("Broadway");
    Station s8 = Station.make("Andrew");
    Station s9 = Station.make("JFK");

    Passenger Bob = Passenger.make("Bob");
    Passenger Alice = Passenger.make("Alice");
    Passenger Carol = Passenger.make("Carol");
    Passenger John = Passenger.make("John");

    BoardEvent be1 = new BoardEvent(Alice, red, s1);
    be1.replayAndCheck(mbta);

    MoveEvent me1 = new MoveEvent(red, s1, s2);
    me1.replayAndCheck(mbta);
    MoveEvent me2 = new MoveEvent(red, s2, s3);
    me2.replayAndCheck(mbta);

    DeboardEvent dbe1 = new DeboardEvent(Alice, red, s3);
    dbe1.replayAndCheck(mbta);

    try
    {
      mbta.checkEnd();
    }
    catch (Exception ex)
    {
      System.out.println("Expected Exception: " + ex);
    }
  }

  @Test public void boardAndDeboardRightAway()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample.json");

    Train red = Train.make("red");
    Train orange = Train.make("orange");
    Train green = Train.make("green");
    Train blue = Train.make("blue");

    Station s1 = Station.make("Davis");
    Station s2 = Station.make("Harvard");
    Station s3 = Station.make("Kendall");
    Station s4 = Station.make("Park");
    Station s5 = Station.make("Downtown Crossing");
    Station s6 = Station.make("South Station");
    Station s7 = Station.make("Broadway");
    Station s8 = Station.make("Andrew");
    Station s9 = Station.make("JFK");

    Passenger John = Passenger.make("John");

    BoardEvent be1 = new BoardEvent(John, red, s1);
    be1.replayAndCheck(mbta);

    MoveEvent me1 = new MoveEvent(red, s1, s2);
    me1.replayAndCheck(mbta);


    DeboardEvent DeBe1 = new DeboardEvent(John, red, s2);
    DeBe1.replayAndCheck(mbta);

    BoardEvent be2 = new BoardEvent(John, red, s2);
    be2.replayAndCheck(mbta);
  }

  @Test public void boardAndDeboard2Passengers()
  {
    MBTA mbta = new MBTA();
    // mbta.loadConfig("sample.json");

    Train red = Train.make("red");
    // Train orange = Train.make("orange");
    // Train green = Train.make("green");
    // Train blue = Train.make("blue");

    Station s1 = Station.make("Davis");
    Station s2 = Station.make("Harvard");
    Station s3 = Station.make("Kendall");
    Station s4 = Station.make("Park");
    Station s5 = Station.make("Downtown Crossing");
    Station s6 = Station.make("South Station");
    Station s7 = Station.make("Broadway");
    Station s8 = Station.make("Andrew");
    Station s9 = Station.make("JFK");

    Passenger John = Passenger.make("John");
    Passenger Alice = Passenger.make("Alice");

    mbta.addLine("red", List.of("Davis", "Harvard", "Kendall", "Park"));
    mbta.addJourney("Alice", List.of("Davis", "Kendall"));
    mbta.addJourney("John", List.of("Harvard", "Kendall"));

    BoardEvent be1 = new BoardEvent(Alice, red, s1);
    be1.replayAndCheck(mbta);

    MoveEvent me1 = new MoveEvent(red, s1, s2);
    me1.replayAndCheck(mbta);

    BoardEvent be2 = new BoardEvent(John, red, s2);
    be2.replayAndCheck(mbta);

    MoveEvent me2 = new MoveEvent(red, s2, s3);
    me2.replayAndCheck(mbta);

    DeboardEvent db1 = new DeboardEvent(Alice, red, s3);
    db1.replayAndCheck(mbta);

    DeboardEvent db2 = new DeboardEvent(John, red, s3);
    db2.replayAndCheck(mbta);
    System.out.println("Board Pass: " + mbta.trainToBoardedPassengers);
    mbta.checkEnd();
  }

  @Test public void checkEndTest()
  {
    MBTA mbta = new MBTA();

    Train a = Train.make("a");
    Train b = Train.make("b");
    Train c = Train.make("c");
    Train d = Train.make("d");
    Train e = Train.make("e");
    Train f = Train.make("f");
    Train g = Train.make("g");
    Train h = Train.make("h");
    Train i = Train.make("i");
    Train j = Train.make("j");

    Station A = Station.make("A");
    Station B = Station.make("B");
    Station C = Station.make("C");
    Station D = Station.make("D");
    Station E = Station.make("E");
    Station F = Station.make("F");
    Station G = Station.make("G");
    Station H = Station.make("H");
    Station I = Station.make("I");
    Station J = Station.make("J");
    Station K = Station.make("K");
    Station L = Station.make("L");
    Station M = Station.make("M");
    Station N = Station.make("N");
    Station O = Station.make("O");
    Station P = Station.make("P");
    Station Q = Station.make("Q");
    Station R = Station.make("R");
    Station S = Station.make("S");
    Station T = Station.make("T");
    Station U = Station.make("U");
    Station V = Station.make("V");
    Station W = Station.make("W");
    Station X = Station.make("X");
    Station Y = Station.make("Y");
    Station Z = Station.make("Z");
    Station AA = Station.make("AA");
    Station BB = Station.make("BB");
    Station CC = Station.make("CC");

    Passenger Alice = Passenger.make("Alice");
    Passenger Bob = Passenger.make("Bob");

    mbta.addLine("a", List.of("A", "B", "C"));
    mbta.addLine("b", List.of("D", "E", "F"));
    mbta.addLine("c", List.of("G", "H", "I"));
    mbta.addLine("d", List.of("J", "K", "L"));
    mbta.addLine("e", List.of("M", "N", "O"));
    mbta.addLine("f", List.of("P", "Q", "R"));
    mbta.addLine("g", List.of("S", "T", "U"));
    mbta.addLine("h", List.of("V", "W", "X"));
    mbta.addLine("i", List.of("Y", "Z", "AA"));
    mbta.addLine("j", List.of("BB", "CC", "DD"));

    mbta.addJourney("Bob", List.of("J", "L"));
    mbta.addJourney("Alice", List.of("A", "B"));

    BoardEvent be1 = new BoardEvent(Alice, a, A);
    be1.replayAndCheck(mbta);

    MoveEvent me1 = new MoveEvent(a, A, B);
    me1.replayAndCheck(mbta);

    DeboardEvent dbe1 = new DeboardEvent(Alice, a, B);
    dbe1.replayAndCheck(mbta);

    // BoardEvent be2 = new BoardEvent(Bob, d, J);
    // be2.replayAndCheck(mbta);
    
    MoveEvent me2 = new MoveEvent(d, J, K);
    me2.replayAndCheck(mbta);
    
    
    MoveEvent me3 = new MoveEvent(d, K, L);
    me3.replayAndCheck(mbta);
    
    // DeboardEvent dbe2 = new DeboardEvent(Bob, d, L);
    // dbe2.replayAndCheck(mbta);
    System.out.println("Board Pass: " + mbta.trainToBoardedPassengers);
    System.out.println("Pass and Station: " + mbta.passengerAndStationsKVP);
    try
    {
      mbta.checkEnd();
    }
    catch (Exception ex)
    {
      System.out.println("Expected Exception: " + ex);
    }
  }

  @Test public void waitinAtStationTest()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample4.json");

    Train red = Train.make("red");

    Passenger Bob = Passenger.make("Bob");
    Passenger John = Passenger.make("John");
    Passenger Alice = Passenger.make("Alice");
    Passenger Bill = Passenger.make("Bill");
    Passenger Ryan = Passenger.make("Ryan");
    Passenger Sam = Passenger.make("Sam");

    Station s1 = Station.make("Davis");
    Station s2 = Station.make("Harvard");
    Station s3 = Station.make("Kendall");
    Station s4 = Station.make("Park");
    Station s5 = Station.make("Downtown Crossing");
    Station s6 = Station.make("South Station");
    Station s7 = Station.make("Broadway");
    Station s8 = Station.make("Andrew");
    Station s9 = Station.make("JFK");

    BoardEvent be1 = new BoardEvent(Bob, red, s1);
    be1.replayAndCheck(mbta);
    BoardEvent be2 = new BoardEvent(John, red, s1);
    be2.replayAndCheck(mbta);
    BoardEvent be3 = new BoardEvent(Alice, red, s1);
    be3.replayAndCheck(mbta);

    MoveEvent me1 = new MoveEvent(red, s1, s2);
    me1.replayAndCheck(mbta);

    MoveEvent me3 = new MoveEvent(red, s2, s3);
    me3.replayAndCheck(mbta);

    DeboardEvent db1 = new DeboardEvent(Bob, red, s3);
    db1.replayAndCheck(mbta);

    BoardEvent be4 = new BoardEvent(Bill, red, s3);
    be4.replayAndCheck(mbta);
    BoardEvent be5 = new BoardEvent(Bob, red, s3);
    be5.replayAndCheck(mbta);
    BoardEvent be6 = new BoardEvent(Ryan, red, s3);
    be6.replayAndCheck(mbta);
    BoardEvent be7 = new BoardEvent(Sam, red, s3);
    be7.replayAndCheck(mbta);

    MoveEvent me4 = new MoveEvent(red, s3, s4);
    me4.replayAndCheck(mbta);

    DeboardEvent db2 = new DeboardEvent(Bob, red, s4);
    db2.replayAndCheck(mbta);
    
    DeboardEvent db3 = new DeboardEvent(Bill, red, s4);
    db3.replayAndCheck(mbta);
    
    
    
    System.out.println("Waiting people: " + mbta.stationAndWaitingPassenger);
    // System.out.println("Line: " + mbta.trainAndStationsKVP);
    System.out.println("Journey: " + mbta.passengerAndStationsKVP);
  }

  @Test public void movingTest()
  {
    MBTA mbta = new MBTA();
    mbta.loadConfig("sample4.json");

    Train red = Train.make("red");

    Passenger Bob = Passenger.make("Bob");
    Passenger John = Passenger.make("John");
    Passenger Alice = Passenger.make("Alice");
    Passenger Bill = Passenger.make("Bill");
    Passenger Ryan = Passenger.make("Ryan");
    Passenger Sam = Passenger.make("Sam");

    Station s1 = Station.make("Davis");
    Station s2 = Station.make("Harvard");
    Station s3 = Station.make("Kendall");
    Station s4 = Station.make("Park");
    Station s5 = Station.make("GREEN_PASS");
    Station s6 = Station.make("Downtown Crossing");
    Station s7 = Station.make("South Station");
    Station s8 = Station.make("Broadway");
    Station s9 = Station.make("Andrew");
    Station s10 = Station.make("JFK");

    mbta.moveTrain(mbta, s1, s2, red);
    mbta.moveTrain(mbta, s2, s3, red);
    mbta.moveTrain(mbta, s3, s4, red);
    mbta.moveTrain(mbta, s4, s5, red);
    mbta.moveTrain(mbta, s5, s6, red);
    mbta.moveTrain(mbta, s6, s7, red);
    mbta.moveTrain(mbta, s7, s8, red);
    mbta.moveTrain(mbta, s8, s9, red);
    mbta.moveTrain(mbta, s9, s10, red);
    mbta.moveTrain(mbta, s10, s9, red);
    mbta.moveTrain(mbta, s9, s8, red);
    mbta.moveTrain(mbta, s8, s7, red);
    mbta.moveTrain(mbta, s7, s6, red);
    mbta.moveTrain(mbta, s6, s5, red);
    mbta.moveTrain(mbta, s5, s4, red);
    mbta.moveTrain(mbta, s4, s3, red);
    mbta.moveTrain(mbta, s3, s2, red);
    mbta.moveTrain(mbta, s2, s1, red);
    mbta.moveTrain(mbta, s1, s2, red);
    System.out.println("Line: " + mbta.trainAndStationsKVP.get(red));


    // "Bob": [ "Davis", "Kendall", "Park"],
    // "John": [ "Davis", "Park" ],
    // "Alice": [ "Davis", "South Station"], 

    // "Bill": [ "Kendall", "Park" ],
    // "Ryan": [ "Kendall", "Downtown Crossing" ],
    // "Sam": [ "Kendall", "JFK"]
  }
  
  
  ///////////CONCURRENCY TESTING///////////
  // @Test public void run_sim()
  // {
  //   MBTA mbta = new MBTA();
  //   mbta.loadConfig("sample.json");
  //   Log log = new Log();

  //   Map<String, Thread> trainAndPassengersThreads = new HashMap<>();

  //   /* each train gets a Thread */
  //   for (Train t : mbta.trainAndStationsKVP.keySet())
  //   {
  //     trainAndPassengersThreads.put("Train#" + t.toString(), new TrainThread(mbta, t.toString(), log));
  //   }

  //   for (Passenger p : mbta.passengerAndStationsKVP.keySet())
  //   {
  //     trainAndPassengersThreads.put("Passenger#" + p.toString(), new PassengerThread(mbta, p.toString(), log));
  //   }

  //   for(String s : trainAndPassengersThreads.keySet())
  //   {
  //     System.out.println("S: " + s);
  //     trainAndPassengersThreads.get(s).run();
  //   }
  // }
}
