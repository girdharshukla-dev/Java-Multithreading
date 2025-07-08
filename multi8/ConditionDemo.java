package multi8;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionDemo {
    private final int MAX_SIZE = 5;
    private final Lock lock = new ReentrantLock();
    private final Queue<Integer> buffer = new LinkedList<>();
    private final Condition bufferNotFull = lock.newCondition();
    private final Condition bufferNotEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        Thread producerThread = new Thread(()->{
            for(int i=0;i<10;i++){
                demo.produce(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumerThread = new Thread(()->{
            for(int i=0;i<10;i++){
                demo.consume();
                try{
                    Thread.sleep(2500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();
    }
    
    private void produce(int item){
        lock.lock();
        try{
            while(buffer.size() == MAX_SIZE){
                bufferNotFull.await();
            }
            buffer.offer(item);
            System.out.println("Produced >> "+ item);
            bufferNotEmpty.signal();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }finally{
            lock.unlock();
        }
    }

    private void consume(){
        lock.lock();
        try{
            while(buffer.size() == 0){
                bufferNotEmpty.await();
            }
            int item = buffer.poll();
            System.out.println("Consumed >>>> "+ item);
            bufferNotFull.signal();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }finally{
            lock.unlock();
        }
    }
}
