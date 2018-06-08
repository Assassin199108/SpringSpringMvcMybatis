package thread.lock;


import org.junit.Test;

public class DeadLockTest {

    @Test
    public void test1() {
        DeadLock deadLock = new DeadLock();
        Runnable r1 = deadLock::lockOne;
        Runnable r2 = deadLock::lockTwo;
        new Thread(r1).start();
        new Thread(r2).start();
    }
}