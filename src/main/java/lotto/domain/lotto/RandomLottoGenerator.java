package lotto.domain.lotto;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.common.constant.LottoConstant;

public class RandomLottoGenerator implements LottoGenerator {
    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoConstant.LOTTO_START_NUMBER, LottoConstant.LOTTO_END_NUMBER, LottoConstant.LOTTO_SIZE);
        return new Lotto(numbers);
    }
}
