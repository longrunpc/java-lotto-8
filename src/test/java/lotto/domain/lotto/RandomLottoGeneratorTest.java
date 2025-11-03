package lotto.domain.lotto;

import lotto.common.constant.LottoConstant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLottoGeneratorTest {

    @Nested
    @DisplayName("싱글톤 동작")
    class SingletonBehavior {
        @Test
        @DisplayName("getInstance는 동일한 인스턴스를 반환한다")
        void getInstance_returns_same_instance() {
            LottoGenerator first = RandomLottoGenerator.getInstance();
            LottoGenerator second = RandomLottoGenerator.getInstance();

            assertThat(first).isSameAs(second);
        }
    }

    @Nested
    @DisplayName("로또 생성")
    class Generate {
        @Test
        @DisplayName("generate는 유효한 6개의 서로 다른 번호를 생성한다")
        void generate_creates_valid_unique_six_numbers() {
            // when
            Lotto lotto = RandomLottoGenerator.getInstance().generate();

            // then
            assertThat(lotto).isNotNull();
            List<Integer> numbers = lotto.getNumbers();

            assertValid(numbers);
        }

        @Test
        @DisplayName("연속 두 번 생성 시 결과는 로또 규칙을 모두 만족한다")
        void generate_twice_results_are_valid() {
            LottoGenerator generator = RandomLottoGenerator.getInstance();
            Lotto first = generator.generate();
            Lotto second = generator.generate();

            assertValid(first.getNumbers());
            assertValid(second.getNumbers());
        }

        private void assertValid(List<Integer> numbers) {
            assertThat(numbers).hasSize(LottoConstant.LOTTO_SIZE);
            assertThat(numbers)
                    .allMatch(n -> n >= LottoConstant.LOTTO_START_NUMBER && n <= LottoConstant.LOTTO_END_NUMBER);
            assertThat(numbers.stream().collect(Collectors.toSet())).hasSize(numbers.size());
        }
    }
}
