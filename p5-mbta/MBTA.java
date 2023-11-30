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

  public Map<Train, LinkedList<Passenger>> trainToBoardedPassengers = new HashMap<>();

  public boolean isTrainMovingForward = true;

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
    for (Train t : this.trainAndStationsKVP.keySet())
    {
      LinkedList<Station> initialStationsLayout = this.trainAndStationsKVP.get(t);
      Station firstStation = initialStationsLayout.getFirst();

      LinkedList<Station> movingForwardStartStations = this.trainForwardStations.get(t);
      Station startStation = movingForwardStartStations.getFirst();

      if (!firstStation.equals(startStation))
      {
        throw new IllegalArgumentException("Error in MBTA#checkStart: First station is {" + firstStation.toString() + "} while train starts at station {" + startStation.toString() + "}.");
      }
    }
    return;
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {
    for (Train t : this.trainAndStationsKVP.keySet())
    {
      LinkedList<Station> initialStationsLayout = this.trainAndStationsKVP.get(t);
      Station lastStation = initialStationsLayout.getLast();

      LinkedList<Station> movingBackwardStartStations = this.trainBackwardStations.get(t);
      Station endStation = movingBackwardStartStations.getLast();

      if (!lastStation.equals(endStation))
      {
        throw new IllegalArgumentException("Error in MBTA#checkStart: Last station is {" + lastStation.toString() + "} while train ends at station {" + endStation.toString() + "}.");
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
      throw new IllegalArgumentException("Error is {loadConfig}: Unable to parse JSON file.");
    }

    /* store trains and their initial station */
    for (Train t : this.trainAndStationsKVP.keySet())
    {
      this.trainForwardStations.put(t, this.trainAndStationsKVP.get(t));
    }
  }

  public void moveTrainForward(Train t, Station currStation)
  {
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
