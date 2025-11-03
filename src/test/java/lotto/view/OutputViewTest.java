package lotto.view;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Payment;
import lotto.domain.TotalResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OutputViewTest {

    static Stream<Arguments> totalResultProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new LottoResult(LottoRank.FIRST)),
                        1000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 1개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 0개"
                        ),
                        200_000_000.0
                ),
                Arguments.of(
                        List.of(new LottoResult(LottoRank.SECOND)),
                        1000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 0개"
                        ),
                        3_000_000.0
                ),
                Arguments.of(
                        List.of(new LottoResult(LottoRank.THIRD)),
                        1000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 1개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 0개"
                        ),
                        150_000.0
                ),
                Arguments.of(
                        List.of(new LottoResult(LottoRank.FOURTH)),
                        1000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 1개",
                                "3개 일치 (5,000원) - 0개"
                        ),
                        5_000.0
                ),
                Arguments.of(
                        List.of(new LottoResult(LottoRank.FIFTH)),
                        1000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 1개"
                        ),
                        500.0
                ),
                Arguments.of(
                        List.of(new LottoResult(LottoRank.FIFTH), new LottoResult(null)),
                        2000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 1개"
                        ),
                        250.0
                ),
                Arguments.of(
                        List.of(new LottoResult(null), new LottoResult(null)),
                        2000,
                        List.of(
                                "6개 일치 (2,000,000,000원) - 0개",
                                "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                                "5개 일치 (1,500,000원) - 0개",
                                "4개 일치 (50,000원) - 0개",
                                "3개 일치 (5,000원) - 0개"
                        ),
                        0.0
                )
        );
    }

    @ParameterizedTest(name = "test {index}: ranks={0}, payment={1}")
    @MethodSource("totalResultProvider")
    void getFinalResultMessageParameterizedTest(List<LottoResult> results,
                                                int paymentAmount,
                                                List<String> expectedLines,
                                                double expectedRateOfReturn) {
        TotalResult totalResult = new TotalResult(results);
        Payment payment = new Payment(paymentAmount);

        String message = OutputView.getFinalResultMessage(totalResult, payment);

        for (String expectedLine : expectedLines) {
            assertThat(message).contains(expectedLine);
        }

        assertThat(message).contains("총 수익률은 %s%%입니다.".formatted(
                String.format("%.1f", expectedRateOfReturn)
        ));
    }
}