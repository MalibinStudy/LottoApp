package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PrizeTest {

    @CsvSource(value = ["6,false,2000000000", "5,true,30000000"])
    @ParameterizedTest
    fun `당첨 결과값을 잘 반환할 수 있다`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }

    @CsvSource(value = ["2,false,0", "1,true,0"])
    @ParameterizedTest
    fun `미당첨 결과값을 잘 반환할 수 있다`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }

    /** 이 역시도 구현되지 않은 기능이므로 테스트에서 제외하는게 좋을까요? */
    @CsvSource(value = ["0,true,0"])
    @ParameterizedTest
    fun `당첨 갯수가 0일때 보너스번호가 true로 됐을 경우 작동하면 안 되는데?`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }

}