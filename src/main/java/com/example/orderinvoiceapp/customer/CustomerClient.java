package com.example.orderinvoiceapp.customer;

import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomerClient {
    // get customer data by Customer ID
    Mono<CustomerDTO> getCustomerDataByCustomerId(Long customerId) {
        return Mono.delay(Duration.ofMillis(10))
                .thenReturn(CustomerDTO.builder()
                        .id(customerId)
                        .address("Rua dos Alfeneiros 404")
                        .birthday(ZonedDateTime.of(2000, 6, 3, 4, 2, 12, 10, ZoneId.of("BET")))
                        .name("John Doe")
                        .creditCardNumber("5523165749125199")
                        .build());
    }

    /*
    Response Times: The 3 Important Limits
    by Jakob Nielsen on January 1, 1993

    Summary: There are 3 main time limits (which are determined by human perceptual abilities) to keep in mind when optimizing web and application performance.

    Excerpt from Chapter 5 in my book Usability Engineering, from 1993:

    The basic advice regarding response times has been about the same for thirty years [Miller 1968; Card et al. 1991]:

    0.1 second is about the limit for having the user feel that the system is reacting instantaneously,
    meaning that no special feedback is necessary except to display the result.

    1.0 second is about the limit for the user's flow of thought to stay uninterrupted, even though the user will notice the delay.
    Normally, no special feedback is necessary during delays of more than 0.1 but less than 1.0 second,
    but the user does lose the feeling of operating directly on the data.

    10 seconds is about the limit for keeping the user's attention focused on the dialogue.
    For longer delays, users will want to perform other tasks while waiting for the computer to finish,
    so they should be given feedback indicating when the computer expects to be done.
    Feedback during the delay is especially important if the response time is likely to be highly variable,
    since users will then not know what to expect.
    */
}
