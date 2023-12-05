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
            /*
             * call mbta.trainLock.lock to aquire the lock before moving the train
             * move the train forward (modify some shared data or memory)
             * wait 1000 milliseconds
             * call mbta.trainLock.unlock to let other thread use it
             * 
             * Deadlock happens when some thread hold on to the lock and NEVER unlock it
             */

            this.mbta.trainLock.lock();

            Train currTrain = Train.make(this.trainName);
            Station currStation = this.mbta.trainAndStationsKVP.get(currTrain).getFirst();
            Station nxtStation;

            if (mbta.isTrainMovingForward)
            {
                nxtStation = this.mbta.trainAndStationsKVP.get(currStation).get(this.mbta.trainAndStationsKVP.get(currStation).indexOf(currStation) + 1);
            }
            else
            {
                nxtStation = this.mbta.trainAndStationsKVP.get(currStation).get(this.mbta.trainAndStationsKVP.get(currStation).indexOf(currStation) - 1);
            }

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie)
            {
                throw new RuntimeException(ie);
            }
            
            this.log.train_moves(currTrain, currStation, nxtStation);
            this.mbta.trainCondition.signalAll();
            this.mbta.trainLock.unlock();
        }
    }
}
