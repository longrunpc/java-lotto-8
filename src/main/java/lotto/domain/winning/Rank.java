package lotto.domain.winning;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    MISS(0, false, 0);

    private static final int BONUS_CONDITION_MATCH_COUNT = 5;

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;

    Rank(int matchCount, boolean bonusMatch, int prize) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (isBonusCondition(matchCount)) {
            return findRankWithBonusCondition(bonusMatch);
        }

        return findRankByMatchCount(matchCount);
    }

    private static boolean isBonusCondition(int matchCount) {
        return matchCount == BONUS_CONDITION_MATCH_COUNT;
    }

    private static Rank findRankWithBonusCondition(boolean bonusMatched) {
        return bonusMatched ? SECOND : THIRD;
    }

    private static Rank findRankByMatchCount(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }

    public int getPrize() {
        return prize;
    }
}
