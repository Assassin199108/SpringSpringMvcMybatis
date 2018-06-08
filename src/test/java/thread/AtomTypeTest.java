package thread;

import org.junit.Test;

import static org.junit.Assert.*;

public class AtomTypeTest {

    @Test
    public void add() {
        AtomType atomType = new AtomType();
        Runnable runnable1 = atomType::add;
        Runnable runnable2 = atomType::add;
        Runnable runnable3 = atomType::add;
        Runnable runnable4 = atomType::add;
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable3).start();
        new Thread(runnable4).start();
    }


    @Test
    public void sleepAdd() {
        AtomType atomType = new AtomType();
        Runnable runnable1 = atomType::sleepAdd;
        Runnable runnable2 = atomType::sleepAdd;
        Runnable runnable3 = atomType::sleepAdd;
        Runnable runnable4 = atomType::sleepAdd;
        new Thread(runnable1).start();
        new Thread(runnable2).start();
        new Thread(runnable3).start();
        new Thread(runnable4).start();
    }
}