package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static String receivePaymentInput() {
        return readLine();
    }

    public static String receiveWinningNumbersInput() {
        return readLine();
    }

    public static String receiveBonusNumberInput() {
        return readLine();
    }

    private static String readLine() {
        return Console.readLine();
    }
}
