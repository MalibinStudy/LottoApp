package com.malibin.study.domain.lotto.result

private typealias PrizeMatchStrategy = (matchedNumberCount: Int, hasBonusNumber: Boolean) -> Boolean

enum class Prize(val amount: Money, private val isMatch: PrizeMatchStrategy) {
    First(
        Money(2_000_000_000L),
        { matchedNumberCount, _ -> matchedNumberCount == 6 }
    ),
    Second(
        Money(30_000_000),
        { matchedNumberCount, hasBonusNumber -> hasBonusNumber && matchedNumberCount == 5 }
    ),
    Third(
        Money(1_500_000L),
        { matchedNumberCount, hasBonusNumber -> !hasBonusNumber && matchedNumberCount == 5 }
    ),
    Fourth(
        Money(50_000L),
        { matchedNumberCount, _ -> matchedNumberCount == 4 }
    ),
    Fifth(
        Money(5_000L),
        { matchedNumberCount, _ -> matchedNumberCount == 3 }
    ),
    Lose(
        Money(0L),
        { _, _ -> false }
    );

    companion object {
        @JvmStatic
        fun find(matchedNumberCount: Int, hasBonusNumber: Boolean): Prize {
            return values().find { it.isMatch(matchedNumberCount, hasBonusNumber) } ?: Lose
        }
    }
}
