package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPolicy;
import lotto.domain.Payment;

/**
 * 로또 발행을 담당하는 클래스
 */
public class LottoPublisher {
    /**
     * 지불 금액을 입력받아 로또를 발행하는 메서드
     * @param payment 지불 금액 객체
     * @return 발행한 로또 목록
     */
    public static List<Lotto> publishLottos(Payment payment) {
        int lottoAmount = payment.getLottoAmount();
        return publishLottosWithAmount(lottoAmount);
    }

    private static List<Lotto> publishLottosWithAmount(int amount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(publishLotto());
        }
        return lottos;
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
