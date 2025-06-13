package multi6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> result = service.submit(new ReturnValueTask());
        System.out.println(result.get());
        service.shutdown();
    }
}

class ReturnValueTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 3;
    }
}