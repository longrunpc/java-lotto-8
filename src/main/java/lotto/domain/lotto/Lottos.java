package lotto.domain.lotto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
}
