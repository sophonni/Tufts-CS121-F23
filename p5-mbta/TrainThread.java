import java.util.*;

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
            //this.mbta.stationLock.lock();;
            // Train thisTrain = Train.make(this.trainName);
            // Station thisTrainCurrStation = this.mbta.trainAndStationsKVP.get(thisTrain).getFirst();
            // int currentIndex = this.mbta.trainAndStationsKVP.get(thisTrain).indexOf(thisTrainCurrStation);
            // Station thisTrainNextStation = this.mbta.trainAndStationsKVP.get(thisTrain).get(currentIndex + 1);

            // for (Train t : this.mbta.trainAndStationsKVP.keySet())
            // {
            //     if (!thisTrain.equals(t))
            //     {
            //         Station otherTrainCurrStation = this.mbta.trainAndStationsKVP.get(t).getFirst();

            //         if (otherTrainCurrStation.equals(thisTrainCurrStation))
            //         {
            //             try
            //             {
            //                 this.mbta.stationCondition.wait();
            //             }
            //             catch (InterruptedException ie)
            //             {
            //                 throw new RuntimeException(ie);
            //             }
            //         }
            //     }
            // }

            // this.log.train_moves(thisTrain, thisTrainCurrStation, thisTrainNextStation);

            // try
            // {
            //     Thread.sleep(1000);
            // }
            // catch (InterruptedException ie)
            // {
            //     throw new RuntimeException(ie);
            // }
            // MoveEvent moveEvent = new MoveEvent(thisTrain, thisTrainCurrStation, thisTrainNextStation);
            // moveEvent.replayAndCheck(mbta);

            //this.mbta.stationCondition.signalAll();
            //this.mbta.stationLock.unlock();
        }
    }
}
