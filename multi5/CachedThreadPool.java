import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<1000;i++){
            service.execute(new TaskOne(i));
        }
        service.shutdown();
    }
}

class TaskOne implements Runnable{

    private int taskID;
    public TaskOne(int taskID){
        this.taskID = taskID;
    }

    @Override
    public void run(){
        System.out.println("Task "+taskID+" being executed by "+Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}