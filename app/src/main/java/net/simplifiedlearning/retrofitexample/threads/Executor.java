package net.simplifiedlearning.retrofitexample.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void IoThread(Runnable runnable)
    {
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        executorService.execute(runnable);
    }
}
