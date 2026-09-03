import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Demonstrates how CompletableFuture can run multiple tasks asynchronously
 * and combine their results when all tasks are complete.
 *
 * In this example, processed and pending transactions are fetched at the same
 * time. The two lists are then merged into one final list. The main thread
 * waits only when it needs to read the combined result.
 */
record Transaction(String id, String status, double amount) {}

/** Runs an example of combining two asynchronous transaction fetches. */
public class TransactionAggregator {

    public static void main(String[] args) {
        System.out.println("Starting transaction fetch...");

        // supplyAsync starts getProcessedTransactions on a background thread.
        // It immediately returns a CompletableFuture representing the future result.
        CompletableFuture<List<Transaction>> processedFuture = 
            CompletableFuture.supplyAsync(TransactionAggregator::getProcessedTransactions);

        // This fetch also starts in the background, so both fetches can run in parallel.
        CompletableFuture<List<Transaction>> pendingFuture = 
            CompletableFuture.supplyAsync(TransactionAggregator::getPendingTransactions);

        // thenCombine waits for both futures to finish and passes both results
        // to this function. The returned future contains the merged list.
        CompletableFuture<List<Transaction>> combinedFuture = 
            processedFuture.thenCombine(pendingFuture, (processedList, pendingList) -> {
                // Create a new list so the original results are not modified.
                List<Transaction> allTransactions = new ArrayList<>();
                allTransactions.addAll(processedList);
                allTransactions.addAll(pendingList);
                return allTransactions;
            });

        try {
            // get() waits for the combined asynchronous operation to finish.
            // It can throw InterruptedException or ExecutionException.
            List<Transaction> finalResult = combinedFuture.get();
            
            System.out.println("\n--- Final Combined Result ---");
            finalResult.forEach(System.out::println);
            
        } catch (InterruptedException | ExecutionException e) {
            // ExecutionException wraps an exception thrown by an async task.
            System.err.println("Error fetching transactions: " + e.getMessage());
        }
    }

    /** Simulates loading processed transactions from a database. */

    private static List<Transaction> getProcessedTransactions() {
        simulateDelay(1000);
        System.out.println("✔ Processed transactions fetched by: " + Thread.currentThread().getName());
        return List.of(
            new Transaction("TXN-001", "PROCESSED", 1500.00),
            new Transaction("TXN-002", "PROCESSED", 250.50)
        );
    }

    /** Simulates loading pending transactions from an external service. */

    private static List<Transaction> getPendingTransactions() {
        simulateDelay(1500);
        System.out.println("✔ Pending transactions fetched by: " + Thread.currentThread().getName());
        return List.of(
            new Transaction("TXN-003", "PENDING", 75.00)
        );
    }

    /**
     * Represents slow I/O, such as a database or network call.
     * Sleeping here is only for demonstration purposes.
     */
    private static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Preserve the interruption so calling code can respond to it.
            Thread.currentThread().interrupt();
        }
    }
}