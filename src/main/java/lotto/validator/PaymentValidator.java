package lotto.validator;

import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

/**
 * 지불 금액 검증 클래스
 */
public class PaymentValidator {
    /**
     * Payment 생성을 위한 입력 문자열 파싱 검증 메서드
     * @param input 입력 문자열
     */
    public static void validatePaymentInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            String message = ErrorMessage.INVALID_PAYMENT_INPUT.format(LottoPolicy.LOTTO_PRICE, input);
            throw new CustomException(message);
        }
    }

    /**
     * 지불 금액 값 검증 메서드
     * @param payment 지불 금액
     */
    public static void validatePaymentValue(int payment) {
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
