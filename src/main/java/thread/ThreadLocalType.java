package thread;

import common.anno.SafeThread;

/**
 * 当前线程类型
 * ThreadLocal定义了四个方法：
 * get()：返回此线程局部变量的当前线程副本中的值。
 * initialValue()：返回此线程局部变量的当前线程的“初始值”。
 * remove()：移除此线程局部变量当前线程的值。
 * set(T value)：将此线程局部变量的当前线程副本中的值设置为指定值。
 * 除了这四个方法，ThreadLocal内部还有一个静态内部类ThreadLocalMap，
 * 该内部类才是实现线程隔离机制的关键，get()、set()、remove()都是基于该内部类操作。
 * ThreadLocalMap提供了一种用键值对方式存储每一个线程的变量副本的方法，key为当前ThreadLocal对象，value则是对应线程的变量副本。
 *
 * @author Administrator
 */
public class ThreadLocalType {

    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

    @SafeThread
    public void add(){
        for (int j = 0; j < 100; j++) {
            int i = threadLocal.get() + 1;
            threadLocal.set(i);
            System.out.println("当前线程"+Thread.currentThread().getName()+"的值："+ threadLocal.get());
        }
    }

    @SafeThread
    public void sleepAdd(){
        int i = threadLocal.get() + 1;
        System.out.println(Thread.currentThread().getName() + ":----" + i);
        try {
            Thread.sleep(100);
            i = i + 2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":----" + i);
        threadLocal.set(i);
    }

}
