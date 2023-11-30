import java.util.*;

public class MoveEvent implements Event {
  public final Train t; public final Station s1, s2;
  public MoveEvent(Train t, Station s1, Station s2) {
    this.t = t; this.s1 = s1; this.s2 = s2;
  }
  public boolean equals(Object o) {
    if (o instanceof MoveEvent e) {
      return t.equals(e.t) && s1.equals(e.s1) && s2.equals(e.s2);
    }
    return false;
  }
  public int hashCode() {
    return Objects.hash(t, s1, s2);
  }
  public String toString() {
    return "Train " + t + " moves from " + s1 + " to " + s2;
  }
  public List<String> toStringList() {
    return List.of(t.toString(), s1.toString(), s2.toString());
  }
  public void replayAndCheck(MBTA mbta) {
    Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;

    /* ensure that the train exist */
    if (trainLine.containsKey(this.t))
    {
      LinkedList<Station> lineStations = trainLine.get(this.t);
  
      /* ensure that the two stations exist in the line */
      if (!lineStations.contains(this.s1) || !lineStations.contains(this.s2) || this.s1 == null || this.s2 == null)
      {
        throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train can't move from station {" + s1.toString() + "} to station {" + s2.toString() + "}.");
      }
      else
      {
        if (mbta.trainBackwardStations.get(t) == null || (mbta.trainBackwardStations.get(t).size() != mbta.trainAndStationsKVP.get(t).size()))
        {
          mbta.isTrainMovingForward = true;
          System.out.println("Moving Forward");
          int s1Index = lineStations.indexOf(this.s1);
          int s2Index = lineStations.indexOf(this.s2);
          /* ensure that the two stations are adjacent */
          if ((s2Index - s1Index) != 1)
          {
            throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingForward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
          }
          else
          {
            /* move train forward */
            mbta.moveTrainForward(this.t, s1);
          }
          
          /* starts moving train backward */
          if (mbta.trainForwardStations.get(this.t).size() == 1)
          {
            mbta.moveTrainForward(this.t, s2);
            mbta.trainAndStationsKVP.put(this.t, mbta.trainBackwardStations.get(t));
            mbta.isTrainMovingForward = false;
          }
        }
        else
        {
          mbta.isTrainMovingForward = false;
          System.out.println("Moving Backward");
          int s1Index = lineStations.indexOf(this.s1);
          int s2Index = lineStations.indexOf(this.s2);
          /* ensure that the two stations are adjacent */
          if ((s2Index - s1Index) != 1)
          {
            throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingBackward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
          }
          else
          {
            /* move train backward */
            mbta.moveTrainBackward(this.t, s1);
          }
  
          /* starts moving train forward */
          if (mbta.trainBackwardStations.get(this.t).size() == 1)
          {
            mbta.moveTrainBackward(this.t, s2);
            mbta.trainAndStationsKVP.put(this.t, mbta.trainForwardStations.get(t));
            mbta.isTrainMovingForward = true;
          }
        }
      }
    }
    else
    {
      throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train {" + this.t.toString() + "} does not exist.");
    }
  }
}
