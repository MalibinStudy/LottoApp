package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

internal class PrizeTest {

    @CsvSource(
        value = [
            "6,false,First",
            "5,true,Second",
            "5,false,Third",
            "4,false,Fourth",
            "3,false,Fifth",
            "2,false,Lose",
            "1,false,Lose",
            "0,false,Lose"
        ]
    )
    @ParameterizedTest
    fun `일치하는 번호의 개수와 보너스 번호의 존재 여부에 따라 당첨 결과를 알 수 있다`(
        matchedNumberCount: Int, hasBonusNumber: Boolean, expectedPrize: Prize
    ) {
        // when
        val actualPrize = Prize.find(matchedNumberCount, hasBonusNumber)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    /*@CsvSource(
        value = [
            "First,2_000_000_000",
            "Second,30_000_000",
            "Third,1_500_000",
            "Fourth,50_000",
            "Fifth,5_000",
            "Lose,0",
        ]
    )
    @ParameterizedTest
    fun `순위에 맞는 당첨 금액을 알 수 있다`(expectedPrize: Prize, expectedAmount: Long) {
        // when
        val actualPrize = expectedPrize.amount.amount

        // then
        assertThat(actualPrize).isEqualTo(expectedAmount)
    }*/
}