package multi3;

// import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronization2 {
    static int counter1 = 0;
    static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    // static AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                // counter++;
                // counter.getAndIncrement();
                increment1();
            }
        });
        Thread two = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                // counter++;
                // counter.getAndIncrement();
                increment2();
            }
        });

        one.start();
        two.start();
        one.join();
        two.join();

        System.out.println("value of counter1 = " + counter1 + " counter2 = "+counter2);
    }

    private static void increment1() {
        synchronized(lock1){
            counter1++;
        }
    }

    private static void increment2() {
        synchronized(lock2){
            counter2++;
        }
    }
}
