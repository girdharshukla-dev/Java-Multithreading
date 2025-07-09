import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetection {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            synchronized(lock1){
                System.out.println("Thread 1 holding on lock1 ");
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Thread 1 waiting for lock2 ...");
                synchronized(lock2){
                    System.out.println("Thread 1 acquired lock2 after lock1");
                }
            }
        } , "Thread1");

        Thread thread2 = new Thread(()->{
            synchronized(lock2){
                System.out.println("Thread 2 holding on lock2 ");
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Thread 2 waiting for lock1 ...");
                synchronized(lock1){
                    System.out.println("Thread 2 acquired lock1 after lock2");
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();

        new Thread(()->{
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while(true){
                long[] threadIds = mxBean.findDeadlockedThreads();
                if(threadIds != null){
                    System.out.println("Deadlock detected ");
                    for(long threadId : threadIds){
                        ThreadInfo threadInfo = mxBean.getThreadInfo(threadId);
                        System.out.println("Thread with thread name : " + threadInfo.getThreadName() + " in deadlock");
                    }
                    System.exit(0);
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
