package lotto.domain.winning;

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

    public WinningReport toReport() {
        List<WinningReportEntry> entries = rankCounts.entrySet().stream()
            .filter(entry -> entry.getKey() != Rank.MISS)
            .map(entry -> {
                Rank rank = entry.getKey();
                int count = entry.getValue();
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
