import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TrainThread extends Thread{
    private MBTA mbta;
    private String trainName;
    private Log log;

    public TrainThread(MBTA mbta, String trainName, Log log)
    {
        this.mbta = mbta;
        this.trainName = trainName;
        this.log = log;
    }

    public void run()
    {
        while (true)
        {
            synchronized(mbta)
            {

                Train thisTrain = Train.make(this.trainName);
                Station thisTrainCurrStation = this.mbta.trainAndStationsKVP.get(thisTrain).getFirst();
    
                int currentIndex = this.mbta.trainAndStationsKVP.get(thisTrain).indexOf(thisTrainCurrStation);
                Station thisTrainNextStation = this.mbta.trainAndStationsKVP.get(thisTrain).get(currentIndex + 1);
    
    
    
                /* get lock of condition of the train's next station */
                Map<Lock, Condition> stationLockAndCondition = this.mbta.staionLockAndConditionKVP.get(thisTrainNextStation);
                Lock nxtStaLck = null;
                Condition nxtStaLckCondition = null;
                if (stationLockAndCondition != null)
                {
                    for (Lock l : stationLockAndCondition.keySet())
                    {
                        nxtStaLck = l;
                        nxtStaLckCondition = stationLockAndCondition.get(nxtStaLck);
                        break;
                    }
                }
    
                
                /* get list of train where its current station = station that this train want to move to */
                List<Train> listOfOtherTrains = new ArrayList<>();
                for (Train t : this.mbta.trainAndStationsKVP.keySet())
                {
                    if (!thisTrain.equals(t))
                    {
                        listOfOtherTrains.add(t);
                    }
                }
                
                
                /* lock next station */
                nxtStaLck.lock();
                /* there are train/s at station that this train want to move to */
                if (!listOfOtherTrains.isEmpty())
                {
                    // for (Train t : listOfOtherTrains)
                    // {
                    //     Station otherTrainCurrStation = this.mbta.trainAndStationsKVP.get(t).getFirst();
                    //     while (otherTrainCurrStation.equals(thisTrainNextStation))
                    //     {
                    //         try
                    //         {
                    //             /* tell current train to wait before move to the train where there's train */
                                
                    //             System.out.println("Waiting");
                    //             nxtStaLckCondition.await();
                    //             System.out.println("Released");
                    //         }
                    //         catch (InterruptedException ie)
                    //         {
                    //             throw new RuntimeException(ie);
                    //         }
                    //     }
                    //     break;
                    // }
                    
                    nxtStaLck.lock();
                    /* the train in which this train want to move to, is free (no other train there) */
                    try
                    {
                        this.log.train_moves(thisTrain, thisTrainCurrStation, thisTrainNextStation);
                        mbta.moveTrain(mbta, thisTrainCurrStation, thisTrainNextStation, thisTrain);
                    }
                    catch (Exception e)
                    {
                        nxtStaLckCondition.signalAll();
                        nxtStaLck.unlock();
                    }
                    nxtStaLckCondition.signalAll();
                    nxtStaLck.unlock();
    
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException ie)
                    {
                        throw new RuntimeException(ie);
                    }
    
                }
                else
                {
                    nxtStaLck.lock();
                    // synchronized(mbta)
                    // {
                    this.log.train_moves(thisTrain, thisTrainCurrStation, thisTrainNextStation);
                    mbta.moveTrain(mbta, thisTrainCurrStation, thisTrainNextStation, thisTrain);
                    // }
                    // System.out.println("Next Station Condition Signal: " + nxtStaLckCondition);
                    nxtStaLckCondition.signalAll();
                    nxtStaLck.unlock();
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException ie)
                    {
                        throw new RuntimeException(ie);
                    }
                }
                nxtStaLckCondition.signalAll();
                nxtStaLck.unlock();
            }

        }
    }
}
