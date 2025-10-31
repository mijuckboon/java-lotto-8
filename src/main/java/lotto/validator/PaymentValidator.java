package lotto.validator;

import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

public class PaymentValidator {

    public static void validatePaymentInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            String message = ErrorMessage.INVALID_PAYMENT_INPUT.format(LottoPolicy.LOTTO_PRICE, input);
            throw new CustomException(message);
        }
    }

    public static void validateAmountOfPayment(int payment) {
        if (!isValidAmount(payment)) {
            String message = ErrorMessage.INVALID_PAYMENT_INPUT.format(LottoPolicy.LOTTO_PRICE, payment);
            throw new CustomException(message);
        }
        if (!isInLimit(payment)) {
            String message = ErrorMessage.PAYMENT_LIMIT_EXCEED.format(LottoPolicy.MAX_PAYMENT, payment);
            throw new CustomException(message);
        }
    }

    private static boolean isValidAmount(int payment) {
        return payment % LottoPolicy.LOTTO_PRICE == 0 && payment > 0;
    }

    private static boolean isInLimit(int payment) {
        return payment <= LottoPolicy.MAX_PAYMENT;
    }
}
