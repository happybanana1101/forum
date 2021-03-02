package com.jsq.forum.util;

import org.springframework.stereotype.Component;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadPoolUtil {
    private static ExecutorService executor;
    static {
        executor = Executors.newFixedThreadPool(150);
    }
    ExecutorService getExecutor(){
        return executor;
    }
}
