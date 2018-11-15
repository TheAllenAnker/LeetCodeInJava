package com.allenanker.fundamentals;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Implement a stack with two queues
 */
public class TwoQueuesStack {
    private Queue<Integer> data;
    private Queue<Integer> helpQueue;
    private int size;

    public TwoQueuesStack(int size) {
        this.size = size;
        data = new PriorityQueue<>(size);
        helpQueue = new PriorityQueue<>(size);
    }

    public int peek() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("Peek out of array bound.");
        }
        while (data.size() > 1) {
            helpQueue.add(data.poll());
        }
        int res = data.poll();
        helpQueue.add(res);
        swap();

        return res;
    }

    public int poll() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("Poll out of array bound");
        }
        while (data.size() > 1) {
            helpQueue.add(data.poll());
        }
        int res = data.poll();
        swap();

        return res;
    }

    public void push(int num) {
        data.offer(num);
    }

    private void swap() {
        Queue<Integer> temp = data;
        data = helpQueue;
        helpQueue = temp;
    }

    public static void main(String[] args) {
        TwoQueuesStack twoQueuesStack = new TwoQueuesStack(3);
        twoQueuesStack.push(1);
        twoQueuesStack.push(2);
        twoQueuesStack.push(3);
        for (int i = 0; i < 3; i++) {
            System.out.println(twoQueuesStack.poll());
        }
    }
}
