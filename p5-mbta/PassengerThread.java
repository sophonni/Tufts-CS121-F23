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
                System.out.println("Here");
                Passenger passenger = Passenger.make(this.passengerName);
                Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
                int currentIndex = this.mbta.passengerAndStationsKVP.get(passenger).indexOf(passInitStation);
                Station passNxtStation = null;
                if (mbta.passengerAndStationsKVP.get(passenger).size() > 1)
                {
                    passNxtStation = mbta.passengerAndStationsKVP.get(passenger).get(currentIndex + 1);
                }
    
                Station trainCurrStation = null;
                
    
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
    
                Lock passNxtStaLck = null;
                Condition passNxtStaCondition = null;
                if (passNxtStation != null)
                {
                    /* get lock and condition of passNextStation */
                    Map<Lock, Condition> lckAndCondPassNxtStaKVP = this.mbta.staionLockAndConditionKVP.get(passNxtStation);
                    if (lckAndCondPassNxtStaKVP != null)
                    {
                        for (Lock l : lckAndCondPassNxtStaKVP.keySet())
                        {
                            passNxtStaLck = l;
                            passNxtStaCondition = lckAndCondPassNxtStaKVP.get(passNxtStaLck);
                            break;
                        }
                    }
                }
                
                if ((passCurrStaLck != null) && (passCurrStaCondition != null))
                {
                    //System.out.println("Before lock pass curr");
                    passCurrStaLck.lock();
                    //System.out.println("After lock pass curr");
                    Train trainToGetOn = null;
                    for (Train t : mbta.trainAndStationsKVP.keySet())
                    {
                        if (mbta.trainAndStationsKVP.get(t).contains(passInitStation) && mbta.trainAndStationsKVP.get(t).contains(passNxtStation))
                        {
                            // int indxSameAsPassInit = mbta.trainAndStationsKVP.get(t).indexOf(passInitStation);
                            // Station trainSameAsInit = mbta.trainAndStationsKVP.get(t).get(indxSameAsPassInit);
                            // Station trainSameAsNxt = mbta.trainAndStationsKVP.get(t).get(indxSameAsPassInit + 1);
                            // // System.out.println("Train Curr: " + trainSameAsInit);
                            // // System.out.println("Train nxt: " + trainSameAsNxt);
                            // // System.out.println("This pass: " + passengerName + " currStation: " + passInitStation + " nxtStation: " + passNxtStation);
                            
                            // if (trainSameAsNxt.equals(passNxtStation))
                            // {
                                trainToGetOn = t;
                                break;
                            //}
                        }
                        // Station trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
                        // int trainCurrStationIdx = mbta.trainAndStationsKVP.get(t).indexOf(trainCurrStation);
                        // Station trainNxtStation = mbta.trainAndStationsKVP.get(t).get(trainCurrStationIdx + 1);
                    }                    
                    while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passInitStation))
                    {
                        System.out.println("Waiting to BOARD");
                        passCurrStaCondition.await();
                        //System.out.println("After wait BOARD");
                        break;
                    }

                    try
                    {
                        mbta.boardPass(mbta, passenger, passInitStation, trainToGetOn);
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                        System.out.println("Exception: " + ex);
                    }
                    this.log.passenger_boards(passenger, trainToGetOn, passInitStation);
                    //System.out.println("After board");
                    passCurrStaCondition.signalAll();

                    //System.out.println("Before unlock pass curr");
                    passCurrStaLck.unlock();
                    //System.out.println("After unlock pass curr");
                    
                    
                    //System.out.println("Before lock pass nxt");
                    passNxtStaLck.lock();
                    //System.out.println("After lock pass nxt");
                    while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxtStation))
                    {
                        //System.out.println("Wait to DEBOARD");
                        passNxtStaCondition.await();
                        //System.out.println("After wait DEBOARD");
                        break;
                    }

                    //System.out.println("Before DEBOARD");
                    mbta.deboardPass(mbta, passenger, passNxtStation, trainToGetOn);
                    this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
                    // System.out.println("Pass Journey: " + mbta.passengerAndStationsKVP.get(passenger));
                    // while (!mbta.passengerAndStationsKVP.get(passenger).isEmpty())
                    // {
                    //     System.out.println("Pass journey not compelte")
                    //     passNxtStaCondition.await();
                    // }
                    //System.out.println("After DEBOARD");

                    passNxtStaCondition.signalAll();
                    passNxtStaLck.unlock();
                    passCurrStaCondition.signalAll();
                    passCurrStaLck.unlock();
                }
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }
    }
}
