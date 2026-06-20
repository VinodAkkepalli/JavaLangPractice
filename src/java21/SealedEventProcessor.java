package java21;

/**
 *
 * Before Sealed Classes, if you created a public interface UserEvent, literally any other class in your
 * entire application (or even in another developer's library) could implement it.
 * Because of this, the Java compiler could never guarantee it knew about every possible event type.
 *
 * When you used a switch statement on an interface, the compiler forced you to add a default branch.
 * It basically said: "I know you handled Created, Deleted, and Suspended, but someone might create
 * an UnknownEvent tomorrow, so you must have a default fallback!"
 *
 * Sealed Classes allow you to lock down your hierarchy. You explicitly tell the compiler:
 * "This interface is sealed. ONLY these three specific classes are allowed to implement it. Period."
 * Because the compiler now knows the exact, exhaustive list of possibilities, it performs Exhaustiveness
 * Checking on your switch expressions. If you cover all permitted classes, you can safely delete the default branch!
 *
 */

public class SealedEventProcessor {

    static void main() {
        System.out.println(processEvent( new UserCreatedEvent("akke", "10@20.com")));
    }

    // Notice we pass in the sealed interface 'UserEvent'
    public static String processEvent(UserEvent event) {

        return switch (event) {
            case UserCreatedEvent(var name, var email) ->
                    "Sending welcome email to: " + email + " for " + name;

            case UserDeletedEvent(var name, var reason) ->
                    "Deactivating account for " + name + " due to: " + reason;

            case UserSuspendedEvent(var name, var days) when days > 30 ->
                    "Alert Admin! Long suspension of " + days + " days for " + name;

            case UserSuspendedEvent(var name, var days) ->
                    "Standard suspension for " + name;

            // NO DEFAULT BRANCH!
            // The compiler knows there are absolutely no other possible events.
        };
    }
}