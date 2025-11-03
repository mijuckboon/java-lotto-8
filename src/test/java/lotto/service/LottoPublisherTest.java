package lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Payment;
import org.junit.jupiter.api.Test;

class LottoPublisherTest {

    @Test
    void publishLottosTest() {
        Payment payment = new Payment("2000");
        List<Lotto> lottos = LottoPublisher.publishLottos(payment);
        assertEquals(2, lottos.size());
    }
}