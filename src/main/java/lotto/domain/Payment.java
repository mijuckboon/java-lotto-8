package lotto.domain;

import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;
import lotto.validator.PaymentValidator;

public class Payment {
    int amount;

    public Payment(String paymentInput) {
        try {
            PaymentValidator.validatePaymentInput(paymentInput);
            int parsedPayment = Integer.parseInt(paymentInput);
            PaymentValidator.validatePaymentValue(parsedPayment);
            this.amount = parsedPayment;
        } catch (RuntimeException e) {
            String message = ErrorMessage.INVALID_INPUT_STRING.format(paymentInput);
            throw new CustomException(message);
        }
    }

    public Payment(int amount) {
        PaymentValidator.validatePaymentValue(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoAmount() {
        return amount / LottoPolicy.LOTTO_PRICE;
    }
}
