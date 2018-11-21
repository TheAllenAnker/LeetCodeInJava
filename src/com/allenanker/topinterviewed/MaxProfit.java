package com.allenanker.topinterviewed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {
    /**
     * Do k projects with money amount of fund, given each project's cost and profit.
     *
     * @param k
     * @param fund
     * @param costs
     * @param profits
     * @return
     */
    public static int maxProfit(int k, int fund, int[] costs, int[] profits) {
        Project[] projects = new Project[profits.length];
        for (int i = 0; i < profits.length; i++) {
            projects[i].cost = costs[i];
            projects[i].profit = profits[i];
        }

        PriorityQueue<Project> minCostHeap = new PriorityQueue<>(new MyProjectCostComparator());
        PriorityQueue<Project> maxProfitHeap = new PriorityQueue<>(new MyProjectProfitComparator());
        minCostHeap.addAll(Arrays.asList(projects));
        for (int i = 0; i < k; i++) {
            while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= fund) {
                maxProfitHeap.add(minCostHeap.poll());
            }
            if (maxProfitHeap.isEmpty()) {
                return fund;
            }
            fund += maxProfitHeap.poll().profit;
        }

        return fund;
    }
}

class Project {
    int cost;
    int profit;
}

class MyProjectCostComparator implements Comparator<Project> {
    @Override
    public int compare(Project p1, Project p2) {
        return Integer.compare(p1.cost, p2.cost);
    }
}

class MyProjectProfitComparator implements Comparator<Project> {
    @Override
    public int compare(Project p1, Project p2) {
        return Integer.compare(p2.profit, p1.profit);
    }
}