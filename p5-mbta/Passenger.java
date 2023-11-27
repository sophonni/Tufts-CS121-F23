import java.util.*;

public class Passenger extends Entity {
  private static Map<String, Passenger> createdPassengers = new HashMap<>();
  private Passenger(String name) { super(name); }

  public static Passenger make(String name) {
    if (name == null || name.isEmpty())
    {
      throw new IllegalArgumentException("Error: Name of Passenger can't be null or empty.");
    }
    else
    {
      /* return existing Passenger */
      if (createdPassengers.containsKey(name))
      {
        return createdPassengers.get(name);
      }
      /* create new Passenger */
      else
      {
        Passenger newPassenger = new Passenger(name);
        createdPassengers.put(name, newPassenger);
        return newPassenger;
      }
    }
  }

  public static void PrintMapHelper()
  {
    System.out.println("Passenger Map: " + createdPassengers);
  }
}
