package multi7;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Arrays;
import java.util.List;

public class COWADemo {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}

class Simulation{
    private final List<Integer> list;

    public Simulation(){
        this.list = new CopyOnWriteArrayList<>();
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0));
    }

    public void simulate(){
        Thread one = new Thread(new WriteTask(list));
        Thread two = new Thread(new WriteTask(list));
        Thread three = new Thread(new WriteTask(list));
        Thread four = new Thread(new ReadTask(list));
        one.start();
        two.start();
        three.start();
        four.start();
    }
}


class ReadTask implements Runnable{
    private final List<Integer> list;
    public ReadTask(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(list);
        }
    }
}

class WriteTask implements Runnable{
    private List<Integer> list;
    public Random random;
    public WriteTask(List<Integer> list){
        this.random = new Random();
        this.list = list;
    }
    @Override
    public void run() {
       while(true){
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.set(random.nextInt(list.size()) , random.nextInt(10));
       }
    }
}