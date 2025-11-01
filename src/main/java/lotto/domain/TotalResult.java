package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 발행한 모든 로또의 결과 객체
 */
public class TotalResult {
    private static final int PERCENT_MULTIPLIER = 100;

    List<LottoResult> lottoResults;

    public TotalResult(List<LottoResult> lottoResults) {
        this.lottoResults = lottoResults;
    }

    /**
     * 모든 로또 결과를 계산하여 반환하는 메서드
     * @return 로또 결과 map (key: 등수, value: 횟수)
     */
    public Map<LottoRank, Integer> getRanks() {
        Map<LottoRank, Integer> rankCount = new HashMap<LottoRank, Integer>();
        for (LottoResult lottoResult : lottoResults) {
            LottoRank lottoRank = lottoResult.getLottoRank();
            rankCount.put(lottoRank, rankCount.getOrDefault(lottoResult.getLottoRank(), 0) + 1);
        }
        return rankCount;
    }

    /**
     * 최종 상금을 반환하는 메서드
     * @return 상금 총합
     */
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

    /**
     * 수익률(상금 총합 / 총 구입 금액)의 백분율 값을 반환하는 메서드
     * @param payment 구입 금액 객체
     * @return 수익률 (단위: %)
     */
    public double getRateOfReturn(Payment payment) {
        return (double) getTotalGain() / payment.getAmount() * PERCENT_MULTIPLIER;
    }
}
