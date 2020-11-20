package practice.java8;

/**
 * <b>Description</b> : Static & Default methods of Functional Interfaces
 *  Diamond problem in case of multiple interface implementations
 *
 * @author Vinod Akkepalli
 */

@FunctionalInterface
interface MyFunctionalInterface1 {
    // Single abstract method
    void abstractMethod();

    // static methods
    static void method1(){
        System.out.println("MyFunctionalInterface1::Method1 Static");
    }
    static void method3(){
        System.out.println("MyFunctionalInterface1::Method3 Static");
    }

    // Default methods
    default void method2(){
        System.out.println("MyFunctionalInterface1::Method2 Default");
    }

    default void method4(){
        System.out.println("MyFunctionalInterface1::Method4 Default");
    }

    // Method of Object class declared as Abstract method does not count
    boolean equals(Object o);
}

@FunctionalInterface
interface MyFunctionalInterface2 {
    void abstractMethod2();

    // Default method
    default void method2(){
        System.out.println("MyFunctionalInterface2::Method2 Default");
    }
}

// Diamond problem arises when we don't inherit common default method method2
class ClassForDiamondProblemDemo implements MyFunctionalInterface1, MyFunctionalInterface2 {

    @Override
    public void abstractMethod() {
        System.out.println("ClassForDiamondProblemDemo::abstractMethod()");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("ClassForDiamondProblemDemo::abstractMethod2()");
    }

    public void accessParentInterfaceMethods() {
        MyFunctionalInterface1.method3();   //to access static methods of implemented interface
        MyFunctionalInterface1.super.method2(); //way to access default methods of implemented Interface
    }

    @Override
    public void method2() {
        System.out.println("ClassForDiamondProblemDemo::method2()");
    }
}

public class FunctionalInterfacesStaticDefaultMethods {

    public static void main(String[] args) {
        // Lambda function using MyFunctionalInterface
        MyFunctionalInterface1 mfi = () -> System.out.println("MyFunctionalInterface1 implemented");
        mfi.abstractMethod();

        mfi.method2();  // Default method accessed
        MyFunctionalInterface1.method1();    //Static method accessed


        ClassForDiamondProblemDemo cdp = new ClassForDiamondProblemDemo();
        cdp.abstractMethod();
        cdp.abstractMethod2();
        cdp.method2();
        cdp.accessParentInterfaceMethods();
    }
}