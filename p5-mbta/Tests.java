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

  //   // try
  //   // {
  //   //   mbta.checkEnd();
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }

  //   MoveEvent me2 = new MoveEvent(red, s2, s3);
  //   me2.replayAndCheck(mbta);

  //   DeboardEvent db2 = new DeboardEvent(John, red, s3);
  //   db2.replayAndCheck(mbta);

  //   MoveEvent me3 = new MoveEvent(red, s3, s4);
  //   me3.replayAndCheck(mbta);

  //   /* train move to next station after train is empty test */
  //   // try
  //   // {
  //   //   mbta.checkEnd();
  //   // }
  //   // catch (Exception e)
  //   // {
  //   //   System.out.println("Expected Exception: " + e);
  //   // }
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

  @Test public void autoGraderLogIsValidTest()
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








  //////////////////////OTHER PEOPLE TEST CASES////////////////////////////////
  // @Test public void others1() {
  //   MBTA mbta = new MBTA();
  
  //   mbta.addLine("red", List.of("Braintree", "Alewife"));
  //   mbta.addLine("green", List.of("Tufts", "Government Center"));
  
  //   Train rTrain = Train.make("red");
  //   Train gTrain = Train.make("green");
  
  //   Station braintree = Station.make("Braintree");
  //   Station alewife = Station.make("Alewife");
  //   Station tufts = Station.make("Tufts");
  //   Station govCenter = Station.make("Government Center");
  
  //   Log log = new Log(List.of(
  //           new MoveEvent(rTrain, braintree, alewife),
  //           new MoveEvent(gTrain, tufts, govCenter)
  //   ));
  
  //   Verify.verify(mbta, log);
  // }

  // @Test public void others2() {
  //   MBTA sim = new MBTA();
  //   Train red = Train.make("red");
  //   Station stop1 = Station.make("stop1");
  //   Station stop2 = Station.make("stop2");
  //   Station stop3 = Station.make("stop3");
  //   Passenger p1 = Passenger.make("p1");

  //   sim.addLine("red", List.of("stop1", "stop2", "stop3"));
  //   sim.addJourney("p1", List.of("stop1", "stop5"));

  //   List<Event> events = new LinkedList<>();
  //   events.add(new BoardEvent(p1, red, stop1));
  //   events.add(new MoveEvent(red, stop1, stop2));
  //   events.add(new MoveEvent(red, stop2, stop3));
  //   events.add(new DeboardEvent(p1, red, stop3));

  //   Log log = new Log(events);
  //   Verify.verify(sim, log);
  // }
}
