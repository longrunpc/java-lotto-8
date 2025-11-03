package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @Nested
    @DisplayName("로또 생성")
    class CreateLotto {
        @Test
        void should_throw_exception_when_size_exceeds_six() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("정상적인 번호로 로또를 생성할 수 있다")
        @Test
        void should_create_lotto_with_valid_numbers() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            assertThat(lotto).isNotNull();
            assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
        @Test
        void should_throw_exception_when_numbers_have_duplicates() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("범위 검증")
    class ValidateRange {
        @DisplayName("범위를 벗어난 숫자가 있으면 예외가 발생한다")
        @ParameterizedTest
        @ValueSource(ints = {-1, 46})
        void should_throw_exception_when_number_out_of_range(int outOfRange) {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, outOfRange)))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("contains 동작")
    class ContainsBehavior {
        @DisplayName("contains는 포함 여부를 정확히 반환한다")
        @Test
        void should_return_correct_contains_result() {
            Lotto lotto = new Lotto(List.of(7, 14, 21, 28, 35, 42));
            assertThat(lotto.contains(21)).isTrue();
            assertThat(lotto.contains(1)).isFalse();
        }
    }
}
