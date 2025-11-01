package lotto.domain;

/**
 * 로또 한 장의 결과를 담는 클래스 <br/>
 * 당첨되지 않은 경우 필드 lottoRank는 null 값을 가진다.
 */
public class LottoResult {
    private LottoRank lottoRank;

    public LottoResult(LottoRank lottoRank) {
        this.lottoRank = lottoRank;
    }

    public LottoRank getLottoRank() {
        return lottoRank;
    }
}
