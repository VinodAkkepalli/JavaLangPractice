/*
package java21;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.Duration;

*/
/**
 * 1. RECORDS (Data)
 * We define our incoming request DTO.
 * The Compact Constructor instantly validates the age before the controller even processes it.
 *//*

record RegistrationRequest(String username, String email, int age) {
    public RegistrationRequest {
        if (age < 18) {
            throw new IllegalArgumentException("User must be 18 or older to register.");
        }
        email = email != null ? email.trim().toLowerCase() : null;
    }
}

// A simple record to hold the created user's data
record UserProfile(String id, String username, String email) {}

*/
/**
 * 2. SEALED CLASSES (Safety)
 * We lock down the possible outcomes of our registration process.
 * ONLY these three specific records are allowed to be a RegistrationResult.
 *//*

sealed interface RegistrationResult permits Success, ValidationError, SystemFailure {}

record Success(UserProfile profile) implements RegistrationResult {}
record ValidationError(String reason) implements RegistrationResult {}
record SystemFailure(String errorCode, Exception cause) implements RegistrationResult {}

*/
/**
 * THE CONTROLLER
 *//*

@RestController
@RequestMapping("/api/users")
public class ModernUserController {

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {

        // 3. VIRTUAL THREADS (Performance)
        // With 'spring.threads.virtual.enabled=true' in your properties,
        // Tomcat automatically assigns a lightweight virtual thread to this request.
        System.out.println("Processing registration on: " + Thread.currentThread());

        // We call our service logic
        RegistrationResult result = processRegistrationLogic(request);

        // 4. PATTERN MATCHING (Logic)
        // We use an exhaustive switch expression to route the result to the correct HTTP status.
        return switch (result) {

            // We match the type AND extract the 'profile' variable instantly
            case Success(var profile) ->
                    ResponseEntity.ok(profile);

            // We extract the 'reason' and return a 400 Bad Request
            case ValidationError(var reason) ->
                    ResponseEntity.badRequest().body("Registration Failed: " + reason);

            // We extract the 'errorCode' and 'cause' to return a 500 Internal Server Error
            case SystemFailure(var code, var cause) ->
                    ResponseEntity.internalServerError()
                            .body("System Error [" + code + "]: " + cause.getMessage());

            // Notice: NO default branch! The compiler knows we handled all permitted subclasses.
        };
    }

    */
/**
     * Simulates the business logic, such as checking a database and saving the user.
     *//*

    private RegistrationResult processRegistrationLogic(RegistrationRequest request) {
        try {
            // SIMULATING I/O WAITING (Database call)
            // Because we are on a Virtual Thread, Thread.sleep() does NOT block an OS thread.
            // The virtual thread safely unmounts here, freeing up the server for other users!
            Thread.sleep(Duration.ofMillis(800));

            // Basic business validation
            if (request.username().isBlank()) {
                return new ValidationError("Username cannot be blank.");
            }

            // Success! Create the profile and return it.
            var newProfile = new UserProfile(
                    "USR-" + Instant.now().toEpochMilli(),
                    request.username(),
                    request.email()
            );

            return new Success(newProfile);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new SystemFailure("DB-TIMEOUT-504", e);
        } catch (Exception e) {
            return new SystemFailure("SYS-500", e);
        }
    }
}*/
