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
        while (!Thread.interrupted())
        {
            Train thisTrain = Train.make(trainName);

            Station currSta = mbta.trainAndStationsKVP.get(thisTrain).getFirst();
            int currStaIdx = mbta.trainAndStationsKVP.get(thisTrain).indexOf(currSta);
            Station nxtSta = mbta.trainAndStationsKVP.get(thisTrain).get(currStaIdx + 1);

            /* get lock and condition of current station */
            Map<Lock, Condition> currStaLckAndCondKVP = mbta.staionLockAndConditionKVP.get(currSta);
            Lock currStaLck = (Lock) currStaLckAndCondKVP.keySet().toArray()[0];
            Condition currStaCond = (Condition) currStaLckAndCondKVP.values().toArray()[0];

            /* get lock and condition of next station */
            Map<Lock, Condition> nxtStaLckAndCondKVP = mbta.staionLockAndConditionKVP.get(nxtSta);
            Lock nxtStaLck = (Lock) nxtStaLckAndCondKVP.keySet().toArray()[0];
            Condition nxtStaCond = (Condition) nxtStaLckAndCondKVP.values().toArray()[0];
            Train trainAtNxtSta = null;

            currStaLck.lock();
            nxtStaLck.lock();
            try
            {
                for (Train t : mbta.trainAndStationsKVP.keySet())
                {
                    if (mbta.trainAndStationsKVP.get(t).getFirst().equals(nxtSta))
                    {
                        trainAtNxtSta = t;
                        break;
                    }
                    else
                    {
                        trainAtNxtSta = null;
                    }
                }

                while (trainAtNxtSta != null)
                {
                    nxtStaCond.await();
                }
                mbta.moveTrain(mbta, currSta, nxtSta, thisTrain);
                log.train_moves(thisTrain, currSta, nxtSta);
                nxtStaCond.signalAll();
                nxtStaLck.unlock();
                currStaCond.signalAll();
                currStaLck.unlock();
                Thread.sleep(100);
            }
            catch (InterruptedException ie)
            {
                return;
            }
        }
    }
}
