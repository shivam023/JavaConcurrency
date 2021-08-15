package com.shivams.concurrency;

import java.util.ArrayList;

public class BlockingQueue<T> {
    private ArrayList<T> queue;
    private Integer end;
    private Integer start;
    private Integer capacity;
    private Integer currentSize;

    private Object lock = new Object();

    public BlockingQueue(Integer capacity) {
        this.capacity = capacity;
        this.start = 0;
        this.end = 0;
        this.currentSize = 0;
        this.queue = new ArrayList(capacity);
    }

    /**
     * Adds element from consumption to the end.
     *
     * If we reach to the capacity then we need to set the end to 0
     * because the elements are being consumed from the beginning and if the queue is again available to add
     * the elements then it means we have space in the beginning not in the end.
     *
     * @param element
     * @throws InterruptedException
     */
    public void enqueue(T element) throws InterruptedException {
        if(currentSize == capacity) {
            wait();
        }
        if(end == capacity) {
            end = 0;
        }
        queue.add(end, element);
        end++;
        currentSize++;
    }

    /**
     *
     * @return
     * @throws InterruptedException
     */
    public T dequeue() throws InterruptedException {
        if(currentSize == 0) {
            wait();
        }
        if(start == capacity) {
            start = 0;
        }
        T element = queue.get(start);
        start++;
        return element;
    }
}
