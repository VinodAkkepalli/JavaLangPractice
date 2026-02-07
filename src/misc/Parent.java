package misc;

public class Parent {
    public static void main(String[] args) {
        System.out.println("From Main method with args");
    }

    public static void main(String[] args, int num) {
        System.out.println("From Main method with args, num");
    }
}
