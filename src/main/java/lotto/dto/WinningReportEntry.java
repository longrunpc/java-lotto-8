package lotto.dto;

public record WinningReportEntry(
    int matchCount,
    boolean hasBonus,
    int prizeMoney,
    int count
) {}
