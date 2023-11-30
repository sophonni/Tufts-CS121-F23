import java.util.*;

public class DeboardEvent implements Event {
  public final Passenger p; public final Train t; public final Station s;
  public DeboardEvent(Passenger p, Train t, Station s) {
    this.p = p; this.t = t; this.s = s;
  }
  public boolean equals(Object o) {
    if (o instanceof DeboardEvent e) {
      return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(p, t, s);
  }
  public String toString() {
    return "Passenger " + p + " deboards " + t + " at " + s;
  }
  public List<String> toStringList() {
    return List.of(p.toString(), t.toString(), s.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    /*
     * passenger 'p' is on train 't' want to deboard at station 's'
     * 
     */

     Map<String, LinkedList<String>> trainLine = mbta.trainLine;
    /* ensure that the train exist */
    if (trainLine.containsKey(this.t.toString()) || this.t != null)
    {
      LinkedList<String> lineStations = trainLine.get(this.t.toString());
      /* ensure the train line has stations */
      if (lineStations != null)
      {
        /* ensure that the train stations contains the station the passenger is deboarding at */
        if (lineStations.contains(this.s.toString()))
        {
          /* ensure that the current station of the train is the station the passenger deboarding at */
          if (lineStations.getFirst().equals(this.s.toString()))
          {
            LinkedList<Passenger> boardPassengers = mbta.trainToBoardedPassengers.get(this.t.toString());

            /* create a new list to store boarded passenger if there doesn't already exist a list */
            if (!boardPassengers.isEmpty())
            {
              /* ensure that all passengers from all station that is willing to board the train have uniqe name e.g if John board red train from station1, John cannot board red train from any other stations */
              if (!boardPassengers.contains(this.p))
              {
                throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Passenger {" + this.p.toString() + "} has not yet board the train {" + this.t.toString() + "}.");
              }
              else
              {
                boardPassengers.remove(boardPassengers.indexOf(this.p));
                mbta.trainToBoardedPassengers.put(this.t.toString(), boardPassengers);
              }
            }
          }
          else
          {
            throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Unable to deboard at station {" +this.s.toString() + "} as current station of the train is {" + lineStations.getFirst() + "}.");
          }
        }
        else
        {
          throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Train {" + this.t + "} does not contains the station {" + this.s.toString() + "}.");
        }
      }
      else
      {
        throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Unable to deboard from Train {" + this.t.toString() + "}.");
      }
    }
    else
    {
      throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Train {" + this.t.toString() + "} does not exist.");
    }
  }
}
