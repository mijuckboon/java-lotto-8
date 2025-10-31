package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalResult {
    private static final int PERCENT_MULTIPLIER = 100;

    List<LottoResult> lottoResults;

    public TotalResult(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public Map<LottoRank, Integer> getRanks() {
        Map<LottoRank, Integer> rankCount = new HashMap<LottoRank, Integer>();
        for (LottoResult lottoResult : lottoResults) {
            LottoRank lottoRank = lottoResult.getLottoRank();
            rankCount.put(lottoRank, rankCount.getOrDefault(lottoResult.getLottoRank(), 0) + 1);
        }
        return rankCount;
    }

    public long getTotalGain() {
        long totalGain = 0;
        Map<LottoRank, Integer> rankCount = new HashMap<LottoRank, Integer>();
        for (LottoResult lottoResult : lottoResults) {
            LottoRank lottoRank = lottoResult.getLottoRank();
            rankCount.put(lottoRank, rankCount.getOrDefault(lottoRank, 0) + 1);
            if (lottoRank == null) {
                continue;
            }
            totalGain += lottoRank.getPrize();
        }
        return totalGain;
    }

    public double getRateOfReturn(Payment payment) {
        return (double) getTotalGain() / payment.getAmount() * PERCENT_MULTIPLIER;
    }
}
