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
        while (true)
        {
            this.mbta.passLock.lock();

            Passenger p = Passenger.make(this.passengerName);
            Station passInitStation = mbta.passengerAndStationsKVP.get(p).getFirst();

            for (Train t : mbta.trainAndStationsKVP.keySet())
            {
                Station trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
                while (!trainCurrStation.equals(passInitStation))
                {
                    System.out.println("Wait to start");
                    try
                    {
                        this.mbta.passCondition.await();
                    }
                    catch (InterruptedException ie)
                    {
                        throw new RuntimeException();
                    }
                }
                
                if (trainCurrStation.equals(passInitStation))
                {
                    this.log.passenger_boards(p, t, passInitStation);
                    
                    
                    Station passNxtStation = mbta.passengerAndStationsKVP.get(p).get(mbta.passengerAndStationsKVP.get(p).indexOf(passInitStation) + 1);
                    while (!trainCurrStation.equals(passNxtStation))
                    {
                        System.out.println("Wait to finish");
                        try
                        {
                            System.out.println("releaseing");
                            this.mbta.passCondition.await();
                        }
                        catch (InterruptedException ie)
                        {
                            throw new RuntimeException(ie);
                        }
                    }

                    if (trainCurrStation.equals(passNxtStation))
                    {
                        this.log.passenger_deboards(p, t, passNxtStation);
                    }

                }
                this.mbta.passCondition.signalAll();
                this.mbta.passLock.unlock();
            }

        }
    }
}
