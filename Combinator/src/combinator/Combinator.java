package combinator;

import java.time.LocalDate;

public class Combinator {

    public static void main(String[] args) {

        Customer alice = new Customer("Alice", "alice@gmail.com", "+905554443322", LocalDate.of(2000, 1, 1));

//        CustomerValidator validator = new CustomerValidator();
//        boolean result = validator.isValid(alice);
//        System.out.println(result);

        CustomerRegistrationValidator.ValidationResult result = CustomerRegistrationValidator
                .isEmailValid()
                .and(CustomerRegistrationValidator.isPhoneNumberValid())
                .and(CustomerRegistrationValidator.isAdult())
                .apply(alice);

        if (result != CustomerRegistrationValidator.ValidationResult.SUCCESS){
            throw new IllegalArgumentException(result.name());
        }
    }

}
