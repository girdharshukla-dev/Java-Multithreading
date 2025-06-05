package multi2;

public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread one  = new Thread(()->{
            for(int i=0;i<50;i++){
                System.out.println("Thread1 "+i);
            }
        });
        
        Thread two  = new Thread(()->{
            for(int i=0;i<25;i++){
                System.out.println("Thread2 "+i);
            }
        });
        one.start();
        one.join();  //it stops the main thread , from executing first
        two.start();
        System.out.println("Completed .....");
    }
}
