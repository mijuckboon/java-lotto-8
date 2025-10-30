package lotto;

import lotto.validator.BonusNumberValidator;

public class BonusNumber {
    int number;

    public BonusNumber(int number, Lotto lotto) {
        BonusNumberValidator.validateBonusNumber(number, lotto);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
