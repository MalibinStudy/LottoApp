package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PrizeTest {

    @CsvSource(value = ["6,false, First",
                        "5,true,Second",
                        "5,false,Third",
                        "4,true,Fourth",
                        "3,false,Fifth",
                        "2,false,Lose",
                        "1,false,Lose",
                        "0,false,Lose"])
    @ParameterizedTest
    fun `로또의 결과값을 잘 반환할 수 있다`(count: Int, has: Boolean, prize: Prize) {
        val assertValue = Prize.find(count, has).name
        assertThat(assertValue).isEqualTo(prize.name)
    }

    @CsvSource(value = ["6,true,6,false",
                        "4,true,4,false",
                        "3,true,3,false",
                        "2,true,2,false",
                        "1,true,1,false",
                        "0,true,0,false"])
    @ParameterizedTest
    fun `보너스 번호가 상관 없는 경우를 잘 판단할 수 있다`(count: Int, has: Boolean, count2 : Int, has2 : Boolean) {
        val bonusValue = Prize.find(count, has).name
        val nonBonusValue = Prize.find(count2, has2).name
        assertThat(bonusValue).isEqualTo(nonBonusValue)
    }

}