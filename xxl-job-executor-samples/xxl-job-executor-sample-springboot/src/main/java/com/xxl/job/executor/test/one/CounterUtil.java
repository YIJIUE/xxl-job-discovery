package com.xxl.job.executor.test.one;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CounterUtil implements Counter {

    private static List<String> list = new CopyOnWriteArrayList<>();

    @Override
    public int get(String str) {
        int count = 0;
        for (String val : list) {
            if (val.equals(str)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void add(String str) {
        list.add(str);
    }
}
