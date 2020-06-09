package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolInvokeAnyTest {

    public static void main(String[] args) {
        ThreadPoolInvokeAnyTest threadPoolInvokeAnyTest = new ThreadPoolInvokeAnyTest();
        System.out.println(threadPoolInvokeAnyTest.getFastTread2());
    }


    private String getFastTread1() {
        ThreadPoolInvokeAnyTest threadPoolInvokeAnyTest = new ThreadPoolInvokeAnyTest();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue(10000));
        Future<String> future1 = poolExecutor.submit(new Callable<>() {
            @Override
            public String call() throws Exception {
                return threadPoolInvokeAnyTest.sleep1();
            }

        });
        Future<String> future2 = poolExecutor.submit(new Callable<>() {
            @Override
            public String call() throws Exception {
                return threadPoolInvokeAnyTest.sleep2();
            }
        });
        Future<String> future3 = poolExecutor.submit(new Callable<>() {
            @Override
            public String call() throws Exception {
                return threadPoolInvokeAnyTest.sleep3();
            }
        });
        String ans = null;
        while (true) {
            if (future1.isDone() || future2.isDone() || future3.isDone()) {
                try {
                    if (future1.isDone())
                        ans = future1.get();
                    if (future2.isDone())
                        ans = future2.get();
                    if (future3.isDone())
                        ans = future3.get();
                    break;
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }
        }
        return ans;

    }

    /**
     * 获取最快的线程
     */
    private String getFastTread2() {
        String ans = null;
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2, 50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue(10000));
        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return sleep1();
            }
        };
        Callable<String> callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return sleep2();
            }
        };
        Callable<String> callable3 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return sleep3();
            }
        };
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(callable1);callables.add(callable2);callables.add(callable3);
        try {
            //invokeAny方法会对线程进行提交
            ans = poolExecutor.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ans;
    }


    private String sleep1() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "111111";
    }

    private String sleep2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "222222";
    }

    private String sleep3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "333333";
    }

    private void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
