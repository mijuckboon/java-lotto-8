package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import lotto.domain.TotalResult;
import lotto.service.LottoPublisher;
import lotto.service.ResultComputer;
import lotto.validator.BonusNumberValidator;
import lotto.validator.LottoValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    private void run() {
        String paymentInput = getPaymentInput();
        Payment payment = new Payment(paymentInput);
        List<Lotto> lottos = publishLottos(payment);

        String winningNumbersInput = getWinningNumbersInput();
        Lotto winningLotto = constructWinningLotto(winningNumbersInput);

        String bonusNumberInput = getBonusNumberInput();
        BonusNumber bonusNumber = constructBonusNumber(bonusNumberInput, winningLotto);
        ResultComputer resultComputer = constructResultComputer(winningLotto, bonusNumber);

        TotalResult totalResult = getTotalResult(resultComputer, lottos);
        printFinalResult(totalResult, payment);
    }

    private String getPaymentInput() {
        OutputView.printPaymentInputMessage();
        return InputView.receivePaymentInput();
    }

    private List<Lotto> publishLottos(Payment payment) {
        List<Lotto> lottos = LottoPublisher.publishLottos(payment);
        OutputView.printLottosOutputMessage(lottos);
        return lottos;
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
        String[] splittedWinningNumbers = winningNumbersInput.split(",");
        return Arrays.stream(splittedWinningNumbers)
                .map(Integer::parseInt)
                .toList();
    }

    private ResultComputer constructResultComputer(Lotto winningLotto, BonusNumber bonusNumber) {
        return new ResultComputer(winningLotto, bonusNumber);
    }

    private String getBonusNumberInput() {
        OutputView.printBonusNumberInputMessage();
        return InputView.receiveBonusNumberInput();
    }

    private BonusNumber constructBonusNumber(String bonusNumberInput, Lotto winningLotto) {
        int parsedInput = Integer.parseInt(bonusNumberInput);
        BonusNumberValidator.validateBonusNumber(parsedInput, winningLotto);
        return new BonusNumber(parsedInput, winningLotto);
    }

    private TotalResult getTotalResult(ResultComputer resultComputer, List<Lotto> lottos) {
        return resultComputer.computeResult(lottos);
    }

    private void printFinalResult(TotalResult totalResult, Payment payment) {
        OutputView.printFinalResultMessage(totalResult, payment);
    }

}
