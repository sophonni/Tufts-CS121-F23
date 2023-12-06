// import java.util.*;

// public class TrainThread extends Thread{
//     private MBTA mbta;
//     private String trainName;
//     private Log log;

//     public TrainThread(MBTA mbta, String trainName, Log log)
//     {
//         this.mbta = mbta;
//         this.trainName = trainName;
//         this.log = log;
//     }

//     public void run()
//     {
//         while (true)
//         {
//             /*
//              * call mbta.trainLock.lock to aquire the lock before moving the train
//              * move the train forward (modify some shared data or memory)
//              * wait 1000 milliseconds
//              * call mbta.trainLock.unlock to let other thread use it
//              * 
//              * Deadlock happens when some thread hold on to the lock and NEVER unlock it
//              */

//             /*
//              * Problem 1
//              *  - trainLock.lock(): can't hold lock with Thread.sleep;
//              *  - have 1 lock per station instead of 1 lock for train
//              */
//             mbta.trainLock.lock();
            
//             Train currTrain = Train.make(this.trainName);
//             Station currStation = this.mbta.trainAndStationsKVP.get(currTrain).getFirst();
//             int currentIndex = this.mbta.trainAndStationsKVP.get(currTrain).indexOf(currStation);
            
//             Station nxtStation = this.mbta.trainAndStationsKVP.get(currTrain).get(currentIndex + 1);
            
//             try
//             {
//                 this.log.train_moves(currTrain, currStation, nxtStation);
//                 MoveEvent moveEvent = new MoveEvent(currTrain, currStation, nxtStation);
//                 moveEvent.replayAndCheck(this.mbta);
//                 System.out.println("Curr Station: " + mbta.trainAndStationsKVP.get(currTrain).getFirst());
//                 mbta.trainCondition.signalAll();

//                 Thread.sleep(1000); //no other threads can't get lock while sleeping
//                 //this.mbta.trainCondition.await(); //ISSUE: THIS CAUSE DEADLOCK
//             }
//             catch (InterruptedException ie)
//             {
//                 throw new RuntimeException(ie);
//             }
            
            
            
//             mbta.trainCondition.signalAll(); // Signal other threads waiting on train movement
//             mbta.trainLock.unlock();
//         }
//     }
// }
