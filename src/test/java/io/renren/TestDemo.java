package io.renren;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author yangxj
 * @date 2020-07-23 09:56
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        ExecutorService executor = Executors.newFixedThreadPool(3);


        IntStream.range(0,2).forEach(item -> executor.submit(() -> threadLocal.set(Thread.currentThread().getName())));

        threadLocal.set(Thread.currentThread().getName());

        System.out.println(threadLocal.get());
    }
}
