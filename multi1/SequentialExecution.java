package multi1;

public class SequentialExecution{
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    public static void demo1(){
        for(int i=0;i<100;i++){
            System.out.println("demo1 + "+i);
        }
    }

    public static void demo2(){
        for(int i=0;i<100;i++){
            System.out.println("demo2 + "+i);
        }
    }
}