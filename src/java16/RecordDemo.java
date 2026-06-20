package java16;

/**
 *
 * A Record is a new kind of Java class designed specifically to be a transparent, immutable carrier of data.
 * When you declare a Record, the Java compiler automatically generates the constructor, getters, equals(), hashCode(),
 * and toString() methods for you behind the scenes.
 */

public class RecordDemo {

    static void main() throws InterruptedException {

        recordJava16Method();
        Thread.sleep(1000);
        System.out.println("**************");
        pojoJava8Method();

    }

    private static void recordJava16Method() {
        // 1. Instantiation looks exactly the same
        UserProfileJava16 user1 = new UserProfileJava16("jane_doe", "jane@example.com", 28);
        UserProfileJava16 user2 = new UserProfileJava16("jane_doe", "jane@example.com", 28);

        // 2. Getters don't use the "get" prefix
        System.out.println("Username: " + user1.username());

        // 3. toString() is beautifully formatted automatically
        System.out.println(user1);
        // Output: UserProfile[username=jane_doe, email=jane@example.com, age=28]

        // 4. equals() checks the actual data, not the memory reference
        System.out.println("Are they equal? " + user1.equals(user2));
        // Output: true


        // This will work perfectly and the email will be lowercased automatically
        UserProfileJava16 validUser = new UserProfileJava16("jane_doe", " JANE@EXAMPLE.COM ", 28);
        System.out.println("Valid User: " + validUser);

        try {
            // This will instantly throw our IllegalArgumentException
            UserProfileJava16 invalidUser = new UserProfileJava16("john_doe", "john@example.com", -5);
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to create user: " + e.getMessage());
        }

    }

    private static void pojoJava8Method() {

        // 1. Instantiation
        UserProfileJava8 user1 = new UserProfileJava8("jane_doe", "jane@example.com", 28);
        UserProfileJava8 user2 = new UserProfileJava8("jane_doe", "jane@example.com", 28);

        // 2. Getters
        System.out.println("Username: " + user1.getUsername());

        // 3. toString()
        System.out.println(user1);
        // Output: UserProfile[username=jane_doe, email=jane@example.com, age=28]

        // 4. equals() checks the actual data, not the memory reference
        System.out.println("Are they equal? " + user1.equals(user2));
        // Output: true
    }
}
