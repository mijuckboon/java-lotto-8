package lotto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.TotalResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultComputerTest {
    Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    BonusNumber bonusNumber = new BonusNumber("7", winningLotto);
    ResultComputer resultComputer = new ResultComputer(winningLotto, bonusNumber);

    static Stream<Arguments> lottoAndExpectedRankProvider() {
        Map<LottoRank, Integer> noGains = new HashMap<>();
        noGains.put(null, 2);

        Map<LottoRank, Integer> oneFifthRankAndNoGain = new HashMap<>();
        oneFifthRankAndNoGain.put(LottoRank.FIFTH, 1);
        oneFifthRankAndNoGain.put(null, 1);


        return Stream.of(
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6))), Map.of(LottoRank.FIRST, 1)
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7))), Map.of(LottoRank.SECOND, 1)
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 5, 8))), Map.of(LottoRank.THIRD, 1)
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 4, 7, 8))), Map.of(LottoRank.FOURTH, 1)
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 8, 9, 10))), Map.of(LottoRank.FIFTH, 1)
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 8, 9, 10, 11)), new Lotto(List.of(7, 8, 9, 10, 11, 12))),
                        noGains
                ),
                Arguments.of(
                        List.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)), new Lotto(List.of(1, 41, 42, 43, 44, 45))),
                        oneFifthRankAndNoGain
                )
        );
    }

    @DisplayName("각 로또 번호에 따라 올바른 등수가 계산된다.")
    @ParameterizedTest(name = "{index} => lottos={0}, expectedRank={1}")
    @MethodSource("lottoAndExpectedRankProvider")
    void computeTotalResultTest(List<Lotto> lottos, Map<LottoRank, Integer> expectedRanks) {
        TotalResult totalResult = resultComputer.computeTotalResult(lottos);

        assertEquals(expectedRanks, totalResult.getRanks());
    }


    @Test
    void computeTotalResult() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        TotalResult totalResult = resultComputer.computeTotalResult(lottos);
        assertEquals(totalResult.getRanks(), Map.of(LottoRank.FIRST, 1));
    }
}