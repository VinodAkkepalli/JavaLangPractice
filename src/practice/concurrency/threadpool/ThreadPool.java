package practice.concurrency.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <b>Description</b> : ThreadPool implementation using BlockingQueue and worker threads
 *
 *
 * @author Vinod Akkepalli
 */

public class ThreadPool {
    private final BlockingQueue<Runnable> queue;

    public ThreadPool(int nThreads) {
        queue = new LinkedBlockingQueue<>();
        PoolWorker[] threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
            queue.offer(task);
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable task;

            while (!queue.isEmpty()) {

                task = queue.poll();

                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(7);

        for (int i = 0; i < 100; i++) {
            MyTask task = new MyTask(i);
            pool.execute(task);
        }
    }

}
