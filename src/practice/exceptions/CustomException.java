package practice.exceptions;

public class CustomException {

    public static void main(String[] args) {

        try {
            foo();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
        try {
            bar();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }

    }

    private static void bar() throws MyException {
        System.out.println("Exception thrown from method bar()");
        throw new MyException("Vinod is great!!");
    }

    private static void foo() throws MyException {

        System.out.println("Exception thrown from method foo()");
        throw new MyException();
    }
}
