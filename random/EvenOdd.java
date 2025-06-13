package random;

public class EvenOdd {
    private final static Object LOCK = new Object();
    private final static int MAX = 100;
    private static int number = 1;
    public static void main(String[] args) {
        EvenOdd obj = new EvenOdd();
        Thread oddThread = new Thread(()->{
            printOdd();
        }, "Thread-Odd");
        Thread evenThread = new Thread(()->{
            printEven(); 
        }, "Thread-Even");
        oddThread.start();
        evenThread.start();        
    }

    public static void printOdd(){
        while(number<=MAX){
            synchronized(LOCK){
                while(number<=MAX && number%2==0){
                    try{
                        LOCK.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "  " + (number++));
                LOCK.notifyAll();
            }
        }
    }
    public static void printEven(){
        while(number<=MAX){
            synchronized(LOCK){
                while(number<=MAX && number%2!=0){
                    try{
                        LOCK.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "  " + (number++));
                LOCK.notifyAll();
            }
        }
    }
}

