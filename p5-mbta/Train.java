import java.util.*;

public class Train extends Entity {
  private static Map<String, Train> createdTrains = new HashMap<>();
  private Train(String name) { super(name); }

  public static Train make(String name) {
    if (name == null || name.isEmpty())
    {
      throw new IllegalArgumentException("Error: Name of Train can't be null or empty.");
    }
    else
    {
      /* return existing Train */
      if (createdTrains.containsKey(name))
      {
        return createdTrains.get(name);
      }
      /* create new Train */
      else
      {
        Train newTrain = new Train(name);
        createdTrains.put(name, newTrain);
        return newTrain;
      }
    }
  }
}
