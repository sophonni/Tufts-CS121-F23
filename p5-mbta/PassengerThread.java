// import java.util.*;
// import java.util.concurrent.locks.Condition;
// import java.util.concurrent.locks.Lock;

// public class PassengerThread extends Thread {
//     private MBTA mbta;
//     private String passengerName;
//     private Log log;

//     public PassengerThread(MBTA mbta, String passengerName, Log log)
//     {
//         this.mbta = mbta;
//         this.passengerName = passengerName;
//         this.log = log;
//     }

//     public void run()
//     {

//         while (true) {
//             /* each passenger will take turns getting the current station station lock
//              * have 1 lock per station
//             */
//             Passenger passenger = Passenger.make(this.passengerName);
//             Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
//             int currentIndex = this.mbta.passengerAndStationsKVP.get(passenger).indexOf(passInitStation);
//             Station passNxtStation = mbta.passengerAndStationsKVP.get(passenger).get(currentIndex + 1);

//             Station trainCurrStation = null;
//             Train trainToGetOn  = null;
            

//             Map<Lock, Condition> lckAndCondPassCurrStaKVP = this.mbta.staionLockAndConditionKVP.get(passInitStation);
//             Lock passCurrStaLck = null;
//             Condition passCurrStaCondition = null;
//             if (lckAndCondPassCurrStaKVP != null)
//             {
//                 for (Lock l : lckAndCondPassCurrStaKVP.keySet())
//                 {
//                     passCurrStaLck = l;
//                     passCurrStaCondition = lckAndCondPassCurrStaKVP.get(passCurrStaLck);
//                     break;
//                 }
//             }


//             Map<Lock, Condition> lckAndCondPassNxtStaKVP = this.mbta.staionLockAndConditionKVP.get(passNxtStation);
//             Lock passNxtStaLck = null;
//             Condition passNxtStaCondition = null;
//             if (lckAndCondPassNxtStaKVP != null)
//             {
//                 for (Lock l : lckAndCondPassNxtStaKVP.keySet())
//                 {
//                     passNxtStaLck = l;
//                     passNxtStaCondition = lckAndCondPassNxtStaKVP.get(passNxtStaLck);
//                     break;
//                 }
//             }
            
//             if ((passCurrStaLck != null) && (passCurrStaCondition != null))
//             {
//                 passCurrStaLck.lock();


//                 for (Train t : mbta.trainAndStationsKVP.keySet())
//                 {
//                     if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
//                     {
//                         trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
//                         trainToGetOn = t;
//                         break;
//                     }
//                 }

//                 while (trainToGetOn == null)
//                 {
//                     try
//                     {
//                         passCurrStaCondition.await();
//                     }
//                     catch (InterruptedException ie)
//                     {
//                         throw new RuntimeException(ie);
//                     }
//                 }
//                 System.out.println("BOARDING the train");
//                 this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
//                 BoardEvent boardEvent = new BoardEvent(passenger, trainToGetOn, passInitStation);
//                 boardEvent.replayAndCheck(mbta);
//                 passCurrStaCondition.signalAll();
//                 passCurrStaLck.unlock();

//                 passNxtStaLck.lock();
//                 for (Train t : mbta.trainAndStationsKVP.keySet())
//                 {
//                     if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passNxtStation))
//                     {
//                         trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
//                         trainToGetOn = t;
//                         break;
//                     }
//                 }
//                 while (trainToGetOn == null)
//                 {
//                     try
//                     {
//                         passNxtStaCondition.await();
//                     }
//                     catch (InterruptedException ie)
//                     {
//                         throw new RuntimeException(ie);
//                     }
//                 }
//                 this.log.passenger_deboards(passenger, trainToGetOn, trainCurrStation);
//                 DeboardEvent deboardEvent = new DeboardEvent(passenger, trainToGetOn, passNxtStation);
//                 deboardEvent.replayAndCheck(mbta);
//                 passNxtStaCondition.signalAll();
//                 passNxtStaLck.unlock();

//             }
//         }
//     }
// }
