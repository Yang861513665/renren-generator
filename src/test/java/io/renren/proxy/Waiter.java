package io.renren.proxy;

/**
 * @author yangxj
 * @date 2020-08-06 11:48
 */
public class Waiter implements Say {
    @Override
    public void sayHello() {
        System.out.println("hello, 欢迎观影");
    }
}
