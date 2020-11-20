package practice.concurrency.readwritelock;

import java.util.Random;

/**
 * <b>Code to demonstrate ReadWriteLock usage</b> :
 *
 * @author Vinod Akkepalli
 */
public class ReadWriteLockDemo implements Runnable {

    ReadWriteLockImpl obj;

    public ReadWriteLockDemo(ReadWriteLockImpl obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        //To control access as expected, all threads
        // must try to access same object
        ReadWriteLockImpl obj = new ReadWriteLockImpl();
        for (int i = 0; i < 10; i++) {
            new Thread(new ReadWriteLockDemo(obj)).start();
        }
    }

    @Override
    public void run() {
        int integer;
        integer = new Random().nextInt(2);
        try {
            if (integer == 0) {
                    obj.writeMethod(Thread.currentThread().getName());
            } else {
                    obj.readMethod(Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
