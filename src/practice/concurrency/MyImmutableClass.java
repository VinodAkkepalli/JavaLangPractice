package practice.concurrency;

public class MyImmutableClass {
    private final int value;

    private MyImmutableClass(int value){
        this.value = value;
    }

    private int getValue(){
        return this.value;
    }

    public MyImmutableClass add(int valueToAdd){
        return new MyImmutableClass(this.value + valueToAdd);
    }


   public static void main(String[] args) {
       MyImmutableClass myImmutableClass = new MyImmutableClass(10);
       MyImmutableClass myImmutableClass1 = myImmutableClass.add(20);

       System.out.println(myImmutableClass);
       System.out.println(myImmutableClass.getValue());

       System.out.println(myImmutableClass1);   //Immutable class will return a different object
       System.out.println(myImmutableClass1.getValue());
   }

    static class Calculator implements Runnable {
        private MyImmutableClass currentValue;

        Calculator(MyImmutableClass currentValue) {
            this.currentValue = currentValue;
        }

        MyImmutableClass getValue(){
            return currentValue;
        }

        @Override
        public void run() {
            this.currentValue = this.currentValue.add(23);
        }

        public static void main(String[] args) {
            MyImmutableClass myImmutableClass = new MyImmutableClass(10);
            Calculator calc1 = new Calculator(myImmutableClass);
//            calc1.setValue(myImmutableClass);
            Calculator calc2 = new Calculator(myImmutableClass.add(23));
//            calc2.setValue(myImmutableClass);

            Thread t1 = new Thread(calc1);
            Thread t2 = new Thread(calc2);
            System.out.println(calc1);
            System.out.println(calc2);
            System.out.println(calc1.currentValue);
            System.out.println(calc2.getValue());
            System.out.println(calc1.currentValue.value);
            System.out.println(calc2.getValue().getValue());
            t1.start();
            t2.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Calculator class is not immutable. So, not thread safe
            //calc1, calc2 object same but value changed
            System.out.println(calc1);
            System.out.println(calc2);
            System.out.println(calc1.currentValue);
            System.out.println(calc2.currentValue);
            System.out.println(calc1.currentValue.value);
            System.out.println(calc2.currentValue.value);
        }

    }

}
