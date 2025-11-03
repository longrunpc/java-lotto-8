package lotto.domain.winning;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.dto.WinningReport;
import lotto.dto.WinningReportEntry;

public class WinningResult {
    
    private final Map<Rank, Integer> rankCounts;

    private WinningResult(Map<Rank, Integer> rankCounts) {
        this.rankCounts = rankCounts;
    }

    public static WinningResult from(Lottos lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

        List<Lotto> lottoList = lottos.getLottos();
        for (Lotto lotto : lottoList) {
            Rank rank = winningLotto.countMatch(lotto);
            counts.merge(rank, 1, Integer::sum);
        }

        return new WinningResult(counts);
    }

    public BigDecimal calculateTotalPrize() {
        return rankCounts.entrySet().stream()
            .map(entry -> {
                BigDecimal prize = BigDecimal.valueOf(entry.getKey().getPrize());
                BigDecimal count = BigDecimal.valueOf(entry.getValue());
                return prize.multiply(count);
            })
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public WinningReport toReport() {
        List<WinningReportEntry> entries = Arrays.stream(Rank.values())
            .filter(rank -> rank != Rank.MISS)
            .sorted(Comparator
                .comparingInt(Rank::getMatchCount)
                .thenComparing(Rank::hasBonus)
            )
            .map(rank -> {
                int count = rankCounts.getOrDefault(rank, 0);
                return new WinningReportEntry(
                    rank.getMatchCount(),
                    rank.hasBonus(),
                    rank.getPrize(),
                    count
                );
            })
            .toList();
    
        return new WinningReport(entries);
    }
}