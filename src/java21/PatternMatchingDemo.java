package java21;

/**
 *
 * Pattern matching is simply the ability to test if an object matches a specific "shape" or type, and if it does,
 * immediately extract its data into variables in a single, safe step.
 *
 * Historically, Java required a two-step dance:
 * The Test: Check the type (e.g., if (obj instanceof String)).
 * The Extraction: Manually cast it to a new variable (e.g., String s = (String) obj;).
 *
 * Java 21 introduces two massive upgrades:
 * Pattern Matching for instanceof: You declare the variable right inside the if condition.
 * Pattern Matching for switch (with Record Patterns): You can pass an object into a switch, check its type,
 * and instantly unpack its internal fields (like the ones in our Records!) all at once.
 */

public class PatternMatchingDemo {

    static void main() {

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent("Vinod", "1@2.com");
        UserSuspendedEvent userSuspendedEvent = new UserSuspendedEvent("Srinu", 23);
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent("Raj", "Bad boy");

        processEventIfElse(userCreatedEvent);
        processEventIfElse(userSuspendedEvent);
        processEventIfElse(userDeletedEvent);

        System.out.println("***********************");

        System.out.println( processEventSwitch(userCreatedEvent) );
        System.out.println( processEventSwitch(userSuspendedEvent) );
        System.out.println( processEventSwitch(userDeletedEvent) );
        System.out.println( processEventSwitch(new Object()) );
    }

    public static void processEventIfElse(Object event) {

        // Java 21: Test the type AND declare the variable 'created' in one step!
        if (event instanceof UserCreatedEvent created) {
            // No casting required! We can use 'created' directly.
            System.out.println("Welcome new user: " + created.username());

        } else if (event instanceof UserDeletedEvent deleted) {
            System.out.println("Goodbye " + deleted.username() + ". Reason: " + deleted.reason());

        } else if (event instanceof UserSuspendedEvent suspendedEvent) {
            System.out.println(suspendedEvent.username() + " suspended for " + suspendedEvent.durationDays() + " days");
        }
    }


    public static String processEventSwitch(Object event) {

        // Java 21 Switch Expression: It returns a value directly!
        return switch (event) {

            // Record Pattern: It checks the type AND extracts the internal variables!
            case UserCreatedEvent(var name, var email) ->
                    "Sending welcome email to: " + email;

            case UserDeletedEvent(var name, var reason) ->
                    "Deactivating account for " + name + " due to: " + reason;

            // Guard Clause (when): We can add conditional logic right inside the case!
            case UserSuspendedEvent(var name, var days) when days > 30 ->
                    "Alert Admin! Long suspension of " + days + " days for " + name;

            case UserSuspendedEvent(var name, var days) ->
                    "Standard suspension for " + name;

            // We must provide a default case so the switch is "exhaustive"
            default ->
                    "Unknown event received.";
        };
    }
}
