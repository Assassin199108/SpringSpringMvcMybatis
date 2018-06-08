package thread;


import common.anno.SafeThread;
import common.anno.UnSafeThread;

/**
 * @author Administrator
 */
public class VolatileTest {

    private volatile int i = 0;

    /**
     * 保持了可见性
     *
     */
    @SafeThread
    public void add(){
        for (int j = 0; j < 100; j++) {
            i++;
            System.out.println("当前线程"+Thread.currentThread().getName()+"的值："+i);
        }
    }

    /**
     * 没保证原子性
     */
    @UnSafeThread
    public void sleepAdd(){
        i = i+1;
        System.out.println(Thread.currentThread().getName() + ":----" + i);
        try {
            Thread.sleep(100);
            i = i + 2;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":----" + i);
    }

    public static void main(String[] args){
    }

}
