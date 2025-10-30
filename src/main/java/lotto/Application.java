package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.TotalResult;
import lotto.service.LottoPublisher;
import lotto.service.ResultComputer;
import lotto.validator.BonusNumberValidator;
import lotto.validator.LottoValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printPaymentInputMessage();
        String paymentInput = InputView.receivePaymentInput();
        LottoPublisher lottoPublisher = new LottoPublisher();
        List<Lotto> lottos = lottoPublisher.publishLottos(paymentInput);
        OutputView.printLottosOutputMessage(lottos);

        OutputView.printWinningNumbersInputMessage();
        String winningNumbersInput = InputView.receiveWinningNumbersInput();
        List<Integer> winningNumbers = Arrays.stream(winningNumbersInput.split(",")).map(Integer::parseInt).toList();
        LottoValidator.validate(winningNumbers);
        Lotto winningLotto = new Lotto(winningNumbers);

        OutputView.printBonusNumberInputMessage();
        String bonusNumberInput = InputView.receiveBonusNumberInput();
        int bonusNumber = Integer.parseInt(bonusNumberInput);
        BonusNumberValidator.validateBonusNumber(bonusNumber, winningLotto);

        ResultComputer resultComputer = new ResultComputer(winningLotto, new BonusNumber(bonusNumber, winningLotto));
        TotalResult totalResult = resultComputer.computeResult(lottos);
        long payment = Long.parseLong(paymentInput);
        OutputView.printFinalResultMessage(totalResult, payment);
    }

    /*

    당첨 통계
    ---
    3개 일치 (5,000원) - 1개
    4개 일치 (50,000원) - 0개
    5개 일치 (1,500,000원) - 0개
    5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
    6개 일치 (2,000,000,000원) - 0개
    총 수익률은 62.5%입니다.
    * */
}
