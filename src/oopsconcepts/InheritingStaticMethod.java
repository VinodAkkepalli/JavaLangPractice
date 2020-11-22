package oopsconcepts;

class A {
    static void myStaticFun() {
        System.out.println("A::myStaticFun()");
    }

    void myFun() {
        System.out.println("a.myFun()");
    }
}

class B extends A {
    static void myStaticFun() {
        System.out.println("B::myStaticFun()");
    }

    void myFun() {
        System.out.println("b.myFun()");
    }
}

public class InheritingStaticMethod {

    public static void main(String[] args) {
        System.out.println("General invocation of static methods");
        A.myStaticFun();
        B.myStaticFun();

        System.out.println("The variable type decides the method being invoked, not the assigned object type");
        A a = new B();
        a.myStaticFun();

        B b = new B();
        b.myStaticFun();

        System.out.println("For non static methods, assigned object type decides");
        a.myFun();
    }
}
