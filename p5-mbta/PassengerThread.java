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
            mbta.passLock.lock(); // Acquire passLock first
    
            try {
                Passenger passenger = Passenger.make(this.passengerName);
                Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
    
                for (Train t : mbta.trainAndStationsKVP.keySet()) {
                    Station trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();

                    mbta.trainLock.lock();
                    while (!trainCurrStation.equals(passInitStation))
                    {
                        System.out.println("Wait to get ON");
                        try
                        {
                            mbta.trainCondition.await();
                        }
                        catch (InterruptedException ie)
                        {
                            throw new RuntimeException(ie);
                        }
                        finally
                        {
                            mbta.trainLock.unlock(); // Release trainLock after await
                        }
                    }
    
                    if (trainCurrStation.equals(passInitStation)) {
                        this.log.passenger_boards(passenger, t, passInitStation);
                        BoardEvent boardEvent = new BoardEvent(passenger, t, passInitStation);
                        boardEvent.replayAndCheck(mbta);
    
                        Station passNxtStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();

                        mbta.trainLock.lock();
                        while (!trainCurrStation.equals(passNxtStation))
                        {
                            System.out.println("Wait to get OFF");
                            try
                            {
                                mbta.trainCondition.await();
                                
                            }
                            catch (InterruptedException ie)
                            {
                                throw new RuntimeException(ie);
                            }
                            finally
                            {
                                mbta.trainLock.unlock();
                            }
                        }
    
                        if (trainCurrStation.equals(passNxtStation)) {
                            this.log.passenger_deboards(passenger, t, passNxtStation);
                            DeboardEvent deboardEvent = new DeboardEvent(passenger, t, passNxtStation);
                            deboardEvent.replayAndCheck(mbta);
                        }
                    }
                }
                mbta.trainCondition.signalAll(); // Signal other threads waiting on train movement
            } finally {
                mbta.passLock.unlock(); // Release passLock
            }
        }

    //     while (true)
    //     {
    //         this.mbta.trainLock.lock(); // Lock 1
    //         this.mbta.passLock.lock(); // Lock 2

    //         Passenger p = Passenger.make(this.passengerName);
    //         Station passInitStation = mbta.passengerAndStationsKVP.get(p).getFirst();

    //         for (Train t : mbta.trainAndStationsKVP.keySet())
    //         {
    //             Station trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
    //             System.out.println("Train {"+ t.toString() + "} currStation station: " + trainCurrStation);
    //             while (!trainCurrStation.equals(passInitStation))
    //             {
    //                 try
    //                 {
    //                     this.mbta.passCondition.await();
    //                 }
    //                 catch (InterruptedException ie)
    //                 {
    //                     throw new RuntimeException();
    //                 }
    //             }
                
    //             // this.mbta.passLock.lock();
    //             if (trainCurrStation.equals(passInitStation))
    //             {
    //                 this.log.passenger_boards(p, t, passInitStation);
    //                 BoardEvent boardEvent = new BoardEvent(p, t, passInitStation);
    //                 boardEvent.replayAndCheck(mbta);
                    
                    
    //                 Station passNxtStation = mbta.passengerAndStationsKVP.get(p).getFirst();
    //                 System.out.println("Train {"+ t.toString() + "} next station: " + mbta.trainAndStationsKVP.get(t).getFirst());
    //                 System.out.println("Passenger next station: " + passNxtStation);
    //                 while (!trainCurrStation.equals(passNxtStation))
    //                 {
    //                     try
    //                     {
    //                         this.mbta.passCondition.await();

    //                     }
    //                     catch (InterruptedException ie)
    //                     {
    //                         throw new RuntimeException(ie);
    //                     }
    //                 }
                    
    //                 // this.mbta.passLock.lock();
    //                 if (trainCurrStation.equals(passNxtStation))
    //                 {
    //                     System.out.println("Next station: " + passNxtStation);
    //                     // this.mbta.passLock.lock();
    //                     this.log.passenger_deboards(p, t, passNxtStation);
    //                     DeboardEvent deboardEvent = new DeboardEvent(p, t, passNxtStation);
    //                     deboardEvent.replayAndCheck(mbta);
    //                     // this.mbta.passLock.unlock();
    //                 }
    //                 // this.mbta.passLock.unlock();
    //             }
    //         }

    //         this.mbta.passLock.unlock(); // Release Lock 2
    //         this.mbta.trainLock.unlock(); // Release Lock 1
    //     }
    // }
    }
}
