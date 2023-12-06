import java.util.*;

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
        /*
            * get first location of passenger journey
            * iterate thorugh trainsToStation kvp
            * if first of currTrain is == first location of passenger journey
            * board passenger
            * have passenger wait on the train
            * debaord passenger when arraive at the next station of the passenger's journey
        */

        while (true) {
            /* each passenger will take turns getting the current station station lock
             * have 1 lock per station
            */

            

            // mbta.stationLock.lock();;
    
            // Passenger passenger = Passenger.make(this.passengerName);
            // Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
            // Station trainCurrStation = null;
            // Train trainToGetOn  = null;
            
            // /* get the train where its current station = passenger initial station */
            // for (Train t : mbta.trainAndStationsKVP.keySet()) {
            //     if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
            //     {
            //         trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
            //         trainToGetOn = t;
            //         break;
            //     }
            // }

            // if (trainCurrStation != null)
            // {
            //     while (!trainCurrStation.equals(passInitStation))
            //     {
            //         /* "temporarily" release the train lock to have the train class
            //         lock onto it in order to move the train forward */
            //         System.out.println("Wait to get ON");
            //         try
            //         {
            //             mbta.stationCondition.await();
            //         }
            //         catch (InterruptedException ie)
            //         {
            //             throw new RuntimeException(ie);
            //         }
            //     }
    
            //     /* train current station matches with the passenger's initial station of their journey */
            //     if (trainCurrStation.equals(passInitStation))
            //     {
            //         System.out.println("Board: ");
            //         for (Passenger p : mbta.stationAndWaitingPassenger.get(passInitStation))
            //         {
            //             this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
            //             BoardEvent boardEvent = new BoardEvent(passenger, trainToGetOn, trainCurrStation);
            //             boardEvent.replayAndCheck(mbta);
            //         }
            //     }
            //     mbta.stationCondition.signalAll();
            //     mbta.stationLock.unlock();
            // }
        }
    }
}
