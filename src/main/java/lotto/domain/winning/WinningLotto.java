package lotto.domain.winning;

import java.util.List;

import lotto.domain.lotto.Lotto;

public class WinningLotto {
    private final Lotto winningLotto;
    private final int bonusNumber;

    private WinningLotto(Lotto winningLotto, int bonusNumber) {
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto create(List<Integer> winningNumbers, int bonusNumber) {
        return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
    }

    public Rank countMatch(Lotto lotto) {
        int matchCount = (int) lotto.getNumbers()
                .stream()
                .filter(winningLotto::contains)
                .count();

        return Rank.of(matchCount, isBonusMatch(lotto));
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }
}
