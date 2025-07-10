package questions;

public class BoundedBlockingQueue {

    private int[] queue;
    private int head, tail, size, capacity;

    BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new int[capacity];
        this.head = -1;
        this.tail = -1;
        this.size = 0;
    }

    public static void main(String[] args) {
        BoundedBlockingQueue q = new BoundedBlockingQueue(4);
        Runnable producer = () -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    Thread.sleep(500);
                    int val = Integer.parseInt(Thread.currentThread().getName().split(" ")[1]) * 10 + i;
                    q.enqueue(val);
                    System.out.println(Thread.currentThread().getName() + " enqueued - " + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable consumer = ()->{
            for(int i =1;i<=5;i++){
                try{
                    Thread.sleep(2000);
                    int ans = q.dequeue();
                    System.out.println(Thread.currentThread().getName() + " dequeued - " + ans);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }  
        };
        
        new Thread(producer , "Producer 1").start();
        new Thread(producer , "Producer 2").start();
        new Thread(consumer , "Consumer 1").start();
        new Thread(consumer , "Consumer 2").start();
    }

    public void enqueue(int data) throws InterruptedException {
        synchronized (this) {
            while (this.size == this.capacity) {
                System.out.println("Queue full .....");
                this.wait();
            }
            size++;
            tail = (tail + 1) % capacity;
            queue[tail] = data;
            this.notifyAll();
        }
    }
    
    public int dequeue() throws InterruptedException {
        synchronized (this) {
            while (this.size == 0) {
                System.out.println("Queue empty ...");
                this.wait();
            }
            size--;
            head = (head + 1) % capacity;
            int val = queue[head];
            this.notifyAll();
            return val;
        }
    }

    public int size() {
        synchronized (this) {
            return this.size;
        }
    }
}
