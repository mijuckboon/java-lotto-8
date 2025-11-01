package lotto.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 문자열을 입력받는 클래스
 */
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
