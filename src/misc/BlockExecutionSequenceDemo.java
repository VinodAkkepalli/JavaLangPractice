package misc;

public class BlockExecutionSequenceDemo {

    static {
        System.out.println("Static Block Execution");
    }

    {
        System.out.println("Non Static Block Execution");
    }

    public BlockExecutionSequenceDemo() {
        System.out.println("Constructor Execution");
    }

    public void someMethod() {
        System.out.println("Some Message");
    }

    public static void main(String[] args) {
        System.out.println("Main Method Execution");
        BlockExecutionSequenceDemo demo = new BlockExecutionSequenceDemo();
        demo.someMethod();
    }

}
