package com.taskexecutor;

public class Task implements Runnable
{
    public int id;
    public Task(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println("Task "+id+" running by <"+Thread.currentThread().getName()+">");

        try {
            Thread.sleep(1000*id);
        }
        catch (InterruptedException ignored){}
    }
}
