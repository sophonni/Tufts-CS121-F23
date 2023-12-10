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
//         try
//         {
//             while (true) {
//                 /* each passenger will take turns getting the current station station lock
//                  * have 1 lock per station
//                 */
//                 Passenger passenger = Passenger.make(this.passengerName);
//                 System.out.println("Pass is: " + passenger);
//                 Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
//                 int currentIndex = this.mbta.passengerAndStationsKVP.get(passenger).indexOf(passInitStation);
//                 Station passNxtStation = null;
//                 if (mbta.passengerAndStationsKVP.get(passenger).size() > 1)
//                 {
//                     passNxtStation = mbta.passengerAndStationsKVP.get(passenger).get(currentIndex + 1);
//                 }
    
//                 Station trainCurrStation = null;
//                 Train trainToGetOn  = null;
                
    
//                 /* get lock and condition of passCurrStation */
//                 Map<Lock, Condition> lckAndCondPassCurrStaKVP = this.mbta.staionLockAndConditionKVP.get(passInitStation);
//                 Lock passCurrStaLck = null;
//                 Condition passCurrStaCondition = null;
//                 if (lckAndCondPassCurrStaKVP != null)
//                 {
//                     for (Lock l : lckAndCondPassCurrStaKVP.keySet())
//                     {
//                         passCurrStaLck = l;
//                         passCurrStaCondition = lckAndCondPassCurrStaKVP.get(passCurrStaLck);
//                         break;
//                     }
//                 }
    
//                 Lock passNxtStaLck = null;
//                 Condition passNxtStaCondition = null;
//                 if (passNxtStation != null)
//                 {
//                     /* get lock and condition of passNextStation */
//                     Map<Lock, Condition> lckAndCondPassNxtStaKVP = this.mbta.staionLockAndConditionKVP.get(passNxtStation);
//                     if (lckAndCondPassNxtStaKVP != null)
//                     {
//                         for (Lock l : lckAndCondPassNxtStaKVP.keySet())
//                         {
//                             passNxtStaLck = l;
//                             passNxtStaCondition = lckAndCondPassNxtStaKVP.get(passNxtStaLck);
//                             break;
//                         }
//                     }
//                 }
                
    
//                 if ((passCurrStaLck != null) && (passCurrStaCondition != null))
//                 {
//                     passCurrStaLck.lock();

//                     while (trainToGetOn == null)
//                     {
//                         for (Train t : mbta.trainAndStationsKVP.keySet())
//                         {
//                             //System.out.println("Curr Train: " + t.toString() + " curr station: " + mbta.trainAndStationsKVP.get(t).getFirst());
//                             if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
//                             {
//                                 System.out.println("Found train");
//                                 trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
//                                 trainToGetOn = t;
//                                 break;
//                             }
//                             else
//                             {
//                                 System.out.println("train not found");
//                                 trainToGetOn = null;
//                             }
//                         }
//                         passCurrStaCondition.await();

//                         if (trainToGetOn != null)
//                         {
//                             System.out.println("Not null");
//                             break;
//                         }
//                     }

//                     try
//                     {
//                         mbta.boardPass(mbta, passenger, trainCurrStation, trainToGetOn);
//                     }
//                     catch (Exception e)
//                     {
//                         System.out.println("EXCEPTION BOARD: " + e);
//                     }
//                     this.log.passenger_boards(passenger, trainToGetOn, trainCurrStation);
//                     passCurrStaCondition.signalAll();
//                     passCurrStaLck.unlock();
                    
//                     if ((passNxtStaLck != null) && (passNxtStaCondition != null))
//                     {
//                         passNxtStaLck.lock();
//                         while (!mbta.trainAndStationsKVP.get(trainToGetOn).getFirst().equals(passNxtStation))
//                         {
//                             System.out.println("Waiting to deboard");
//                             passNxtStaCondition.await();
//                         }    

//                         try
//                         {
//                             mbta.deboardPass(mbta, passenger, passNxtStation, trainToGetOn);
//                         }
//                         catch (Exception e)
//                         {
//                             System.out.println("EXCEPTION DEBOARD: " + e);
//                         }
//                         this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
//                         // if (mbta.trainToBoardedPassengers.isEmpty())
//                         // {
//                         //     return;
//                         // }
//                         passNxtStaCondition.signalAll();
//                         passNxtStaLck.unlock();
//                         // try
//                         // {
//                         //     mbta.checkEnd();
//                         //     return;
//                         // }
//                         // catch (Exception e)
//                         // {
//                         // }

//                     }
//                 }
//             }
//         }
//         catch (Exception e)
//         {
//             //e.printStackTrace();
//         }
//     }
// }
