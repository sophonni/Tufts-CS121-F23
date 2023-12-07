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
    mbta.moveTrain(mbta, s1, s2, t);
    // System.out.println("Move#: " + this.toString());
    // System.out.println("Move#TrainAndStations: " + mbta.trainAndStationsKVP);
    // System.out.println("Move#PassengersAndJourney: " + mbta.passengerAndStationsKVP);
    // System.out.println("Move#BoardedPassengers: " + mbta.trainToBoardedPassengers);
    // Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;

    // // System.out.println("Forward List: " + mbta.trainForwardStations);
    // // System.out.println("Backward List: " + mbta.trainBackwardStations);

    // for (Train currTrain : trainLine.keySet())
    // {
    //   if (!currTrain.equals(this.t))
    //   {
    //     if (mbta.trainAndStationsKVP.get(currTrain).getFirst().equals(s2))
    //     {
    //       throw new IllegalArgumentException("Error in MoveEvent#replayAndCheck: Train {" + this.t.toString() + "} can't move to station {" + this.s2.toString() + "} b/c train {" + currTrain.toString() + "} is at station {" + this.s2.toString() + "}.");
    //     }
    //   }
    // }

    // /* ensure that the train exist */
    // if (trainLine.containsKey(this.t))
    // {
    //   LinkedList<Station> lineStations = trainLine.get(this.t);
  
    //   /* ensure that the two stations exist in the line */
    //   if (!lineStations.contains(this.s1) || !lineStations.contains(this.s2) || this.s1 == null || this.s2 == null)
    //   {
    //     throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train can't move from station {" + s1.toString() + "} to station {" + s2.toString() + "}.");
    //   }
    //   else
    //   {
    //     if (!mbta.trainAndStationsKVP.get(t).getFirst().equals(this.s1))
    //     {
    //       throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingForward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "} because current station is {" + mbta.trainAndStationsKVP.get(this.t).getFirst().toString() + "}.");
    //     }
    //     else if (mbta.trainForwardStations.get(t) != null && mbta.trainAndIfItsMovingForward.get(this.t))
    //     {
    //       // System.out.println("MOVING FORWARD");
    //       mbta.isTrainMovingForward = true;
    //       mbta.trainAndIfItsMovingForward.put(this.t, true);
    //       int s1Index = lineStations.indexOf(this.s1);
    //       int s2Index = lineStations.indexOf(this.s2);
    //       /* ensure that the two stations are adjacent */
    //       if ((s2Index - s1Index) != 1)
    //       {
    //         throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingForward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
    //       }
    //       else
    //       {
    //         // System.out.println("Moving Forward to: " + this.s2);
    //         /* move train forward */
    //         mbta.moveTrainForward(this.t, s1);
    //       }
    //       /* starts moving train backward */
    //       if (mbta.trainForwardStations.get(this.t).size() == 1)
    //       {
    //         System.out.println("Start moving backward");
    //         mbta.moveTrainForward(this.t, s2);
    //         mbta.trainAndStationsKVP.put(this.t, mbta.trainBackwardStations.get(t));
    //         mbta.isTrainMovingForward = false;
    //         mbta.trainAndIfItsMovingForward.put(this.t, false);
    //       }
    //     }
    //     else if (mbta.trainBackwardStations.get(t) != null && !mbta.trainAndIfItsMovingForward.get(this.t))
    //     {
    //       // System.out.println("MOVING BACKWARD");
    //       mbta.isTrainMovingForward = false;
    //       mbta.trainAndIfItsMovingForward.put(this.t, false);
    //       int s1Index = lineStations.indexOf(this.s1);
    //       int s2Index = lineStations.indexOf(this.s2);
    //       /* ensure that the two stations are adjacent */
    //       if ((s2Index - s1Index) != 1)
    //       {
    //         // System.out.println("HERE: 3");
    //         throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingBackward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
    //       }
    //       else
    //       {
    //         /* move train backward */
    //         mbta.moveTrainBackward(this.t, s1);
    //       }
  
    //       /* starts moving train forward */
    //       if (mbta.trainBackwardStations.get(this.t).size() == 1)
    //       {
    //         System.out.println("Start moving forward");
    //         mbta.moveTrainBackward(this.t, s2);
    //         mbta.trainAndStationsKVP.put(this.t, mbta.trainForwardStations.get(t));
    //         mbta.isTrainMovingForward = true;
    //         mbta.trainAndIfItsMovingForward.put(this.t, true);
    //       }
    //     }
    //   }
    // }
    // else
    // {
    //   throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train {" + this.t.toString() + "} does not exist.");
    // }
  }
}
