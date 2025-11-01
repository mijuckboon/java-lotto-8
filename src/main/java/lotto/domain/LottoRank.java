package lotto.domain;

/**
 * 로또 등수 enum 클래스
 */
public enum LottoRank {
    FIRST(1,2_000_000_000, 6, 0),
    SECOND(2, 30_000_000, 5, 1),
    THIRD(3, 1_500_000, 5, 0),
    FOURTH(4, 50_000, 4, 0),
    FIFTH(5, 5_000, 3, 0)
    ;

    private final int rank;
    private final long prize;
    private final int matchCount;
    private final int matchBonusCount;

    LottoRank(int rank, long prize, int matchCount, int matchBonusCount) {
        this.rank = rank;
        this.prize = prize;
        this.matchCount = matchCount;
        this.matchBonusCount = matchBonusCount;
    }

    public int getRank() {
        return rank;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getMatchBonusCount() {
        return matchBonusCount;
    }
}
