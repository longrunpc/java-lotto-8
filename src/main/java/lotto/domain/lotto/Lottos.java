package lotto.domain.lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.dto.PurchasedLotto;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos generate(int lottoCount, LottoGenerator lottoGenerator) {
        List<Lotto> lottos = IntStream.range(0, lottoCount)
            .mapToObj(i -> lottoGenerator.generate())
            .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    public List<PurchasedLotto> toPurchasedLottos() {
        return lottos.stream()
            .map(Lotto::toPurchasedLotto)
            .toList();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
