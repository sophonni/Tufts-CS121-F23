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
    Map<String, LinkedList<String>> trainLine = mbta.trainLine;
    LinkedList<String> lineStations = trainLine.get(this.t.toString());

    /* ensure that the two stations exist in the line */
    if (!lineStations.contains(this.s1.toString()) || !lineStations.contains(this.s2.toString()))
    {
      throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train can't move from station {" + s1.toString() + "} to station {" + s2.toString() + "}.");
    }
    else
    {
      if (mbta.trainBackwardStations.get(t.toString()) == null || (mbta.trainBackwardStations.get(t.toString()).size() != mbta.trainLine.get(t.toString()).size()))
      {
        System.out.println("Moving Forward");
        int s1Index = lineStations.indexOf(this.s1.toString());
        int s2Index = lineStations.indexOf(this.s2.toString());
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
        if (mbta.trainForwardStations.get(this.t.toString()).size() == 1)
        {
          mbta.moveTrainForward(this.t, s2);
          mbta.trainLine.put(this.t.toString(), mbta.trainBackwardStations.get(t.toString()));
        }
      }
      else
      {
        System.out.println("Moving Backward");
        int s1Index = lineStations.indexOf(this.s1.toString());
        int s2Index = lineStations.indexOf(this.s2.toString());
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
        if (mbta.trainBackwardStations.get(this.t.toString()).size() == 1)
        {
          mbta.moveTrainBackward(this.t, s2);
          mbta.trainLine.put(this.t.toString(), mbta.trainForwardStations.get(t.toString()));
        }
      }
    }
  }
}
