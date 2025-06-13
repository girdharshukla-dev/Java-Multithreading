import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutor{
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(), 1, 1, TimeUnit.SECONDS);

        try{
            if(!service.awaitTermination(10, TimeUnit.SECONDS)){
                service.shutdownNow();
            }
        }catch(InterruptedException e){
            service.shutdownNow();
        }
    }
}

class ProbeTask implements Runnable{

    @Override
    public void run() {
        System.out.println("Probing end points for updates ...");
    }

}