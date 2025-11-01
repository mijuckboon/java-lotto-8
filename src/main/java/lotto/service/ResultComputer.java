package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.TotalResult;

/**
 * 로또 결과 계산을 담당하는 메서드
 */
public class ResultComputer {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public ResultComputer(Lotto winningLotto, BonusNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public TotalResult computeTotalResult(List<Lotto> lottos) {
        List<LottoResult> results = new ArrayList<LottoResult>();
        for (Lotto lotto : lottos) {
            results.add(computeOneLottoResult(lotto));
        }
        return new TotalResult(results);
    }

    private LottoResult computeOneLottoResult(Lotto lotto) {
        List<Integer> selectedNumbers = lotto.getNumbers();
        List<Integer> correctNumbers = filterCorrectNumbers(selectedNumbers);
        int matchCount = correctNumbers.size();
        int matchBonusCount = computeMatchBonusCount(selectedNumbers);
        return constructLottoResult(matchCount, matchBonusCount);
    }

    private int computeMatchBonusCount(List<Integer> selectedNumbers) {
        return computeMatchBonusCount(selectedNumbers, bonusNumber.getNumber());
    }

    private int computeMatchBonusCount(List<Integer> selectedNumbers, int bonusNumberValue) {
        return selectedNumbers.stream()
                .filter(number -> number == bonusNumberValue)
                .toList()
                .size();
    }

    private LottoResult constructLottoResult(int targetMatchCount, int targetMatchBonusCount) {
        for (LottoRank rank : LottoRank.values()) {
            if (isMatched(rank, targetMatchCount, targetMatchBonusCount)) {
                return new LottoResult(rank);
            }
        }
        return new LottoResult(null);
    }

    private boolean isMatched(LottoRank rank, int targetMatchCount, int targetMatchBonusCount) {
        int matchCount = rank.getMatchCount();
        int matchBonusCount = rank.getMatchBonusCount();
        return matchCount == targetMatchCount && matchBonusCount == targetMatchBonusCount;
    }

    private List<Integer> filterCorrectNumbers(List<Integer> selectedNumbers) {
        return selectedNumbers.stream().filter(
                number -> winningLotto.getNumbers().contains(number)
        ).toList();
    }
}
