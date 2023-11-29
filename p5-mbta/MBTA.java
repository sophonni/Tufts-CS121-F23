import com.google.gson.*;
import java.io.*;
import java.time.format.SignStyle;
import java.util.*;

public class MBTA {
  public Map<String, LinkedList<String>> trainLine = new HashMap<>();
  public Map<String, LinkedList<String>> passengerJourney = new HashMap<>();

  public Map<String, LinkedList<String>> trainForwardStations = new HashMap<>();
  public Map<String, LinkedList<String>> trainBackwardStations = new HashMap<>();

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
      /* only create new line if name doesn't already exist */
      if (!trainLine.containsKey(name))
      {
        this.trainLine.put(name, new LinkedList<>(stations));
      }
      else
      {
        throw new IllegalArgumentException("Error in {addLine}: Line with the name {" + name + "} already exist.");
      }
    }
  }

  // Adds a new planned journey to the simulation
  public void addJourney(String name, List<String> stations) {
    /* only create new journey for the passenger if name doesn't already exist */
    if (!passengerJourney.containsKey(name))
    {
      this.passengerJourney.put(name, new LinkedList<String>(stations));
    }
    else
    {
      throw new IllegalArgumentException("Error is {addJourney}: Journey with the name {" + name + "} already exist.");
    }
  }

  // Return normally if initial simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkStart() {
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {
  }

  // reset to an empty simulation
  public void reset() {
    this.trainLine.clear();
    this.passengerJourney.clear();
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
    for (String key : this.trainLine.keySet())
    {
      this.trainForwardStations.put(key, this.trainLine.get(key));
    }
  }

  public void moveTrainForward(Train t, Station currStation)
  {
    /* remove currStation from the map for forward movement and add the front of the map for backward movement */
    LinkedList<String> forwardStations = this.trainForwardStations.get(t.toString());
    forwardStations.remove(currStation.toString());
    
    LinkedList<String> backwardStations = null;
    if (!this.trainBackwardStations.containsKey(t.toString()))
    {
      backwardStations = new LinkedList<>();
    }
    else
    {
      backwardStations = this.trainBackwardStations.get(t.toString());
    }
    backwardStations.addFirst(currStation.toString());    
    this.trainBackwardStations.put(t.toString(), backwardStations);
  }
  
  public void moveTrainBackward(Train t, Station currStation)
  {
    /* remove currStation from the map for backward movement and add the front of the map for forward movement */
    LinkedList<String> backwardStations = this.trainBackwardStations.get(t.toString());
    backwardStations.remove(currStation.toString());

    LinkedList<String> forwardStations = null;
    if (!this.trainForwardStations.containsKey(t.toString()))
    {
      forwardStations = new LinkedList<>();
    }
    else
    {
      forwardStations = this.trainForwardStations.get(t.toString());
    }
    forwardStations.addFirst(currStation.toString());
    this.trainForwardStations.put(t.toString(), forwardStations);
  }

  public void PrintLines()
  {
    System.out.println("Lines: " + this.trainLine);
  }

  public void PrintJournies()
  {
    System.out.println("Trips: " + this.passengerJourney);
  }
}
