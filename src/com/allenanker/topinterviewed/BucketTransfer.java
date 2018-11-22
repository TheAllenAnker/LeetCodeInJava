package com.allenanker.topinterviewed;

public class BucketTransfer {
    public static void main(String[] args) {
        bucketTransfer(3, "Left", "Right", "Mid");
    }

    public static void bucketTransfer(int n, String from, String to, String help) {
        if (n == 1) {
            System.out.println("Moving " + n + " from " + from + " to " + to);
        } else {
            bucketTransfer(n - 1, from, help, to);
            System.out.println("Moving " + n + " from " + from + " to " + to);
            bucketTransfer(n - 1, help, to, from);
        }
    }
}
