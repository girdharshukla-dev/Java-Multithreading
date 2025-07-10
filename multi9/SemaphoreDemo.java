package multi9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<15;i++){
            service.execute(()->{
                ScrapService.INSTANCE.scrape();
            });
        }
        service.shutdown();
    }
}

enum ScrapService{
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3);

    public void scrape(){
        try{
            semaphore.acquire();
            invokeScrapeBot();
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }finally{
            semaphore.release();
        }
    }

    public void invokeScrapeBot(){
        try{
            System.out.println("Scraping data ");
            Thread.sleep(2000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    } 
}