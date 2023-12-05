import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Sim {

  public static void run_sim(MBTA mbta, Log log) {
    Map<String, Thread> trainAndPassengersThreads = new HashMap<>();

    /* each train gets a Thread */
    for (Train t : mbta.trainAndStationsKVP.keySet())
    {
      trainAndPassengersThreads.put("Train#" + t.toString(), new TrainThread(mbta, t.toString(), log));
    }

    for (Passenger p : mbta.passengerAndStationsKVP.keySet())
    {
      trainAndPassengersThreads.put("Passenger#" + p.toString(), new PassengerThread(mbta, p.toString(), log));
    }

    for(String s : trainAndPassengersThreads.keySet())
    {
      System.out.println("S: " + s);
      trainAndPassengersThreads.get(s).run();
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("usage: ./sim <config file>");
      System.exit(1);
    }

    MBTA mbta = new MBTA();
    mbta.loadConfig(args[0]);

    Log log = new Log();

    run_sim(mbta, log);

    String s = new LogJson(log).toJson();
    PrintWriter out = new PrintWriter("log.json");
    out.print(s);
    out.close();

    mbta.reset();
    mbta.loadConfig(args[0]);
    Verify.verify(mbta, log);
  }
}
