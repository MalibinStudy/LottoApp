package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PrizeTest {

    @ParameterizedTest
    @CsvSource(
        value = [
            "0,true, Lose", "0,false,Lose",
            "1,true,Lose", "1,false,Lose",
            "2,true,Lose", "2,false,Lose",
            "3,true,Fifth", "3,false,Fifth",
            "4,true,Fourth", "4,false,Fourth",
            "5,true,Second", "5,false,Third",
            "6,true,First", "6,false,First",
            "7,true,Lose", "7,false,Lose",
            "-1,true,Lose", "-1,false,Lose"
        ],
        delimiter = ','
    )
    fun `로또 번호와 맞는 숫자 개수와 보너스 숫자의 여부에 따라 find()함수를 통해 Prize를 얻을 수 있다`(
        matchedNumberCount: Int,
        hasBonusNumber: Boolean,
        prize: Prize
    ) {
        // when
        val actualPrize = Prize.find(matchedNumberCount, hasBonusNumber)
        // then
        assertThat(actualPrize).isEqualTo(prize)
    }
}
