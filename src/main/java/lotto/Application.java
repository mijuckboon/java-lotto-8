package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.TotalResult;
import lotto.exception.CustomException;
import lotto.service.LottoPublisher;
import lotto.service.ResultComputer;
import lotto.validator.LottoValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 프로그램의 진입점 클래스
 */
public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    /**
     * 프로그램 실행 메서드
     * Payment, 당첨 번호, 보너스 번호를 입력받아 결과를 출력한다.
     */
    private void run() {
        Payment payment = constructPayment();
        List<Lotto> lottos = publishLottos(payment);

        Lotto winningLotto = constructWinningLotto();

        BonusNumber bonusNumber = constructBonusNumber(winningLotto);
        ResultComputer resultComputer = constructResultComputer(winningLotto, bonusNumber);

        TotalResult totalResult = getTotalResult(resultComputer, lottos);
        printFinalResult(totalResult, payment);
    }

    private Payment constructPayment() {
        while (true) {
            try {
                String paymentInput = getPaymentInput();
                return constructPayment(paymentInput);
            } catch (CustomException e) {
                OutputView.println(e.getMessage());
            }
        }
    }

    private String getPaymentInput() {
        OutputView.printPaymentInputMessage();
        return InputView.receivePaymentInput();
    }

    private Payment constructPayment(String paymentInput) {
        return new Payment(paymentInput);
    }

    private List<Lotto> publishLottos(Payment payment) {
        List<Lotto> lottos = LottoPublisher.publishLottos(payment);
        OutputView.printLottosOutputMessage(lottos);
        return lottos;
    }

    private Lotto constructWinningLotto() {
        while (true) {
            try {
                String winningNumbersInput = getWinningNumbersInput();
                return constructWinningLotto(winningNumbersInput);
            } catch (CustomException e) {
                OutputView.println(e.getMessage());
            }
        }
    }

    private String getWinningNumbersInput() {
        OutputView.printWinningNumbersInputMessage();
        return InputView.receiveWinningNumbersInput();
    }

    private Lotto constructWinningLotto(String winningNumbersInput) {
        List<Integer> winningNumbers = parseWinningNumbers(winningNumbersInput);
        LottoValidator.validate(winningNumbers);
        return new Lotto(winningNumbers);
    }

    private List<Integer> parseWinningNumbers(String winningNumbersInput) {
        LottoValidator.validateNumbersInput(winningNumbersInput);
        String[] splittedWinningNumbers = winningNumbersInput.split(",");
        return Arrays.stream(splittedWinningNumbers)
                .map(Integer::parseInt)
                .toList();
    }

    private ResultComputer constructResultComputer(Lotto winningLotto, BonusNumber bonusNumber) {
        return new ResultComputer(winningLotto, bonusNumber);
    }

    private BonusNumber constructBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                String bonusNumberInput = getBonusNumberInput();
                return constructBonusNumber(bonusNumberInput, winningLotto);
            } catch (CustomException e) {
                OutputView.println(e.getMessage());
            }
        }
    }

    private String getBonusNumberInput() {
        OutputView.printBonusNumberInputMessage();
        return InputView.receiveBonusNumberInput();
    }

    private BonusNumber constructBonusNumber(String bonusNumberInput, Lotto winningLotto) {
        return new BonusNumber(bonusNumberInput, winningLotto);
    }

    private TotalResult getTotalResult(ResultComputer resultComputer, List<Lotto> lottos) {
        return resultComputer.computeTotalResult(lottos);
    }

    private void printFinalResult(TotalResult totalResult, Payment payment) {
        OutputView.printFinalResultMessage(totalResult, payment);
    }

}
