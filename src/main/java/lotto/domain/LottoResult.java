package lotto.domain;

public class LottoResult {
    private LottoRank lottoRank;

    public LottoResult(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
    }

    public LottoRank getLottoRank() {
        return lottoRank;
    }
}
