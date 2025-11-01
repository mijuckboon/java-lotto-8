package lotto.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

/**
 * BousNumber 유효성 검사 클래스
 */
public class BonusNumberValidator {
    /**
     * BonusNumber 생성을 위한 문자열 파싱 검증 메서드
     * @param input 입력 문자열
     */
    public static void validateBonusNumberInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            String message = ErrorMessage.INVALID_INPUT_STRING.format(input);
            throw new CustomException(message);
        }
    }

    /**
     * BonusNumber의 값이 유효한지 검증하는 메서드
     * @param bonusNumber 보너스 번호 값
     * @param lotto 로또 객체
     */
    public static void validateValue(int bonusNumber, Lotto lotto) {
        if (isDuplicate(bonusNumber, lotto)) {
            String message = ErrorMessage.BONUS_NUMBER_ALREADY_CHOOSED.format(bonusNumber, lotto.getNumbers());
            throw new CustomException(message);
        }
        if (hasInvalidRange(bonusNumber)) {
            String message = ErrorMessage.INVALID_RANGE.format(
                    LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER, bonusNumber
            );
            throw new CustomException(message);
        }
    }

    private static boolean isDuplicate(int bonusNumber, Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private static boolean hasInvalidRange(int number) {
        return number < LottoPolicy.MIN_NUMBER || number > LottoPolicy.MAX_NUMBER;
    }
}
