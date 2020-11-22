package oopsconcepts;

public class InitializationBlocks {

    static {
        System.out.println("Static Initialization Block; executed only once per class; even if no object is created");
    }

    {
        System.out.println("Instance Initialization Block executed for Object: " + this.toString());
    }

    private InitializationBlocks() {
        System.out.println("Constructor for Object: " + this.toString());
    }

    public static void main(String[] args) {

        new InitializationBlocks();
        new InitializationBlocks();
    }
}
