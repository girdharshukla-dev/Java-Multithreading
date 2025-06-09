package multi3;

// import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronization {
    static int counter = 0;
    // static AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(()->{
            for(int i=0;i<10000;i++){
                // counter++;
                // counter.getAndIncrement();
                increment();
            }
        });
        Thread two = new Thread(()->{
            for(int i=0;i<10000;i++){
                // counter++;
                // counter.getAndIncrement();
                increment();
            }
        });
        
        one.start();
        two.start();
        one.join();
        two.join();
        
        System.out.println("value of counter = "+counter);
    }

    private synchronized static void increment(){
        counter++;
    }
}
