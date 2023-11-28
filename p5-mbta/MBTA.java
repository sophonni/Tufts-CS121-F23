import java.util.*;

public class MBTA {
  Map<String, LinkedHashSet<String>> createdLines = new HashMap<>();
  Map<String, LinkedList<String>> createdPassengerJourney = new HashMap<>();
  // Creates an initially empty simulation
  public MBTA() { }

  // Adds a new transit line with given name and stations
  public void addLine(String name, List<String> stations) {
    LinkedHashSet<String> nonDuplicateStations = new LinkedHashSet<String>(stations);

    /* ensure that given list of stations does not contains duplicate */
    if (nonDuplicateStations.size() != stations.size())
    {
      throw new IllegalArgumentException("Error in {addLine}: Given list of stations contains duplicate.");
    }
    else
    {
      /* only create new line if name doesn't already exist */
      if (!createdLines.containsKey(name))
      {
        this.createdLines.put(name, nonDuplicateStations);
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
    if (!createdPassengerJourney.containsKey(name))
    {
      this.createdPassengerJourney.put(name, new LinkedList<String>(stations));
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
    this.createdLines.clear();
    this.createdPassengerJourney.clear();
  }

  // adds simulation configuration from a file
  public void loadConfig(String filename) {
    throw new UnsupportedOperationException();
  }

  public void PrintLineHelper()
  {
    System.out.println("Lines Map: " + this.createdLines);
  }

  public void PrintJourneyHelper()
  {
    System.out.println("Journey Map: " + this.createdPassengerJourney);
  }
}
