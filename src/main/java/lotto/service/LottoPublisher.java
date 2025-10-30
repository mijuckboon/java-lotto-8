package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPolicy;
import lotto.validator.InputValidator;

public class LottoPublisher {
    public static List<Lotto> publishLottos(String input) {
        int payment = parsePaymentInput(input);
        InputValidator.validateAmountOfPayment(payment);
        int lottoAmount = computeLottoAmount(payment);
        return publishLottosWithAmount(lottoAmount);
    }

    private static List<Lotto> publishLottosWithAmount(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(publishLotto());
        }
        return lottos;
    }

    private static int computeLottoAmount(int payment) {
        return payment / LottoPolicy.LOTTO_PRICE;
    }

    private static int parsePaymentInput(String input) {
        InputValidator.validatePaymentInput(input);
        return Integer.parseInt(input);
    }

    private static Lotto publishLotto() {
        List<Integer> numbers = pickLottoNumbers();
        List<Integer> sortedNumbers = sorted(numbers);
        return new Lotto(sortedNumbers);
    }

    private static List<Integer> sorted(List<Integer> numbers) {
        return numbers.stream().sorted().toList();
    }

    private static List<Integer> pickLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER, LottoPolicy.NUMBER_COUNT_TO_CHOOSE
        );
    }
}
