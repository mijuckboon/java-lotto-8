package lotto.validator;

import lotto.domain.Lotto;
import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

public class BonusNumberValidator {
    public static void validateBonusNumber(int bonusNumber, Lotto lotto) {

        if (isDuplicate(bonusNumber, lotto)) {
            throw new CustomException(ErrorMessage.BONUS_NUMBER_ALREADY_CHOOSED.format(bonusNumber, lotto.getNumbers()));
        }
        if (hasInvalidRange(bonusNumber)) {
            throw new CustomException(ErrorMessage.INVALID_RANGE.format(bonusNumber, lotto.getNumbers()));
        }
    }

    private static boolean isDuplicate(int bonusNumber, Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    private static boolean hasInvalidRange(int number) {
        return number < LottoPolicy.MIN_NUMBER || number > LottoPolicy.MAX_NUMBER;
    }
}
