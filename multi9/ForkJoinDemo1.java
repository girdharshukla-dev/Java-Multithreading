package multi9;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo1 {
    public static void main(String[] args) {
        int arr[] = new int[100];
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            arr[i] = random.nextInt(10);
        }
        int searchElement = random.nextInt(10);
        ForkJoinPool pool = new ForkJoinPool(4);
        SearchOccurrence task = new SearchOccurrence(arr, 0, arr.length - 1, searchElement);
        Integer occurrence = pool.invoke(task);
        System.out.println("Arrays is : " + Arrays.toString(arr));
        System.out.printf("%d found %d times ", searchElement, occurrence);
        pool.close();
    }
}

class SearchOccurrence extends RecursiveTask<Integer> {
    int[] arr;
    int start, end, searchElement;

    public SearchOccurrence(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    public Integer compute() {
        int size = end - start + 1;
        if (size > 50) {
            int mid = (start + end) / 2;
            SearchOccurrence task1 = new SearchOccurrence(arr, start, mid, searchElement);
            SearchOccurrence task2 = new SearchOccurrence(arr, mid + 1, end, searchElement);
            task1.fork();
            task2.fork();
            return task1.join() + task2.join();
        }
        return search();
    }

    private Integer search() {
        int count = 0;
        for(int i=start;i<end;i++){
            if(arr[i] == searchElement){
                count++ ;
            }
        }
        return count;
    }
}
