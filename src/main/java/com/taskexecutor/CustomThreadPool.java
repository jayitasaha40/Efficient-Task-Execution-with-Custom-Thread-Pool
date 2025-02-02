package com.taskexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool
{
    public List<WorkerThread> workers;
    private volatile boolean isShutDown;
    LinkedBlockingQueue<Runnable> taskQueue;

    public CustomThreadPool(int threadCount){
       this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>();
        this.isShutDown = false;

        for(int i = 0;i<threadCount;i++){
            WorkerThread worker = new WorkerThread(taskQueue,isShutDown);
            workers.add(worker);
        }
    }

    public void submitTask(Runnable task){
        if(!isShutDown) {
            taskQueue.offer(task);

        }
        else {
            throw new IllegalStateException("ThreadPool is shutting down, cannot accept new tasks");
        }
    }

    public void runTask(){
        for(WorkerThread worker: workers){
            worker.start();
        }
    }

    public void shutDown(){
        isShutDown = true;
        for(WorkerThread worker: workers){
            worker.shutDown();
        }
    }
}
