import java.util.function.*;

public class FunctionalInterfaces {

    public static void main(String[] args) {

        //! FunctionalInterface

        //? Function
        Function<Integer, Integer> incrementByOne = number -> ++number;
        int result = incrementByOne.apply(9);
        System.out.println(result);

        Function<Integer, Integer> multiplyBy10 = number -> number * 10;
        result = multiplyBy10.apply(result);
        System.out.println(result);

        Function<Integer, Integer> incrementAndMultiply = incrementByOne.andThen(multiplyBy10);
        result = incrementAndMultiply.apply(9);
        System.out.println(result);

        //? BiFunction
        BiFunction<Integer, Integer, Float> doSomeMathOperations = (num1, num2) -> (float) ((num1 + 1) * num2);
        Float fResult = doSomeMathOperations.apply(9, 10);
        System.out.println(fResult);

        //? Consumer
        Consumer<Customer> greetCustomer = customer -> System.out.println("Hello " + customer.name + ". Your phone number is " + customer.phoneNumber);
        greetCustomer.accept(new Customer("Mahmut Can", "123456789"));

        //? BiConsumer
        BiConsumer<Customer, Boolean> greetCustomer2 = (customer, bool) ->  System.out.println("Hello " + customer.name + ". Your phone number is " + (bool ? customer.phoneNumber : "--- --- ---"));
        greetCustomer2.accept(new Customer("Mehmet Efendi", "123456789"), false);

        //? Predicate
        Predicate<String> isPhoneNumberValid = phoneNumber -> phoneNumber.startsWith("+90");
        Predicate<String> isPhoneNumberValid2 = phoneNumber -> phoneNumber.length() == 13;
        boolean bResult = isPhoneNumberValid.and(isPhoneNumberValid2).test("+905554443322");
        System.out.println(bResult);

        //? Supplier
        Supplier<String> getDatabaseUrl = () -> "jdbc://localhost:5432/database";
        System.out.println(getDatabaseUrl.get());

    }

    static class Customer{
        private final String name;
        private final String phoneNumber;

        Customer(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }

}
