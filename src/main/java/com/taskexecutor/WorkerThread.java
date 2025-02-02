package com.taskexecutor;

import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread extends Thread
{
    private final LinkedBlockingQueue<Runnable> taskQueue;
    private volatile boolean isShutDown;

    public WorkerThread(LinkedBlockingQueue<Runnable> taskQueue,boolean isShutDown){
        this.taskQueue = taskQueue;
        this.isShutDown = isShutDown;
    }

    public void shutDown(){
        isShutDown = true;
        System.out.println("Shutting down "+this.getName());
        this.interrupt();
    }

    @Override
    public void run(){
            while(!isShutDown){

                try {
                    System.out.println("Worker " + Thread.currentThread().getName() + " is waiting..............");
                    Runnable task = taskQueue.take();
                    //System.out.println("Worker " + Thread.currentThread().getName() + " picked up task");
                    task.run();
                } catch (InterruptedException e) {
                    if(isShutDown) break;
                }
            }

    }
}
