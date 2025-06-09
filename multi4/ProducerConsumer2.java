package multi4;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer2 {
    public static void main(String[] args) {
        Workers worker = new Workers(5, 0);
        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

class Workers {
    private final int top;
    private final int bottom;
    private final List<Integer> container;
    private int sequence = 0;
    private final Object LOCK = new Object();

    public Workers(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (LOCK) {
                while (container.size() == top) {
                    System.out.println("Container is full , waiting for items to be removed ....");
                    LOCK.wait();
                }
                container.add(sequence);
                System.out.println(sequence + " Added to container ....");
                sequence++;
                LOCK.notify();
            }
            Thread.sleep(100);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (LOCK) {
                while (container.size() == bottom) {
                    System.out.println("Container empty , waiting for items to be added ....");
                    LOCK.wait();
                }
                System.out.println(container.remove(0) + " Removed from container .....");
                LOCK.notify();
            }
            Thread.sleep(1000);
        }
    }
}