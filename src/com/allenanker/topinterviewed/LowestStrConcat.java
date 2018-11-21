package com.allenanker.topinterviewed;

import java.util.Arrays;
import java.util.Comparator;

public class LowestStrConcat {
    public static String lowestConcat(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        Arrays.sort(strs, new MyStrConcatComparator());
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }
}

class MyStrConcatComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return (s1 + s2).compareTo(s2 + s1);
    }
}
