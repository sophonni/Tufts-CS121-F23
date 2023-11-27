import java.util.*;

public class Station extends Entity {
  private static Map<String, Station> createdStations = new HashMap<>();
  private Station(String name) { super(name); }

  public static Station make(String name) {

    if (name == null || name.isEmpty())
    {
      throw new IllegalArgumentException("Error: Name of Station can't be null or empty.");
    }
    else
    {
      /* return existing Station */
      if (createdStations.containsKey(name))
      {
        return createdStations.get(name);
      }
      /* create new Station */
      else
      {
        Station newStation = new Station(name);
        createdStations.put(name, newStation);
        return newStation;
      }
    }
  }
}
