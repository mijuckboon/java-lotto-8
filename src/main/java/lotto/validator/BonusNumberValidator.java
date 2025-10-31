package lotto.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

public class BonusNumberValidator {
    public static void validateBonusNumberInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            String message = ErrorMessage.INVALID_INPUT_STRING.format(input);
            throw new CustomException(message);
        }
    }

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
