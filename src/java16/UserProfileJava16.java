package java16;

public record UserProfileJava16(String username, String email, int age) {

    // This is the Compact Constructor!
    // Notice it has no parameters () and no "this.field = field" assignments.
    public UserProfileJava16 {

        // 1. Validate the age
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative! Provided: " + age);
        }

        // 2. Validate the username
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank!");
        }

        // You can even reassign parameters to clean them up before Java saves them!
        email = email != null ? email.trim().toLowerCase() : null;

        // Once this block finishes, Java automatically assigns:
        // this.username = username;
        // this.email = email;
        // this.age = age;
    }
}
