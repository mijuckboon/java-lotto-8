package lotto.domain;

import lotto.validator.BonusNumberValidator;

public class BonusNumber {
    int number;

    public BonusNumber(String bonusNumberInput, Lotto lotto) {
        BonusNumberValidator.validateBonusNumberInput(bonusNumberInput);
        int number = Integer.parseInt(bonusNumberInput);
        BonusNumberValidator.validateValue(number, lotto);
        this.number = number;
    }

    public BonusNumber(int number, Lotto lotto) {
        BonusNumberValidator.validateValue(number, lotto);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
