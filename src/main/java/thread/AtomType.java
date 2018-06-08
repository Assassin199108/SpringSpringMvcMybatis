package thread;

import common.anno.SafeThread;
import common.anno.UnSafeThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类型
 *
 * @author Administrator
 */
public class AtomType {

    private AtomicInteger i = new AtomicInteger(0);

    @SafeThread
    public void add(){
        for (int j = 0; j < 100; j++) {
            this.i.addAndGet(1);
            System.out.println("当前线程"+Thread.currentThread().getName()+"的值："+ i.intValue());
        }
    }

    @UnSafeThread
    public void sleepAdd(){
        i.addAndGet(1);
        System.out.println(Thread.currentThread().getName() + ":----" + i);
        try {
            Thread.sleep(100);
            i.addAndGet(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":----" + i);
    }

}
