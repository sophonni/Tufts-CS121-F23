import com.google.gson.*;
import java.io.*;
import java.time.format.SignStyle;
import java.util.*;

public class MBTA {
  public Map<Train, LinkedList<Station>> trainAndStationsKVP = new HashMap<>();
  public Map<Passenger, LinkedList<Station>> passengerAndStationsKVP = new HashMap<>();

  /* first element of the LinkedList represents the current station the train is at */
  public Map<Train, LinkedList<Station>> trainForwardStations = new HashMap<>();
  public Map<Train, LinkedList<Station>> trainBackwardStations = new HashMap<>();

  /* use to keep track on passengers and the train they boarded */
  public Map<Train, LinkedList<Passenger>> trainToBoardedPassengers = new HashMap<>();

  /* original layout of lines and journeys from config file */
  public Map<Train, LinkedList<Station>> originalTrainAndStationKVP = new HashMap<>();

  public boolean isTrainMovingForward = true;

  /* use to keep track on whether or not trains have passengers to drop off */
  public Map<Train, Boolean> trainAndIfPassengerHasBeenBoarded = new HashMap<>();

  public Map<Train, Boolean> trainAndIfItsMovingForward = new HashMap<>();

  // Creates an initially empty simulation
  public MBTA() { }

  // Adds a new transit line with given name and stations
  public void addLine(String name, List<String> stations) {
    LinkedHashSet<String> nonDuplicateStations = new LinkedHashSet<>(stations);

    /* ensure that given list of stations does not contains duplicate */
    if (nonDuplicateStations.size() != stations.size())
    {
      throw new IllegalArgumentException("Error in {addLine}: Given list of stations contains duplicate.");
    }
    else
    {
      /* make train and stations from given parameters and store */
      Train train = Train.make(name);
      LinkedList<Station> trainStationList = new LinkedList<>();
      for (String s : stations)
      {
        Station newStation = Station.make(s);
        trainStationList.add(newStation);
      }
      this.trainForwardStations.put(train, trainStationList);
      this.trainAndStationsKVP.put(train, trainStationList);

      /* keep a list of original order of the stations for each train */
      this.originalTrainAndStationKVP.put(train, new LinkedList<>(trainStationList));

      for(Train t : this.originalTrainAndStationKVP.keySet())
      {
        this.trainAndIfItsMovingForward.put(train, true);
      }
    }
  }

  // Adds a new planned journey to the simulation
  public void addJourney(String name, List<String> stations) {
    /* make passenger and stations from given parameters and store */
    Passenger passenger = Passenger.make(name);
    LinkedList<Station> passengerStationList = new LinkedList<>();
    for (String s : stations)
    {
      Station passengerStation = Station.make(s);
      passengerStationList.add(passengerStation);
    }
    this.passengerAndStationsKVP.put(passenger, passengerStationList);

  }

  // Return normally if initial simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkStart() {
    for (Train t : this.originalTrainAndStationKVP.keySet())
    {
      LinkedList<Station> initialStationsLayout = this.originalTrainAndStationKVP.get(t);
      Station firstStation = initialStationsLayout.getFirst();
      
      LinkedList<Station> movingForwardStartStations = this.trainForwardStations.get(t);
      Station startStation = movingForwardStartStations.getFirst();

      if (!firstStation.equals(startStation))
      {
        throw new IllegalArgumentException("Error in MBTA#checkStart: First station of train {" + t.toString() +"} is {" + firstStation.toString() + "} while train starts at station {" + startStation.toString() + "}.");
      }
    }
    return;
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {

    /*
     * The simulation ends when all passengers have arrived at their 
     * final stops. It is an error for the simulation to end early. 
     * It is fine if trains run a bit past the point where all passengers have finished their journeys.
     */
    for (Train t : this.originalTrainAndStationKVP.keySet())
    {
      /*
       * get all passengers that board train 't'
       * if null or empty, that's ok
       * else through exception
       * get first element of the ll from trainAndStationsKVP of the train 't'
       */

      // System.out.println("Curr Train: " + t);
      // System.out.println("Board Passenger: " + this.trainToBoardedPassengers);
      // System.out.println("Curr Train Line: " + this.trainAndStationsKVP);
      System.out.println("Train to Boarded Pass: " + this.trainToBoardedPassengers);

      // if(this.trainToBoardedPassengers.get(t) != null)
      // {
      //   throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> train {" + t.toString() + "} ends before all passengers have been deboarded.");
      // }

      if(this.trainToBoardedPassengers.get(t) == null || this.trainToBoardedPassengers.get(t).isEmpty())
      {
        System.out.println("YES EMPTY");
        // System.out.println("Curr Station: " + this.trainAndStationsKVP.get(t).getFirst());
        // System.out.println("Last Station: " + this.lastStationOfLastDeboardPassenger);

        /* ensure that there are passenger/s that have boarded the train 't' */
        if (this.trainAndIfPassengerHasBeenBoarded.get(t) != null)
        {
          /* get(t) can't returns false b/c if get(t) returns a non-null object, that means it's true */
          // if (!this.trainAndIfPassengerHasBeenBoarded.get(t))
          // {
          //   if (!this.trainAndStationsKVP.get(t).getFirst().equals(this.originalTrainAndStationKVP.get(t).getFirst()))
          //   {
          //     throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> Stop at station {" + this.trainAndStationsKVP.get(t).getFirst() + "} while train is empty.");
          //   }
          // }
        }

        // /* train should not move from its initial station when there's no passsenger to board or deboard */
        else
        {
          // if (!this.trainAndStationsKVP.get(t).getFirst().equals(this.originalTrainAndStationKVP.get(t).getFirst()))
          // {
          //   throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> Train {" + t.toString() + "}move to station {" + this.trainAndStationsKVP.get(t).getFirst() + "} while train is empty.");
          // }
        }
      }
      else
      {
        throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> train {" + t.toString() + "} ends before all passengers have been deboarded.");
      }
    }

    /* ensure that all passengers completed their journey */
    for (Passenger p : this.passengerAndStationsKVP.keySet())
    {
      if (!this.passengerAndStationsKVP.get(p).isEmpty())
      {
        throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> Passenger {" + p.toString() + "} with journey {" + this.passengerAndStationsKVP.get(p) + "} needs to be drop off.");
      }
    }
    return;
  }

