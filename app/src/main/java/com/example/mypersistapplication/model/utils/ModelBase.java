package com.example.mypersistapplication.model.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelBase {

    /**
     * Below variable is used to initialize ExecutorService class object.
     */
    private final ExecutorService mExecutorService;
    /**
     * Below constructor is used to initialize newly created single-threaded Executor.
     */
    public ModelBase() {
        mExecutorService = Executors.newSingleThreadExecutor();
    }
    /**
     * Below method is used to run on worker thread.
     */
    protected void runOnWorker(Runnable r) {
        mExecutorService.submit(r);
    }
}
