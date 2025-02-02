package com.taskexecutor;

public class Main {
    public static void main(String[] args) {

        CustomThreadPool customThreadPool =  new CustomThreadPool(2);

        //Create Task
        Task task1 = new Task(1);
        Task task2 = new Task(2);
        Task task3 = new Task(3);
        Task task4 = new Task(4);
        customThreadPool.submitTask(task1);
        customThreadPool.submitTask(task2);
        customThreadPool.submitTask(task3);
        customThreadPool.submitTask(task4);

        customThreadPool.runTask();
        try {
            Thread.sleep(8000);

        }
        catch (Exception e){

        }
        //Submitting Another task
        Task task8 = new Task(8);
        System.out.println("............Submitting one new task.........");
        customThreadPool.submitTask(task8);


        try {Thread.sleep(8000);}
        catch (Exception ignored){}
        customThreadPool.shutDown();

    }
}