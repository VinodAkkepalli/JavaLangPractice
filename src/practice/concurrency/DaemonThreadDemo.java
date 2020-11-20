package practice.concurrency;

/**
 * <b>Description</b> : Daemon threads demo
 *
 * @author Vinod Akkepalli
 */
public class DaemonThreadDemo implements Runnable {

    public static void main(String[] args) {

        Thread daemonThread = new Thread(new DaemonThreadDemo());
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread userThread = new Thread(new DaemonThreadDemo());
        userThread.start();

    }

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName() + " Is a Daemon: "
                    + Thread.currentThread().isDaemon());
    }
}
