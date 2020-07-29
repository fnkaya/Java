import java.util.function.Consumer;

public class Callback {

    public static void main(String[] args) {

        greet("Furkan", null, System.out::println);

        greet2("Furkan", null, System.out::println);

    }

    private static void greet(String firstname, String lastname, Consumer<String> callback){
        System.out.println(firstname);
        if (lastname != null){
            System.out.println(lastname);
        }
        else {
            callback.accept("no lastname provided for " + firstname);
        }
    }

    private static void greet2(String firstname, String lastname, Runnable callback){
        System.out.println(firstname);
        if (lastname != null){
            System.out.println(lastname);
        }
        else {
            callback.run();
        }
    }

}
