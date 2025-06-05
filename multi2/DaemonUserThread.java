package multi2;

public class DaemonUserThread {
    public static void main(String[] args) throws InterruptedException {
        Thread bgDaemonThread = new Thread(new DaemonHelper());
        bgDaemonThread.setDaemon(true);
        Thread userThread = new Thread(new UserThreadHelper());
        
        bgDaemonThread.start();
        // bgDaemonThread.join();    //this will actually defeat the purpose of making it
        userThread.start();
        
    }
}


class DaemonHelper implements Runnable{
    @Override
    public void run(){
        int count = 0;
        while(count<500){     //this should actually run for 500s, but remember that this a daemon thread
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            System.out.println("Daemon helper running ....."+count);
        }
    }
}
class UserThreadHelper implements Runnable{
    @Override
    public void run(){
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("User thread done with execution .......");
    }
}
