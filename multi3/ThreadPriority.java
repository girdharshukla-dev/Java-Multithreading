package multi3;

public class ThreadPriority {
    public static void main(String[] args) {
        // System.out.println(Thread.currentThread().getName());
        // System.out.println(Thread.currentThread().getPriority());
        // Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        // System.out.println(Thread.currentThread().getPriority());

        System.out.println("This is the main thread running");
        Thread one = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println("This is the other thread + "+i);
            }
        });
        one.setPriority(10);
        one.start();
        System.out.println("This is the main thread running again");

        /*setPriority is in no way a guarantee that it will be obeyed by the jvm, as you can see here */
    }
}
