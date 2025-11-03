package lotto.domain.lotto;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(0, 45, 6);
        return new Lotto(numbers);
    }
}
