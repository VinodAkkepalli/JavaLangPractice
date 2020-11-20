package practice.concurrency.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <b>ReadWriteLockImpl class using ReadLock & WriteLock to lock objects for access.
 * When a write is in progress, no other read or write allowed
 * Any number of reads can take place simultaneously</b> :
 *
 * @author Vinod Akkepalli
 */
public class ReadWriteLockImpl {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    void readMethod(String name) throws InterruptedException {
        readLock.lock();
        System.out.println(name + " entered read area");
        int integer = new Random().nextInt(10);
        Thread.sleep(integer * 1000);
        System.out.println(name + " exiting read area");
        readLock.unlock();
    }

    void writeMethod(String name) throws InterruptedException {
        writeLock.lock();
        System.out.println(name + " entered write area");
        int integer = new Random().nextInt(10);
        Thread.sleep(integer * 1000);
        System.out.println(name + " exiting write area");
        writeLock.unlock();
    }
}
