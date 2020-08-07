package io.renren.proxy;

import java.lang.reflect.Proxy;

/**
 * @author yangxj
 * @date 2020-08-06 11:49
 */
public class ProxyTest {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        Class clazz = waiter.getClass();
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), (proxy, method, args1) -> {
            System.out.println("say hello 之前");
            Object result = method.invoke(waiter, args1);
            System.out.println("say hello 之后");
            return result;
        });

         ((Say) o).sayHello();
    }
}
