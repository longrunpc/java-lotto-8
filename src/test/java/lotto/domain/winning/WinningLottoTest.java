package lotto.domain.winning;

import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Nested
    @DisplayName("WinningLotto 생성")
    class CreateWinningLotto {
        @DisplayName("정상 입력이면 생성된다")
        @Test
        void should_create_when_valid_inputs() {
            // Given
            List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
            int bonusNumber = 7;

            // When
            WinningLotto winningLotto = WinningLotto.create(winningNumbers, bonusNumber);

            // Then
            assertThat(winningLotto).isNotNull();
        }

        @DisplayName("당첨 번호 개수가 6이 아니면 예외")
        @Test
        void should_throw_when_invalid_winning_numbers_size() {
            // Given
            List<Integer> invalidWinningNumbers = List.of(1, 2, 3, 4, 5);

            // When & Then
            assertThatThrownBy(() -> WinningLotto.create(invalidWinningNumbers, 7))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("보너스 번호가 범위를 벗어나면 예외")
        @Test
        void should_throw_when_bonus_out_of_range() {
            // Given
            List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);

            // When & Then
            assertThatThrownBy(() -> WinningLotto.create(winningNumbers, 0))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> WinningLotto.create(winningNumbers, 46))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("매치 계산")
    class CountMatchBehavior {
        @DisplayName("6개 일치하면 1등")
        @Test
        void should_return_first_when_six_matches() {
            // Given
            WinningLotto winning = WinningLotto.create(List.of(1, 2, 3, 4, 5, 6), 7);
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // When
            Rank rank = winning.countMatch(lotto);

            // Then
            assertThat(rank).isEqualTo(Rank.FIRST);
        }

        @DisplayName("5개 + 보너스 일치하면 2등")
        @Test
        void should_return_second_when_five_matches_and_bonus() {
            // Given
            WinningLotto winning = WinningLotto.create(List.of(1, 2, 3, 4, 5, 6), 7);
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

            // When
            Rank rank = winning.countMatch(lotto);

            // Then
            assertThat(rank).isEqualTo(Rank.SECOND);
        }

        @DisplayName("5개 일치하지만 보너스 불일치면 3등")
        @Test
        void should_return_third_when_five_matches_without_bonus() {
            // Given
            WinningLotto winning = WinningLotto.create(List.of(1, 2, 3, 4, 5, 6), 7);
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

            // When
            Rank rank = winning.countMatch(lotto);

            // Then
            assertThat(rank).isEqualTo(Rank.THIRD);
        }
    }

    @Nested
    @DisplayName("보너스 매치")
    class BonusMatchBehavior {
        @DisplayName("보너스 번호가 포함되면 true")
        @Test
        void should_return_true_when_bonus_in_lotto() {
            // Given
            WinningLotto winning = WinningLotto.create(List.of(10, 11, 12, 13, 14, 15), 20);
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 20));

            // When
            boolean result = winning.isBonusMatch(lotto);

            // Then
            assertThat(result).isTrue();
        }

        @DisplayName("보너스 번호가 포함되지 않으면 false")
        @Test
        void should_return_false_when_bonus_not_in_lotto() {
            // Given
            WinningLotto winning = WinningLotto.create(List.of(10, 11, 12, 13, 14, 15), 20);
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            // When
            boolean result = winning.isBonusMatch(lotto);

            // Then
            assertThat(result).isFalse();
        }
    }
}


