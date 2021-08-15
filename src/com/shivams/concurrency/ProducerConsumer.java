package com.shivams.concurrency;

public class ProducerConsumer {
    public static void main(String args[]) throws InterruptedException {
        final BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(3);

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0; i<5; i++) {
                        blockingQueue.enqueue(i);
                        System.out.println("Tread 1 enqueued:" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Exception occurred " +  e.getMessage());
                }
            }
        });

        Thread threadTwo =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Tread 2 dequeued:" + blockingQueue.dequeue());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Exception occurred " +  e.getMessage());
                }
            }
        });

//        Thread threadThree = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for(int i=0; i<20; i++) {
//                        blockingQueue.enqueue(i);
//                        System.out.println("Thread 3 enqueued:" + i);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    System.out.println("Exception occurred " +  e.getMessage());
//                }
//            }
//        });

        Thread threadFour =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Thread 4 dequeued:" + blockingQueue.dequeue());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Exception occurred " +  e.getMessage());
                }
            }
        });

        threadOne.start();
        Thread.sleep(4000);
        threadTwo.start();

        threadTwo.join();

        threadFour.start();
        threadOne.join();
        threadFour.join();
    }
}
