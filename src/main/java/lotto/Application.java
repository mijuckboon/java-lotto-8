package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.validator.BonusNumberValidator;
import lotto.validator.LottoValidator;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        String paymentInput = Console.readLine();
        LottoPublisher lottoPublisher = new LottoPublisher();
        List<Lotto> lottos = lottoPublisher.publishLottos(paymentInput);
        System.out.println("%d개를 구매했습니다.".formatted(lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println("당첨 번호를 입력해 주세요.");
        String numbersInput = Console.readLine();
        List<Integer> winningNumbers = Arrays.stream(numbersInput.split(",")).map(Integer::parseInt).toList();
        LottoValidator.validate(winningNumbers);
        Lotto winningLotto = new Lotto(winningNumbers);

        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumberInput = Console.readLine();
        BonusNumberValidator.validateBonusNumber(Integer.parseInt(bonusNumberInput), winningLotto);

    }

    /*
    당첨 번호를 입력해 주세요.
    1,2,3,4,5,6

    보너스 번호를 입력해 주세요.
    7

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
