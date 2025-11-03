package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Nested
    @DisplayName("Rank 생성")
    class RankOfBehavior {
        @DisplayName("보너스 조건(5개 일치)에서는 보너스 여부에 따라 등수가 달라진다")
        @ParameterizedTest
        @CsvSource({
                "5, true, SECOND",
                "5, false, THIRD"
        })
        void should_return_second_or_third_when_bonus_condition(int matchCount, boolean bonusMatch, Rank expected) {
            assertThat(Rank.of(matchCount, bonusMatch)).isEqualTo(expected);
        }

        @DisplayName("매치 수로 등수를 판별한다 (보너스 조건 제외)")
        @ParameterizedTest
        @CsvSource({
                "6, false, FIRST",
                "4, false, FOURTH",
                "3, false, FIFTH",
                "2, false, MISS",
                "1, false, MISS",
                "0, false, MISS"
        })
        void should_return_rank_by_match_count_when_not_bonus_condition(int matchCount, boolean bonusMatch, Rank expected) {
            assertThat(Rank.of(matchCount, bonusMatch)).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("당첨금 조회")
    class PrizeLookup {
        @DisplayName("각 등수의 당첨금이 일치한다.")
        @ParameterizedTest
        @CsvSource({
                "FIRST, 2000000000",
                "SECOND, 30000000",
                "THIRD, 1500000",
                "FOURTH, 50000",
                "FIFTH, 5000",
                "MISS, 0"
        })
        void should_return_correct_prize_for_each_rank(Rank rank, int expectedPrize) {
            assertThat(rank.getPrize()).isEqualTo(expectedPrize);
        }
    }
}
