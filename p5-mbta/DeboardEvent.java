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
    Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;
    System.out.println("Lines Deboard: " + trainLine);
    /* ensure that the train exist */
    if (trainLine.containsKey(this.t) || this.t != null)
    {
      LinkedList<Station> lineStations = trainLine.get(this.t);
      /* ensure the train line has stations */
      if (lineStations != null)
      {
        /* ensure that the train stations contains the station the passenger is deboarding at */
        if (lineStations.contains(this.s))
        {
          /* ensure that the current station of the train matches with the station the passenger want to get off at */
          if (lineStations.getFirst().equals(this.s))
          {
            LinkedList<Passenger> boardPassengers = mbta.trainToBoardedPassengers.get(this.t);

            /* ensure that there are passengers that have boarded the train */
            if (boardPassengers != null)
            {
              /* ensure the given passenger has boarded the given train */
              if (!boardPassengers.contains(this.p))
              {
                throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Passenger {" + this.p.toString() + "} has not yet board the train {" + this.t.toString() + "}.");
              }
              else
              {
                LinkedList<Station> givenPassengerJourney = mbta.passengerAndStationsKVP.get(this.p);
                /* ensure that the journey to the given station for the given passenger has been initialize */
                if (givenPassengerJourney.contains(this.s))
                {
                  if (mbta.passengerAndStationsKVP.get(this.p) != null || !mbta.passengerAndStationsKVP.get(this.p).isEmpty())
                  {
                    givenPassengerJourney.remove(this.s);
                    mbta.passengerAndStationsKVP.put(this.p, givenPassengerJourney);
                  }

                  /* remove passenger from the map of boarded passenger if there journey is complete */
                  if (mbta.passengerAndStationsKVP.get(this.p) == null || mbta.passengerAndStationsKVP.get(this.p).isEmpty())
                  {
                    System.out.println("Remove Pass: " + this.p);
                    boardPassengers.remove(boardPassengers.indexOf(this.p));
                  }
                  mbta.trainToBoardedPassengers.put(this.t, boardPassengers);
                }
                else
                {
                  throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Journey to station {" + this.s + "} for passenger {" + this.p.toString() + "} has not yet been initialized.");
                }
              }
            }
            else
            {
              throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: There doesn't exist any passengers that have boarded the train {" + this.t.toString() + "}.");
            }
          }
          else
          {
            throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Unable to deboard at station {" +this.s.toString() + "} as current station of the train is {" + lineStations.getFirst() + "}.");
          }
        }
        else
        {
          throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Train {" + this.t.toString() + "} does not contains the station {" + this.s.toString() + "}.");
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
