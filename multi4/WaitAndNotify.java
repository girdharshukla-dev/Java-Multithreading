package multi4;

public class WaitAndNotify{

    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            try {
                oneFunc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(()->{
            try {
                twoFunc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        one.start();
        two.start();
    }

    private static void oneFunc() throws InterruptedException{
        synchronized(LOCK){
            System.out.println("In the one function......");
            LOCK.wait();
            System.out.println("Back in the one function....");
        }
    }

    private static void twoFunc() throws InterruptedException{
        synchronized(LOCK){
            System.out.println("In the two function ......");
            LOCK.notify();
            System.out.println("Back in the two function after notifying ......");
        }
    }
}
