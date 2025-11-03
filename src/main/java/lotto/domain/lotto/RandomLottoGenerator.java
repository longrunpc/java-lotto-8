package lotto.domain.lotto;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.common.constant.LottoConstant;
import lotto.util.validator.LottoValidator;

public class RandomLottoGenerator implements LottoGenerator {

    private RandomLottoGenerator() {}
    
    private static class Holder {
        private static final LottoGenerator INSTANCE = new RandomLottoGenerator();
    }

    public static LottoGenerator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Lotto generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(LottoConstant.LOTTO_START_NUMBER, LottoConstant.LOTTO_END_NUMBER, LottoConstant.LOTTO_SIZE);
        LottoValidator.validateLottoNumbers(numbers);
        return new Lotto(numbers);
    }
}
