package ma.emsi.salesmanagementservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public String createPaymentIntent(int amount, String currency) throws StripeException {
        Stripe.apiKey = stripeApiKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) amount * 100) // Convert amount to cents
                        .setCurrency(currency)
                        .build();

        PaymentIntent intent = PaymentIntent.create(params);
        return intent.getClientSecret(); // Return client secret to the front-end
    }
}
