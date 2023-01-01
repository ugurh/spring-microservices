package io.ugurh.userservice.query;

import io.ugurh.core.models.PaymentDetails;
import io.ugurh.core.models.User;
import io.ugurh.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {

    @QueryHandler
    public User getUserPaymentDetails(FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery){
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("151 41415 1515")
                .cvv("251")
                .name("HARUN UĞUR")
                .getValidUntilYear(2029)
                .validUntilMonth(11)
                .build();

        return User.builder()
                .userId(fetchUserPaymentDetailsQuery.getUserId())
                .firstName("HARUN")
                .lastName("UĞUR")
                .paymentDetails(paymentDetails)
                .build();
    }
}
