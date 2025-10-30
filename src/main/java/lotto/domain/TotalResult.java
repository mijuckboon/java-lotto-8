package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalResult {
    private static final int PERCENT_MULTIPLIER = 100;
    private static final int ROUND_DECIMAL = 2;
            
    List<LottoResult> lottoResults;

    public TotalResult(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    public Map<LottoRank, Integer> getRanks() {
        Map<LottoRank, Integer> rankCount = new HashMap<LottoRank, Integer>();
        for (LottoResult lottoResult : lottoResults) {
//            if (lottoResult == null) {
//                continue;
//            }
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

    public double getRoundedRateOfReturn(long payment) {
        return round(getRateOfReturn(payment), ROUND_DECIMAL);
    }

    private double getRateOfReturn(long payment) {
        return (double) getTotalGain() / payment * PERCENT_MULTIPLIER;
    }
    
    private double round(double value, int places) {
        double exponent = Math.pow(10, places);
        return Math.round(value * exponent) / exponent;
    }
}
