package practice.variables;

/**
 * <b>Description</b> : Code to demonstrate that java is always pass by value
 * https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
 *
 * @author Vinod Akkepalli
 */
public class JavaPassByValue {

    public static void main(String[] args) {
        CustomObject carObj = new CustomObject("Car");
        CustomObject lamborghiniObj = carObj;

        modifyReference(carObj);
        if( carObj == lamborghiniObj) {
            System.out.println("Java is pass by value");
            System.out.println("carObj name is: " + carObj.getName());
        }

        changeReference(carObj);
        if( carObj == lamborghiniObj) {
            System.out.println("Java is pass by value");
            System.out.println("carObj name is: " + carObj.getName());
        }
    }

    static void modifyReference(CustomObject carObj) {
        carObj.setName("TataNano");
        System.out.println("inside foo::carObj name is: " + carObj.getName());
    }

    private static void changeReference(CustomObject carObj) {
        carObj = new CustomObject("Bus");
        System.out.println("inside bar::carObj name is: " + carObj.getName());
    }
}


class CustomObject {
    String name;

    public CustomObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}