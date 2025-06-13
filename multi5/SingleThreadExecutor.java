import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            service.execute(new Task(i));
        }
        service.shutdown();
    }
}

class Task implements Runnable {

    private final int taskID;

    public Task(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void run(){
        System.out.println("Task with ID "+taskID+" being executed by thread "+ Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
