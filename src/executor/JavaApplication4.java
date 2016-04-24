package executor;

/**
 * Created by Юлия on 22.04.2016.
 */

        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

class MyTask implements Runnable {

    final int number;

    public MyTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number);
        while (true){

        }
    }

}

public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i=0; i<20; ++i) {
            Runnable task = new MyTask(i);
            executor.submit(task);
        }
        executor.shutdown();
    }

}