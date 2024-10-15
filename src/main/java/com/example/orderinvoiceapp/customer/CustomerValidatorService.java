package com.example.orderinvoiceapp.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerValidatorService {
    private final CustomerClient customerClient;

    public Mono<Boolean> validate(Long customerId) {
        return customerClient.getCustomerDataByCustomerId(customerId)
                .map(customerDTO -> isValidCreditCard(customerDTO.getCreditCardNumber()));
    }

    private static Boolean isValidCreditCard(String cardNumber) {
        // Luhn Algorithm for CC validation
        // from https://medium.com/@veerujadhav879/validating-credit-card-numbers-using-the-luhn-algorithm-in-java-3fd58e276744#:~:text=The%20Java%20implementation%20of%20the,total%20is%20divisible%20by%2010.

        int sum = 0;
        boolean alternate = false;

        // Iterate from right to left
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));

            // Double every second digit
            if (alternate) {
                n *= 2;
                // If the result is greater than 9, subtract 9
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate; // Flip the alternate flag
        }

        // The number is valid if the total sum is a multiple of 10
        return (sum % 10 == 0);
    }
}
