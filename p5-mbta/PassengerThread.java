// import java.util.*;

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
//         /*
//             * get first location of passenger journey
//             * iterate thorugh trainsToStation kvp
//             * if first of currTrain is == first location of passenger journey
//             * board passenger
//             * have passenger wait on the train
//             * debaord passenger when arraive at the next station of the passenger's journey
//         */

//         while (true) {
//             mbta.passLock.lock(); // Acquire passLock first
//             mbta.trainLock.lock();
    
//             Passenger passenger = Passenger.make(this.passengerName);
//             Station passInitStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
//             Station trainCurrStation = null;
//             Train trainToGetOn  = null;
            
//             /* get the train where its current station = passenger initial station */
//             for (Train t : mbta.trainAndStationsKVP.keySet()) {
//                 if (mbta.trainAndStationsKVP.get(t).getFirst().equals(passInitStation))
//                 {
//                     trainCurrStation = mbta.trainAndStationsKVP.get(t).getFirst();
//                     trainToGetOn = t;
//                     break;
//                 }
//             }

//             while (!trainCurrStation.equals(passInitStation))
//             {
//                 /* "temporarily" release the train lock to have the train class
//                 lock onto it in order to move the train forward */
//                 System.out.println("Wait to get ON");
//                 try
//                 {
//                     mbta.trainCondition.await();
//                 }
//                 catch (InterruptedException ie)
//                 {
//                     throw new RuntimeException(ie);
//                 }
//             }

//             /* train current station matches with the passenger's initial station of their journey */
//             if (trainCurrStation.equals(passInitStation))
//             {
//                 /* board passenger onto the train */
//                 this.log.passenger_boards(passenger, trainToGetOn, passInitStation);
//                 BoardEvent boardEvent = new BoardEvent(passenger, trainToGetOn, passInitStation);
//                 boardEvent.replayAndCheck(mbta);
                
//                 /* "temporarily" release the train lock to have the train class lock onto
//                 it in order to move the train forward */
//                 try
//                 {
//                     mbta.trainCondition.await();
//                 }
//                 catch (InterruptedException ie)
//                 {
//                     throw new RuntimeException(ie);
//                 }

                
//                 Station passNxtStation = mbta.passengerAndStationsKVP.get(passenger).getFirst();
//                 System.out.println("Pass Next Sta: " + passNxtStation);
//                 /* get the train lock after the train had move forward once,
//                 to check if current station of the train matches with
//                 passenger's next station of their journey */
//                 mbta.trainLock.lock();
//                 while (!trainCurrStation.equals(passNxtStation))
//                 {
//                     /* "temporarily" release the train lock to have the train class lock onto
//                     it in order to move the train forward */
//                     System.out.println("Wait to get OFF");
//                     try
//                     {
//                         mbta.trainCondition.await();
                        
//                     }
//                     catch (InterruptedException ie)
//                     {
//                         throw new RuntimeException(ie);
//                     }
//                 }

//                 /* current station of the train matches with passenger's next station of their journey */
//                 if (trainCurrStation.equals(passNxtStation)) {
//                     System.out.println("Found Next Station");
//                     /* deboard passenger and "temporarily" release the train lock to have the train class lock onto
//                     it in order to move the train forward */
//                     this.log.passenger_deboards(passenger, trainToGetOn, passNxtStation);
//                     DeboardEvent deboardEvent = new DeboardEvent(passenger, trainToGetOn, passNxtStation);
//                     deboardEvent.replayAndCheck(mbta);
//                     mbta.trainCondition.signalAll(); // Signal other threads waiting on train movement
//                 }
//             }
//             mbta.trainCondition.signalAll();
//             mbta.trainLock.unlock();
//             mbta.passLock.unlock(); // Release passLock
//         }
//     }
// }
