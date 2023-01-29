package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PrizeTest {

    @CsvSource("6,false,First")
    @ParameterizedTest
    fun `6개의 번호가 일치할 경우 1등 당첨`(
        matchedNumberCount: Int,
        hasBonusNumber: Boolean,
        expectedPrize: Prize
    ) {
        // when
        val actualPrize = Prize.find(matchedNumberCount, hasBonusNumber)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    @Test
    fun `5개의 번호가 일치하고 보너스 번호가 존재할 경우 2등 당첨`() {

    }

    @Test
    fun `5개의 번호가 일치하고 보너스 번호가 없는 경우 3등 당첨`() {

    }

    @Test
    fun `4개의 번호가 일치할 경우 4등 당첨`() {

    }

    @Test
    fun `3개의 번호가 일치할 경우 5등 당첨`() {

    }

    @Test
    fun `2개이하의 번호가 일치할 경우 낙첨`() {

    }
}