package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PrizeTest {
    @CsvSource(value = ["6,false,First", "5,true,Second", "5,false,Third", "4,false,Fourth", "3,false,Fifth", "2,false,Lose"])
    @ParameterizedTest
    fun `당첨된 수의 개수와 보너스 숫자에 따른 올바른 등수를 매기는 지를 체크한다`(match: Int, has: Boolean, prize: Prize) {
        //when
        val actualPrize = Prize.find(match, has)
        //then
        assertThat(actualPrize).isEqualTo(prize)
    }
}