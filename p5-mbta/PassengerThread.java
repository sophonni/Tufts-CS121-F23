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
        try
        {
            while (true) {
                /* each passenger will take turns getting the current station station lock
                 * have 1 lock per station
                */
                Passenger passenger = Passenger.make(this.passengerName);
                Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
                int currentIndex = this.mbta.passengerAndStationsKVP.get(passenger).indexOf(passInitStation);
                Station passNxtStation = null;
                if (mbta.passengerAndStationsKVP.get(passenger).size() > 1)
                {
                    passNxtStation = mbta.passengerAndStationsKVP.get(passenger).get(currentIndex + 1);
                }
    
                Station trainCurrStation = null;

                /* get lock and condition of current station */
                Map<Lock, Condition> lckAndCondPassCurrStaKVP = mbta.staionLockAndConditionKVP.get(passInitStation);
                Lock passCurrStaLck = (Lock) lckAndCondPassCurrStaKVP.keySet().toArray()[0];
                Condition passCurrStaCondition = (Condition) lckAndCondPassCurrStaKVP.values().toArray()[0];

                /* get lock and condition of next station */
                Map<Lock, Condition> lckAndCondPassNxtStaKVP = mbta.staionLockAndConditionKVP.get(passNxtStation);
                Lock passNxtStaLck = (Lock) lckAndCondPassNxtStaKVP.keySet().toArray()[0];
                Condition passNxtStaCondition = (Condition) lckAndCondPassNxtStaKVP.values().toArray()[0];

                Train trainToGetOn = null;
                passCurrStaLck.lock();
                // System.out.println("Pass Curr: " + passInitStation);
                // System.out.println("Pass Nxt: " + passNxtStation);
                for (Train t : mbta.trainAndStationsKVP.keySet())
                {
                    if (mbta.trainAndStationsKVP.get(t).contains(passInitStation) && mbta.trainAndStationsKVP.get(t).contains(passNxtStation))
                    {
                        trainToGetOn = t;
                        break;
                    }
                }                    
                while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passInitStation))
                {
                    passCurrStaCondition.await();
                }
                mbta.boardPass(mbta, passenger, passInitStation, trainToGetOn);
                this.log.passenger_boards(passenger, trainToGetOn, passInitStation);
                passCurrStaCondition.signalAll();
                passCurrStaLck.unlock();

                passNxtStaLck.lock();
                while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxtStation))
                {
                    passNxtStaCondition.await();
                    break;
                }

                mbta.deboardPass(mbta, passenger, passNxtStation, trainToGetOn);
                this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
                passNxtStaCondition.signalAll();
                passNxtStaLck.unlock();
                if (mbta.passengerAndStationsKVP.get(passenger).isEmpty())
                {
                    return;
                }
                // passCurrStaCondition.signalAll();
                // passCurrStaLck.unlock();
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
    }
}
