import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter time ....");
        int duration = sc.nextInt();
        System.out.println("Time starts .....");
        Thread timer = new Thread(()->{
            int time = duration;
            while(time>0){
                System.out.println(time--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("BOOOOOOOM !!!!!!");
        });
        LocalTime start = LocalTime.now();
        System.out.println(start);
        timer.start();
        timer.join();
        LocalTime end = LocalTime.now();
        System.out.println(end);
        sc.close();
    }
}
