package misc.cmd;

public class OverloadMainMethod {
    static void main() {
        System.out.println("From Main method without args");
    }

    public static void main(String[] args) {
        System.out.println("From Main method with args");
    }

    static void main(String[] args, int x) {
        System.out.println("From Main method with args, x= " + x);
    }
}

class Child extends OverloadMainMethod {
    static void main() {
        System.out.println("Child: From Main method without args");
    }

    public static void main(String[] args) {
        System.out.println("Child: From Main method without args");
    }
}