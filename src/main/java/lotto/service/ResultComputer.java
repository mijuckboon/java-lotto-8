package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.TotalResult;

public class ResultComputer {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public ResultComputer(Lotto winningLotto, BonusNumber bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public TotalResult computeResult(List<Lotto> lottos) {
        List<LottoResult> results = new ArrayList<LottoResult>();
        for (Lotto lotto : lottos) {
            results.add(computeResult(lotto));
        }
        return new TotalResult(results);
    }

    private LottoResult computeResult(Lotto lotto) {
        List<Integer> selectedNumbers = lotto.getNumbers();
        List<Integer> correctNumbers = filterCorrectNumbers(selectedNumbers);
        int matchCount = correctNumbers.size();
        int matchBonusCount = selectedNumbers.stream().filter(number -> number == bonusNumber.getNumber()).toList()
                .size();

        for (LottoRank rank : LottoRank.values()) {
            if (rank.getMatchCount() == matchCount && rank.getMatchBonusCount() == matchBonusCount) {
                return new LottoResult(rank);
            }
        }
        return new LottoResult(null);
    }

    private List<Integer> filterCorrectNumbers(List<Integer> selectedNumbers) {
        return selectedNumbers.stream().filter(
                number -> winningLotto.getNumbers().contains(number)
        ).toList();
    }
}
