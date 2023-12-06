import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class PassengerThread extends Thread {
    private MBTA mbta;
    private String passengerName;
    private Log log;

    public PassengerThread(MBTA mbta, String passengerName, Log log)
    {
        this.mbta = mbta;
        this.passengerName = passengerName;
        this.log = log;
    }

    public void run()
    {

        while (true) {
            /* each passenger will take turns getting the current station station lock
             * have 1 lock per station
            */
            Passenger passenger = Passenger.make(this.passengerName);
            Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
            Station trainCurrStation = null;
            Train trainToGetOn  = null;
            
            Map<Lock, Condition> lockAndConditionOfPassInitStation = this.mbta.staionLockAndConditionKVP.get(passInitStation);
            Lock stationLock = null;
            Condition stationCondition = null;
            if (lockAndConditionOfPassInitStation != null)
            {
                for (Lock l : lockAndConditionOfPassInitStation.keySet())
                {
                    stationLock = l;
                    stationCondition = lockAndConditionOfPassInitStation.get(stationLock);
                    break;
                }
            }
            
            if ((stationLock != null) && (stationCondition != null))
            {
                stationLock.lock();
                /* get the train where its current station = passenger initial station */
                for (Train t : mbta.trainAndStationsKVP.keySet()) {
                    if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
                    {
                        System.out.println("BOARDING the train");
                        stationLock.lock();
                        trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
                        trainToGetOn = t;

                        this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
                        BoardEvent boardEvent = new BoardEvent(passenger, trainToGetOn, passInitStation);
                        boardEvent.replayAndCheck(mbta);
                        // stationCondition.wa
                        stationCondition.signalAll();
                        stationLock.unlock();
                        System.out.println("Unloack Thread");
                        break;
                    }
                }
                
                Station passNxStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
                lockAndConditionOfPassInitStation = this.mbta.staionLockAndConditionKVP.get(passInitStation);
                if (lockAndConditionOfPassInitStation != null)
                {
                    for (Lock l : lockAndConditionOfPassInitStation.keySet())
                    {
                        stationLock = l;
                        stationCondition = lockAndConditionOfPassInitStation.get(stationLock);
                        break;
                    }
                }

                if (stationLock != null && stationCondition != null)
                {
                    // while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxStation))
                    // {
                    //     System.out.println("Waiting to DEBOARD");
                    //     try
                    //     {
                    //         stationCondition.await();
                    //     }
                    //     catch (InterruptedException ie)
                    //     {
                    //         throw new RuntimeException();
                    //     }
                    // }

                    System.out.println("HERER");
                    System.out.println("Train to get on: " + mbta.trainAndStationsKVP);
                    System.out.println("Train: " + trainToGetOn);
                    if (mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxStation))
                    {
                        stationLock.lock();
                        System.out.println("Deboard Pas");
                        this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
                        DeboardEvent deboardEvent = new DeboardEvent(passenger, trainToGetOn, passNxStation);
                        deboardEvent.replayAndCheck(mbta);
                        stationCondition.signalAll();
                        stationLock.unlock();
                    }
                    else
                    {
                        System.out.println("Waiting to DEBOARD");
                        try
                        {
                            stationCondition.await();
                        }
                        catch (InterruptedException ie)
                        {
                            throw new RuntimeException();
                        }
                    }
                }

                stationCondition.signalAll();
                stationLock.unlock();
            }
        }
    }
}
