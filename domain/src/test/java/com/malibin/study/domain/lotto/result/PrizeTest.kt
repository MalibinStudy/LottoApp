package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PrizeTest {
    @CsvSource(
        "6,false,First",
        "5,true,Second",
        "5,false,Third",
        "4,false,Fourth",
        "3,false,Fifth",
        "2,false,Lose",
        "1,false,Lose",
        "0,false,Lose",
    )
    @ParameterizedTest
    fun `맞은 로또 번호 개수와 맞은 보너스 숫자 여부에 따른 결과를 찾을 수 있다`(
        matchedNumberCount: Int,
        isBonusNumberMatched: Boolean,
        expectedPrize: Prize
    ) {
        // when
        val actualPrize = Prize.find(matchedNumberCount, isBonusNumberMatched)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }
}
