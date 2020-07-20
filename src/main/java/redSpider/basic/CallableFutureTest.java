package redSpider.basic;

import java.util.concurrent.*;

public class CallableFutureTest {

    static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));

    public static void main(String[] args) {

        // Future类接口
        // cancel：试图取消线程 (不一定成功)
        // isDnoe：是否完成
        // isCancel：是否取消
        // get  get(long paramLong, TimeUnit paramTimeUnit)
        Future<String> result = poolExecutor.submit(new Task());
        try {
            result.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        // FutureTask类实现了Callable和Future
        FutureTask<String> futureTask = new FutureTask<>(new Task());
        poolExecutor.submit(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "call";
    }

}
