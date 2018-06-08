package thread;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadLocalTypeTest {

    @Test
    public void add() {
        ThreadLocalType threadLocalType = new ThreadLocalType();
        Runnable run1 = threadLocalType::add;
        Runnable run2 = threadLocalType::add;
        Runnable run3 = threadLocalType::add;
        Runnable run4 = threadLocalType::add;
        new Thread(run1).start();
        new Thread(run2).start();
        new Thread(run3).start();
        new Thread(run4).start();
    }

    @Test
    public void sleepAdd() {
        ThreadLocalType threadLocalType = new ThreadLocalType();
        Runnable run1 = threadLocalType::sleepAdd;
        Runnable run2 = threadLocalType::sleepAdd;
        Runnable run3 = threadLocalType::sleepAdd;
        Runnable run4 = threadLocalType::sleepAdd;
        new Thread(run1).start();
        new Thread(run2).start();
        new Thread(run3).start();
        new Thread(run4).start();
    }
}