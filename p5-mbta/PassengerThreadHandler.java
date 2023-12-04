// import java.util.*;

// public class PassengerThreadHandler extends Thread {
//     private MBTA mbta;
//     private String passengerName;
//     private Log log;

//     public PassengerThreadHandler(MBTA mbta, String passengerName, Log log)
//     {
//         this.mbta = mbta;
//         this.passengerName = passengerName;
//         this.log = log;
//     }

//     public void run()
//     {
//         while (true)
//         {
//             /*
//              * get first location of passenger journey
//              * iterate thorugh trainsToStation kvp
//              * if first of currTrain is == first location of passenger journey
//              * board passenger
//              * have passenger wait on the train
//              * debaord passenger when arraive at the next station of the passenger's journey
//              */
//             Station initialStation = mbta.passengerAndStationsKVP.get(Passenger.make(this.passengerName)).getFirst();

//             for(Train t : mbta.trainAndStationsKVP.keySet())
//             {
//                 if (mbta.trainAndStationsKVP.get(t).getFirst().equals(initialStation))
//                 {
                    
//                 }
//             }
//         }
//     }
// }
