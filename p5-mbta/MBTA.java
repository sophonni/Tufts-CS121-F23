import com.google.gson.*;
import java.io.*;
import java.time.format.SignStyle;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MBTA {
  public Map<Train, LinkedList<Station>> trainAndStationsKVP = new HashMap<>();
  public Map<Passenger, LinkedList<Station>> passengerAndStationsKVP = new HashMap<>();

  /* first element of the LinkedList represents the current station the train is at */
  public Map<Train, LinkedList<Station>> trainForwardStations = new HashMap<>();
  public Map<Train, LinkedList<Station>> trainBackwardStations = new HashMap<>();

  /* use to keep track on passengers and the train they boarded */
  public Map<Train, LinkedList<Passenger>> trainToBoardedPassengers = new HashMap<>();

  /* original layout of lines and journeys from config file */
  public Map<Train, LinkedList<Station>> originalTrainAndStationKVP = new HashMap<>();

  public boolean isTrainMovingForward = true;

  /* use to keep track on whether or not trains have passengers to drop off */
  public Map<Train, Boolean> trainAndIfPassengerHasBeenBoarded = new HashMap<>();

  public Map<Train, Boolean> trainAndIfItsMovingForward = new HashMap<>();

  public Map<Station, Map<Lock, Condition>> staionLockAndConditionKVP = new HashMap<>();
  public Lock stationLock = new ReentrantLock();
  public Condition stationCondition = stationLock.newCondition();

  // public Lock passLock = new ReentrantLock();
  // public Condition passCondition = passLock.newCondition();

  public Map<Station, List<Passenger>> stationAndWaitingPassenger = new HashMap<>();
 

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
      /* make train and stations from given parameters and store */
      Train train = Train.make(name);
      LinkedList<Station> trainStationList = new LinkedList<>();
      for (String s : stations)
      {
        Station newStation = Station.make(s);
        trainStationList.add(newStation);
      }
      this.trainForwardStations.put(train, trainStationList);
      this.trainAndStationsKVP.put(train, trainStationList);

      /* keep a list of original order of the stations for each train */
      this.originalTrainAndStationKVP.put(train, new LinkedList<>(trainStationList));

      for(Train t : this.originalTrainAndStationKVP.keySet())
      {
        this.trainAndIfItsMovingForward.put(train, true);
      }
    }
  }

  // Adds a new planned journey to the simulation
  public void addJourney(String name, List<String> stations) {
    /* make passenger and stations from given parameters and store */
    Passenger passenger = Passenger.make(name);
    LinkedList<Station> passengerStationList = new LinkedList<>();
    for (String s : stations)
    {
      Station passengerStation = Station.make(s);
      passengerStationList.add(passengerStation);
    }
    this.passengerAndStationsKVP.put(passenger, passengerStationList);

    if (this.stationAndWaitingPassenger.get(passengerStationList.getFirst()) == null)
    {
      this.stationAndWaitingPassenger.put(passengerStationList.getFirst(), new LinkedList<>());
      this.stationAndWaitingPassenger.get(passengerStationList.getFirst()).add(passenger);
    }
    else
    {
      this.stationAndWaitingPassenger.get(passengerStationList.getFirst()).add(passenger);
    }
  }

  // Return normally if initial simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkStart() {
    for (Train t : this.originalTrainAndStationKVP.keySet())
    {
      LinkedList<Station> initialStationsLayout = this.originalTrainAndStationKVP.get(t);
      Station firstStation = initialStationsLayout.getFirst();
      
      LinkedList<Station> movingForwardStartStations = this.trainForwardStations.get(t);
      Station startStation = movingForwardStartStations.getFirst();

      if (!firstStation.equals(startStation))
      {
        throw new IllegalArgumentException("Error in MBTA#checkStart: First station of train {" + t.toString() +"} is {" + firstStation.toString() + "} while train starts at station {" + startStation.toString() + "}.");
      }
    }
    return;
  }

  // Return normally if final simulation conditions are satisfied, otherwise
  // raises an exception
  public void checkEnd() {

    /*
     * The simulation ends when all passengers have arrived at their 
     * final stops. It is an error for the simulation to end early. 
     * It is fine if trains run a bit past the point where all passengers have finished their journeys.
     */
    for (Train t : this.originalTrainAndStationKVP.keySet())
    {
      System.out.println("Train to Boarded Pass: " + this.trainToBoardedPassengers);

      if(this.trainToBoardedPassengers.get(t) == null || this.trainToBoardedPassengers.get(t).isEmpty())
      {
        System.out.println("YES EMPTY");
      }
      else
      {
        throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> train {" + t.toString() + "} ends before all passengers have been deboarded.");
      }
    }

    /* ensure that all passengers completed their journey */
    for (Passenger p : this.passengerAndStationsKVP.keySet())
    {
      if (!this.passengerAndStationsKVP.get(p).isEmpty())
      {
        throw new IllegalArgumentException("Error in MBTA#checkEnd: Invalid Ending --> Passenger {" + p.toString() + "} with journey {" + this.passengerAndStationsKVP.get(p) + "} needs to be drop off.");
      }
    }
    return;
  }

  // reset to an empty simulation
  public void reset() {
    this.trainAndStationsKVP.clear();
    this.passengerAndStationsKVP.clear();
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
      throw new IllegalArgumentException("Error in {loadConfig}: Unable to parse JSON file.");
    }

    /* store trains and their initial station */
    for (Train t : this.trainAndStationsKVP.keySet())
    {
      this.trainForwardStations.put(t, this.trainAndStationsKVP.get(t));
    }

    for (Train t : this.trainAndStationsKVP.keySet())
    {
      LinkedList<Station> stationOfTrain = this.trainAndStationsKVP.get(t);

      for (Station s : stationOfTrain)
      {
        if (!this.staionLockAndConditionKVP.containsKey(s))
        {
          Lock newstationLock = new ReentrantLock();
          Condition newStationCondition = newstationLock.newCondition();
  
          Map<Lock, Condition> lockToConditionMap = new HashMap<>();
          lockToConditionMap.put(newstationLock, newStationCondition);
          this.staionLockAndConditionKVP.put(s, lockToConditionMap);
        }
      }
    }
  }
  synchronized public void moveTrain(MBTA mbta, Station s1, Station s2, Train t)
  {
    Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;

    // System.out.println("Forward List: " + mbta.trainForwardStations);
    // System.out.println("Backward List: " + mbta.trainBackwardStations);

    for (Train currTrain : trainLine.keySet())
    {
      if (!currTrain.equals(t))
      {
        if (mbta.trainAndStationsKVP.get(currTrain).getFirst().equals(s2))
        {
          throw new IllegalArgumentException("Error in MoveEvent#replayAndCheck: Train {" + t.toString() + "} can't move to station {" + s2.toString() + "} b/c train {" + currTrain.toString() + "} is at station {" + s2.toString() + "}.");
        }
      }
    }

    /* ensure that the train exist */
    if (trainLine.containsKey(t))
    {
      LinkedList<Station> lineStations = trainLine.get(t);
  
      /* ensure that the two stations exist in the line */
      if (!lineStations.contains(s1) || !lineStations.contains(s2) || s1 == null || s2 == null)
      {
        throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train can't move from station {" + s1.toString() + "} to station {" + s2.toString() + "}.");
      }
      else
      {
        if (!mbta.trainAndStationsKVP.get(t).getFirst().equals(s1))
        {
          throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingForward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "} because current station is {" + mbta.trainAndStationsKVP.get(t).getFirst().toString() + "}.");
        }
        else if (mbta.trainForwardStations.get(t) != null && mbta.trainAndIfItsMovingForward.get(t))
        {
          // System.out.println("MOVING FORWARD");
          mbta.isTrainMovingForward = true;
          mbta.trainAndIfItsMovingForward.put(t, true);
          int s1Index = lineStations.indexOf(s1);
          int s2Index = lineStations.indexOf(s2);
          /* ensure that the two stations are adjacent */
          if ((s2Index - s1Index) != 1)
          {
            throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingForward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
          }
          else
          {
            // System.out.println("Moving Forward to: " + s2);
            /* move train forward */
            mbta.moveTrainForward(t, s1);
          }
          /* starts moving train backward */
          if (mbta.trainForwardStations.get(t).size() == 1)
          {
            System.out.println("Start moving backward");
            mbta.moveTrainForward(t, s2);
            mbta.trainAndStationsKVP.put(t, mbta.trainBackwardStations.get(t));
            mbta.isTrainMovingForward = false;
            mbta.trainAndIfItsMovingForward.put(t, false);
          }
        }
        else if (mbta.trainBackwardStations.get(t) != null && !mbta.trainAndIfItsMovingForward.get(t))
        {
          // System.out.println("MOVING BACKWARD");
          mbta.isTrainMovingForward = false;
          mbta.trainAndIfItsMovingForward.put(t, false);
          int s1Index = lineStations.indexOf(s1);
          int s2Index = lineStations.indexOf(s2);
          /* ensure that the two stations are adjacent */
          if ((s2Index - s1Index) != 1)
          {
            // System.out.println("HERE: 3");
            throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck#movingBackward}: Train can't move from station: {" + s1.toString() + "} to station {" + s2.toString() + "}.");
          }
          else
          {
            /* move train backward */
            mbta.moveTrainBackward(t, s1);
          }
  
          /* starts moving train forward */
          if (mbta.trainBackwardStations.get(t).size() == 1)
          {
            System.out.println("Start moving forward");
            mbta.moveTrainBackward(t, s2);
            mbta.trainAndStationsKVP.put(t, mbta.trainForwardStations.get(t));
            mbta.isTrainMovingForward = true;
            mbta.trainAndIfItsMovingForward.put(t, true);
          }
        }
      }
    }
    else
    {
      throw new IllegalArgumentException("Error in {MoveEvent#replayAndCheck}: Train {" + t.toString() + "} does not exist.");
    }
  }
  synchronized public void boardPass(MBTA mbta, Passenger p, Station s, Train t)
  {
    Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;
    /* ensure that the train exist */
    if (trainLine.containsKey(t) || t != null)
    {
      LinkedList<Station> lineStations = trainLine.get(t);
      /* ensure the train line has stations */
      if (lineStations != null)
      {
        /* ensure that the train stations contains the station the passenger is boarding from */
        if (lineStations.contains(s))
        {
          /* ensure that the current station of the train is the station the passenger is boarding from */
          if (lineStations.getFirst().equals(s))
          {
            LinkedList<Passenger> boardPassengers = mbta.trainToBoardedPassengers.get(t);

            /* create a new list to store boarded passenger if there doesn't already exist a list */
            if (boardPassengers == null)
            {
              boardPassengers = new LinkedList<>();
              LinkedList<Station> givenPassengerJourney = mbta.passengerAndStationsKVP.get(p);
              // System.out.println("Board#Pass is: " + p);
              // System.out.println("Board#Pass and Station: " + givenPassengerJourney);
              /* ensure that the journey to the given station for the given passenger has been initialize */
              if (givenPassengerJourney.contains(s))
              {
                boardPassengers.add(p);

                if (mbta.stationAndWaitingPassenger.get(s) != null)
                {
                  mbta.stationAndWaitingPassenger.get(s).remove(p);
                }
                
                // System.out.println("Remove sta: " + s);
                /* remove the station they boarded from, from their journey */
                givenPassengerJourney.remove(s);
                mbta.trainToBoardedPassengers.put(t, boardPassengers);
                mbta.trainAndIfPassengerHasBeenBoarded.put(t, true);
                //System.out.println("Pass Station List after board: " + mbta.stationAndWaitingPassenger.get(s));
              }
              else
              {
                throw new IllegalArgumentException("111111 Error in {BoardEvent#replayAndCheck}: Journey to station {" + s + "} for passenger {" + p.toString() + "} has not yet been initialized.");
              }
            }
            else
            {
              // /* ensure that all passengers from all station that is willing to board the train have uniqe name e.g if John board red train from station1, John cannot board red train from any other stations */
                LinkedList<Station> givenPassengerJourney = mbta.passengerAndStationsKVP.get(p);
                // System.out.println("Board#Has Pass: " + p);
                // System.out.println("Board#Has Pass and Station: " + givenPassengerJourney);
                /* ensure that the journey to the given station for the given passenger has been initialize */
                if (givenPassengerJourney.contains(s))
                {
                  if (mbta.stationAndWaitingPassenger.get(s) != null)
                  {
                    mbta.stationAndWaitingPassenger.get(s).remove(p);
                  }
                  boardPassengers.add(p);
                  // mbta.stationAndWaitingPassenger.get(s).remove(p);
                  givenPassengerJourney.remove(s);
                  mbta.trainToBoardedPassengers.put(t, boardPassengers);
                  mbta.trainAndIfPassengerHasBeenBoarded.put(t, true);
                }
                else
                {
                  throw new IllegalArgumentException("222222222 Error in {BoardEvent#replayAndCheck}: Journey to station {" + s + "} for passenger {" + p.toString() + "} has not yet been initialized.");
                }
            }
          }
          else
          {
            throw new IllegalArgumentException("Error in {BoardEvent#replayAndCheck}: Unable to board from station {" +s.toString() + "} as current station of the train is {" + lineStations.getFirst() + "}.");
          }
        }
        else
        {
          throw new IllegalArgumentException("Error in {BoardEvent#replayAndCheck}: Train {" + t + "} does not contains the station {" + s.toString() + "}.");
        }
      }
      else
      {
        throw new IllegalArgumentException("Error in {BoardEvent#replayAndCheck}: Unable to board onto Train {" + t.toString() + "}.");
      }
    }
    else
    {
      throw new IllegalArgumentException("Error in {BoardEvent#replayAndCheck}: Train {" + t.toString() + "} does not exist.");
    }
  }
  synchronized public void deboardPass(MBTA mbta, Passenger p, Station s, Train t)
  {
    Map<Train, LinkedList<Station>> trainLine = mbta.trainAndStationsKVP;
    /* ensure that the train exist */
    if (trainLine.containsKey(t) || t != null)
    {
      LinkedList<Station> lineStations = trainLine.get(t);
      /* ensure the train line has stations */
      if (lineStations != null)
      {
        /* ensure that the train stations contains the station the passenger is deboarding at */
        if (lineStations.contains(s))
        {
          /* ensure that the current station of the train matches with the station the passenger want to get off at */
          if (lineStations.getFirst().equals(s))
          {
            LinkedList<Passenger> boardPassengers = mbta.trainToBoardedPassengers.get(t);

            /* ensure that there are passengers that have boarded the train */
            if (boardPassengers != null)
            {
              /* ensure the given passenger has boarded the given train */
              if (!boardPassengers.contains(p))
              {
                throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Passenger {" + p.toString() + "} has not yet board the train {" + t.toString() + "}.");
              }
              else
              {
                LinkedList<Station> givenPassengerJourney = mbta.passengerAndStationsKVP.get(p);
                // System.out.println("DeBoard#Pass is: " + p);
                // System.out.println("DeBoard#Pass and Station: " + givenPassengerJourney);
                // System.out.println("Pass Sta: " + mbta.passengerAndStationsKVP);
                /* ensure that the journey to the given station for the given passenger has been initialize */
                if (givenPassengerJourney.contains(s))
                {
                  if (mbta.passengerAndStationsKVP.get(p).size() == 1)
                  {
                    givenPassengerJourney.remove(s);
                  }
                  /* remove passenger from the map of boarded passenger if there journey is complete */
                  if (mbta.trainToBoardedPassengers.get(t) == null || mbta.passengerAndStationsKVP.get(p).isEmpty())
                  {
                    // System.out.println("Remove Pass: " + p);
                    boardPassengers.remove(boardPassengers.indexOf(p));
                    for(Train train : mbta.trainToBoardedPassengers.keySet())
                    {
                      if (mbta.trainToBoardedPassengers.get(train).contains(p))
                      {
                        mbta.trainToBoardedPassengers.get(train).remove(p);
                      }
                    }
                  }
                  else
                  {
                    // System.out.println("Station is: " + s);
                    // System.out.println("Wait Peopl: " + mbta.stationAndWaitingPassenger.get(s));
                    if (mbta.stationAndWaitingPassenger.get(s) == null)
                    {
                      mbta.stationAndWaitingPassenger.put(s, new LinkedList<>());
                      mbta.stationAndWaitingPassenger.get(s).add(p);
                    }
                    else
                    {
                      mbta.stationAndWaitingPassenger.get(s).add(p);
                    }
                    
                  }
                  mbta.trainToBoardedPassengers.put(t, boardPassengers);
                }
                else
                {
                  throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Journey to station {" + s + "} for passenger {" + p.toString() + "} has not yet been initialized.");
                }
              }
            }
            else
            {
              throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: There doesn't exist any passengers that have boarded the train {" + t.toString() + "}.");
            }
          }
          else
          {
            throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Unable to deboard at station {" +s.toString() + "} as current station of the train is {" + lineStations.getFirst() + "}.");
          }
        }
        else
        {
          throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Train {" + t.toString() + "} does not contains the station {" + s.toString() + "}.");
        }
      }
      else
      {
        throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Unable to deboard from Train {" + t.toString() + "}.");
      }
    }
    else
    {
      throw new IllegalArgumentException("Error in {DeboardEvent#replayAndCheck}: Train {" + t.toString() + "} does not exist.");
    }
  }
  public void moveTrainForward(Train t, Station currStation)
  {
    /* remove currStation from the map for forward movement and add the front of the map for backward movement */
    LinkedList<Station> forwardStations = this.trainAndStationsKVP.get(t);
    forwardStations.remove(currStation);
    
    LinkedList<Station> backwardStations = null;
    if (!this.trainBackwardStations.containsKey(t))
    {
      backwardStations = new LinkedList<>();
    }
    else
    {
      backwardStations = this.trainBackwardStations.get(t);
    }
    backwardStations.addFirst(currStation);    
    this.trainBackwardStations.put(t, backwardStations);
  }
  
  public void moveTrainBackward(Train t, Station currStation)
  {
    /* remove currStation from the map for backward movement and add the front of the map for forward movement */
    LinkedList<Station> backwardStations = this.trainBackwardStations.get(t);
    backwardStations.remove(currStation);

    LinkedList<Station> forwardStations = null;
    if (!this.trainForwardStations.containsKey(t))
    {
      forwardStations = new LinkedList<>();
    }
    else
    {
      forwardStations = this.trainForwardStations.get(t);
    }
    forwardStations.addFirst(currStation);
    this.trainForwardStations.put(t, forwardStations);
  }

  public void PrintLines()
  {
    System.out.println("Lines: " + this.trainAndStationsKVP);
  }

  public void PrintJournies()
  {
    System.out.println("Trips: " + this.passengerAndStationsKVP);
  }
}
