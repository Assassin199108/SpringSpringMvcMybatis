package thread.lock;

/**
 * 死锁演示
 *
 * @author Administrator
 */
public class DeadLock {

    private Object o1 = new Object();

    private Object o2 = new Object();

    public void lockOne() {
        try {
            synchronized (o1){
                System.out.println("大家好，A拿到o1的线程锁了-----现在准备去拿o2线程锁，有点累睡一会");
                Thread.sleep(100);
                synchronized (o2){
                    System.out.println("大家好，A拿到o2的线程锁了");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void lockTwo(){
        try {
            synchronized (o2){
                System.out.println("大家好，B拿到了o2的线程锁---现在准备去拿o1的线程锁");
                synchronized (o1){
                    System.out.println("B拿到了o1的线程锁");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
