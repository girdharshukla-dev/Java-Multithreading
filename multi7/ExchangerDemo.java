package multi7;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread one = new Thread(new FirstThread(exchanger));
        Thread two = new Thread(new SecondThread(exchanger));
        one.start();
        two.start();
    }
}

class FirstThread implements Runnable {
    private final Exchanger<String> exchanger;

    public FirstThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        String dataToSend = " sent from first thread ";
        System.out.println("Sending data from FirstThread");
        try {
            String receivedString = exchanger.exchange(dataToSend);
            System.out.println("FirstThread received : " + receivedString);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondThread implements Runnable {
    private final Exchanger<String> exchanger;

    public SecondThread(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String dataToSend = "sent from second thread";
        System.out.println("Sending data from secondThread");
        try {
            String receivedString = exchanger.exchange(dataToSend);
            System.out.println("SecondThread recevied : " + receivedString);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}