  // reset to an empty simulation
  public void reset() {
    this.trainAndStationsKVP.clear();
    this.passengerAndStationsKVP.clear();
  }

  // adds simulation configuration from a file
  public void loadConfig(String filename) {
    Gson gson = new Gson();

    try (Reader reader = new FileReader(filename)) {

      MBTAData mbtaData = gson.fromJson(reader, MBTAData.class);

      /* accessing trainLine from JSON config and storing in a HashMap */
      Map<String, LinkedList<String>> trainLinesFromConfig = mbtaData.getLines();
      for (String key : trainLinesFromConfig.keySet())
      {
        this.addLine(key, trainLinesFromConfig.get(key));
      }
      
      /* accessing trips from JSON config and storing in a HashMap */
      Map<String, LinkedList<String>> tripesFromConfig = mbtaData.getTrips();
      for (String key : tripesFromConfig.keySet())
      {
        this.addJourney(key, tripesFromConfig.get(key));
      }
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException("Error in {loadConfig}: Unable to parse JSON file.");
    }

    /* store trains and their initial station */
    for (Train t : this.trainAndStationsKVP.keySet())
    {
      this.trainForwardStations.put(t, this.trainAndStationsKVP.get(t));
    }
  }

  public void moveTrainForward(Train t, Station currStation)
  {
    // Station stationToMoveTowards = this.trainAndStationsKVP.get(t).get(this.trainAndStationsKVP.get(t).indexOf(currStation) + 1);
    // Station otherTrainCurrStation = null;
    // for (Train currTrain : this.trainAndStationsKVP.keySet())
    // {
    //   /* ensure that there's no train at the station that train 't' wants to move to : SD TODO : NEED TO DO A THREAD WAIT HERE : THROW EXCEPTION FOR NOW */
    //   if (!currTrain.equals(t))
    //   {
    //     otherTrainCurrStation = this.trainAndStationsKVP.get(currTrain).getFirst();
    //     if (otherTrainCurrStation.equals(stationToMoveTowards))
    //     {
    //       throw new IllegalArgumentException("Error in MBTA#moveTrainForward: Train {" + t.toString() + "} can't move to station {" + stationToMoveTowards.toString() + "} b/c train {" + currTrain.toString() + "} is at station {" + stationToMoveTowards.toString() + "}.");
    //     }
    //   }
    // }

    /* remove currStation from the map for forward movement and add the front of the map for backward movement */
    LinkedList<Station> forwardStations = this.trainAndStationsKVP.get(t);
    forwardStations.remove(currStation);
    
    LinkedList<Station> backwardStations = null;
    if (!this.trainBackwardStations.containsKey(t))
    {
      backwardStations = new LinkedList<>();
    }
    else
    {
      backwardStations = this.trainBackwardStations.get(t);
    }
    backwardStations.addFirst(currStation);    
    this.trainBackwardStations.put(t, backwardStations);
  }
  
  public void moveTrainBackward(Train t, Station currStation)
  {
    // Station stationToMoveTowards = this.trainAndStationsKVP.get(t).get(this.trainAndStationsKVP.get(t).indexOf(currStation) + 1);
    // Station otherTrainCurrStation = null;
    // for (Train currTrain : this.trainAndStationsKVP.keySet())
    // {
    //   /* ensure that there's no train at the station that train 't' wants to move to : SD TODO : NEED TO DO A THREAD WAIT HERE : THROW EXCEPTION FOR NOW */
    //   if (!currTrain.equals(t))
    //   {
    //     otherTrainCurrStation = this.trainAndStationsKVP.get(currTrain).getFirst();
    //     if (otherTrainCurrStation.equals(stationToMoveTowards))
    //     {
    //       throw new IllegalArgumentException("Error in MBTA#moveTrainForward: Train {" + t.toString() + "} can't move to station {" + stationToMoveTowards.toString() + "} b/c train {" + currTrain.toString() + "} is at station {" + stationToMoveTowards.toString() + "}.");
    //     }
    //   }
    // }
    /* remove currStation from the map for backward movement and add the front of the map for forward movement */
    LinkedList<Station> backwardStations = this.trainBackwardStations.get(t);
    backwardStations.remove(currStation);

    LinkedList<Station> forwardStations = null;
    if (!this.trainForwardStations.containsKey(t))
    {
      forwardStations = new LinkedList<>();
    }
    else
    {
      forwardStations = this.trainForwardStations.get(t);
    }
    forwardStations.addFirst(currStation);
    this.trainForwardStations.put(t, forwardStations);
  }

  public void PrintLines()
  {
    System.out.println("Lines: " + this.trainAndStationsKVP);
  }

  public void PrintJournies()
  {
    System.out.println("Trips: " + this.passengerAndStationsKVP);
  }
}
