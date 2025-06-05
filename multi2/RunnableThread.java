package multi2;

public class RunnableThread {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        // Thread three = new Thread(new Runnable() {
        //     @Override
        //     public void run(){
        //         for(int i=0;i<50;i++){
        //             System.out.println("ThreadThree + "+i);
        //         }
        //     }
        // });
        Thread three = new Thread(() -> {
                for(int i=0;i<50;i++){
                    System.out.println("ThreadThree + "+i);
                }
        });
        one.start();
        two.start();
        three.start();
    }
}

class ThreadOne implements Runnable{

    @Override
    public void run() {
       for(int i=0;i<50;i++){
        System.out.println("ThreadOne + "+i);
       }
    }
}
class ThreadTwo implements Runnable{

    @Override
    public void run() {
       for(int i=0;i<50;i++){
        System.out.println("ThreadTwo + "+i);
       }
    }
}
