import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDemo {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;
    
    public static void main(String[] args) {
        ReentrantDemo demo = new ReentrantDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(demo::method1).start();
        }
    }

    public void method1(){
        lock.lock();
        try{
            sharedData++;
            System.out.println("method1 shared data > "+sharedData);
            method2();
        }finally{
            lock.unlock();
        }
    }

    public void method2(){
        lock.lock();
        try{
            sharedData--;
            System.out.println("method2 shared data > "+sharedData);
        }finally{
            lock.unlock();
        }
    }
}
