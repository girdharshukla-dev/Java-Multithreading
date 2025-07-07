package multi7;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ConcurrentMap {
    private static final Map<String , String > cache = new ConcurrentHashMap<>();
    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            final int threadNum = i;
            new Thread(()->{
                String key = "Key @ " + threadNum;
                for(int j=0;j<3;j++){
                    String value = getCachedValue(key);
                    System.out.println("Thread " + Thread.currentThread().getName() + " : key = "+key+" value = "+value);
                }
            }).start();
        }
    }

    public static String getCachedValue(String key){
        String value = cache.get(key);
        if(value == null){
            value = compute(key);
            cache.put(key, value);
        }
        return value;
    }

    public static String compute(String key){
        System.out.println(key + " value not present so computing ....");
        try{
            Thread.sleep(500);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        return ("Value for " + key);
    }
}
