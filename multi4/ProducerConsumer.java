package multi4;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker(5, 0);
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

class Worker {
    private int sequence = 0;
    private final int top;
    private final int bottom;
    private final List<Integer> container;
    private final Object LOCK = new Object();

    public Worker(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (container.size() == top) {
                    System.out.println("Container is full, waiting for items to be removed .....");
                    LOCK.wait();
                } else {
                    System.out.println(sequence + " Added to container ....");
                    container.add(sequence++);
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (container.size() == bottom) {
                    System.out.println("Container is empty, waiting for item to be added ....");
                    LOCK.wait();
                } else {
                    System.out.println(container.removeFirst() + " removed from container ....");
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}