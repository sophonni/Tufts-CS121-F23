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
            int currentIndex = this.mbta.passengerAndStationsKVP.get(passenger).indexOf(passInitStation);
            Station passNxtStation = mbta.passengerAndStationsKVP.get(passenger).get(currentIndex + 1);

            Station trainCurrStation = null;
            Train trainToGetOn  = null;
            

            /* get lock and condition of passCurrStation */
            Map<Lock, Condition> lckAndCondPassCurrStaKVP = this.mbta.staionLockAndConditionKVP.get(passInitStation);
            Lock passCurrStaLck = null;
            Condition passCurrStaCondition = null;
            if (lckAndCondPassCurrStaKVP != null)
            {
                for (Lock l : lckAndCondPassCurrStaKVP.keySet())
                {
                    passCurrStaLck = l;
                    passCurrStaCondition = lckAndCondPassCurrStaKVP.get(passCurrStaLck);
                    break;
                }
            }

            /* get lock and condition of passNextStation */
            Map<Lock, Condition> lckAndCondPassNxtStaKVP = this.mbta.staionLockAndConditionKVP.get(passNxtStation);
            Lock passNxtStaLck = null;
            Condition passNxtStaCondition = null;
            if (lckAndCondPassNxtStaKVP != null)
            {
                for (Lock l : lckAndCondPassNxtStaKVP.keySet())
                {
                    passNxtStaLck = l;
                    passNxtStaCondition = lckAndCondPassNxtStaKVP.get(passNxtStaLck);
                    break;
                }
            }
            

            if ((passCurrStaLck != null) && (passCurrStaCondition != null))
            {
                passCurrStaLck.lock();
                for (Train t : mbta.trainAndStationsKVP.keySet())
                {
                    if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
                    {
                        trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
                        trainToGetOn = t;

                        synchronized(mbta)
                        {
                            this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
                            mbta.boardPass(mbta, passenger, trainCurrStation, trainToGetOn);
                            System.out.println("After Board at: " + trainCurrStation);
                            // passCurrStaCondition.signalAll();
                            // passCurrStaLck.unlock();
                        }
                        break;
                    }
                    trainToGetOn = null;
                }
                
                passNxtStaLck.lock();
                if (trainToGetOn != null)
                {
                    while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxtStation))
                    {
                        try
                        {
                            System.out.println("Wait to get off");
                            passNxtStaCondition.await();
                        }
                        catch (InterruptedException ie)
                        {
                            throw new RuntimeException(ie);
                        }
                    }

                    synchronized(mbta)
                    {
                        //System.out.println("About to deboard at: " + passNxtStation);
                        //passNxtStaLck.lock();
                        this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
                        mbta.deboardPass(mbta, passenger, passNxtStation, trainToGetOn);
                        System.out.println("After deboard at: " + passNxtStation);
                        //passNxtStaCondition.signalAll();
                        //passNxtStaLck.unlock();
                    }
                }
                // passNxtStaCondition.signalAll();
                // passCurrStaLck.unlock();


                // while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passInitStation))
                // {
                //     try
                //     {
                //         passCurrStaCondition.await();
                //     }
                //     catch (InterruptedException ie)
                //     {
                //         throw new RuntimeException(ie);
                //     }
                // }

                // for (Train t : mbta.trainAndStationsKVP.keySet())
                // {
                //     if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passNxtStation))
                //     {
                //         passNxtStation = mbta.trainAndStationsKVP.get(t).getFirst();
                //         trainToGetOn = t;

                //         synchronized(mbta)
                //         {
                //             System.out.println("Found train at next station");
                //             this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
                //             mbta.deboardPass(mbta, passenger, passNxtStation, trainToGetOn);
                //         }
                //         // passNxtStaCondition.signalAll();
                //         // passNxtStaLck.unlock();
                //         break;
                //     }
                // }
                // while (trainToGetOn == null)
                // {
                //     try
                //     {
                //         passNxtStaCondition.await();
                //     }
                //     catch (InterruptedException ie)
                //     {
                //         throw new RuntimeException(ie);
                //     }
                // }
                passCurrStaCondition.signalAll();
                passCurrStaLck.lock();
            }
        }
    }
}
