package multi7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Blocking {
    static final int QUEUE_CAPACITY = 10;
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        Thread producer = new Thread(()->{
            try{
                for (int i = 0; i <= 20 ; i++) {
                    taskQueue.put(i);
                    System.out.println("Task produced " + i);
                    Thread.sleep(500);
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        Thread consumer1 = new Thread(()->{
            try{
                while(true){
                    int task = taskQueue.take();
                    processTask(task, "consumer1");
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        Thread consumer2 = new Thread(()->{
            try{
                while(true){
                    int task = taskQueue.take();
                    processTask(task, "consumer2");
                }
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }

    public static void processTask(int task , String consumer_name) throws InterruptedException{
        System.out.println("Task being processed by " + consumer_name + " : " + task);
        Thread.sleep(1000);
        System.out.println("Task consumed by " + consumer_name + " : " + task);
    }
}
