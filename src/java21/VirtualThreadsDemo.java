package java21;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class VirtualThreadsDemo {
    static void main(String[] args) {

        virtualThreadsMethod();
        System.out.println("****************");
        carrierThreadsMethod();
    }

    private static void virtualThreadsMethod() {

        System.out.println("VirtualThreads: Starting the massive task simulation...");

        long start = System.currentTimeMillis();

        // We use the new Java 21 executor for Virtual Threads
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Submitting 10,000 concurrent tasks
            IntStream.range(0, 10000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        // Simulate a 1-second I/O blocking operation
                        Thread.sleep(Duration.ofSeconds(1));

                        // Print the first and last task to see the thread names
                        if (i == 0 || i == 9999) {
                            System.out.println("Task " + i + " completed by: " + Thread.currentThread());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });

        } // The try-with-resources block automatically waits for all tasks to finish

        long end = System.currentTimeMillis();
        System.out.println("VirtualThreads: Total Execution Time: " + (end - start) + " ms");
    }

    private static void carrierThreadsMethod() {
        System.out.println("CarrierThreads: Starting the massive task simulation...");

        long start = System.currentTimeMillis();

        // We use the new Java 21 executor for Virtual Threads
        try (var executor = Executors.newFixedThreadPool(1000)) {

            // Submitting 10,000 concurrent tasks
            IntStream.range(0, 10000).forEach(i -> {
                executor.submit(() -> {
                    try {
                        // Simulate a 1-second I/O blocking operation
                        Thread.sleep(Duration.ofSeconds(1));

                        // Print the first and last task to see the thread names
                        if (i == 0 || i == 9999) {
                            System.out.println("Task " + i + " completed by: " + Thread.currentThread());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            });

        } // The try-with-resources block automatically waits for all tasks to finish

        long end = System.currentTimeMillis();
        System.out.println("CarrierThreads: Total Execution Time: " + (end - start) + " ms");
    }
}