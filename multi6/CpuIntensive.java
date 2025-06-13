package multi6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensive {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        System.out.println("Created thread pool with : "+cores+" cores");
        for(int i = 0; i<20;i++){
            service.execute(new CpuTask());
        }
        service.shutdown();
    }
}

class CpuTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Some cpu intensive task is being performed ....." + Thread.currentThread().getName());
    }
}