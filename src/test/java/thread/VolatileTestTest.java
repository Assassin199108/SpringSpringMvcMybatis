package thread;

import org.junit.Test;


public class VolatileTestTest {

    @Test
    public void add(){
        VolatileTest volatileTest = new VolatileTest();
        Runnable run1 = volatileTest::add;
        Runnable run2 = volatileTest::add;
        Thread thread1 = new Thread(run1);
        Thread thread2 = new Thread(run2);
        thread1.start();
        thread2.start();
    }


    @Test
    public void sleepAdd() {
        VolatileTest volatileTestTest = new VolatileTest();
        Runnable run1 = volatileTestTest::sleepAdd;
        Runnable run2 = volatileTestTest::sleepAdd;
        Runnable run3 = volatileTestTest::sleepAdd;
        Runnable run4 = volatileTestTest::sleepAdd;
        new Thread(run1).start();
        new Thread(run2).start();
        new Thread(run3).start();
        new Thread(run4).start();
    }
}