package lotto.domain;

import lotto.validator.PaymentValidator;

public class Payment {
    int amount;

    public Payment(String paymentInput) {
        PaymentValidator.validatePaymentInput(paymentInput);
        int parsedPayment = Integer.parseInt(paymentInput);
        PaymentValidator.validateAmountOfPayment(parsedPayment);
        this.amount = parsedPayment;
    }

    public int getAmount() {
        return amount;
    }

    public int getLottoAmount() {
        return amount / LottoPolicy.LOTTO_PRICE;
    }
}
