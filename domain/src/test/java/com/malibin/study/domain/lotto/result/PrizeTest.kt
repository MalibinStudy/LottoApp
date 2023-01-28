package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PrizeTest {

    @CsvSource(value = ["6,false,2000000000", "5,true,30000000", "5,false,1500000"])
    @ParameterizedTest
    fun `당첨_됐을_경우_작동_테스트`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }

    @CsvSource(value = ["2,false,0", "0,false,0", "1,true,0"])
    @ParameterizedTest
    fun `미당첨_됐을_경우_작동_테스트`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }

    /** ?? */
    @CsvSource(value = ["0,true,0"])
    @ParameterizedTest
    fun `당첨_갯수가_0일때_보너스번호가_true로_됐을_경우_작동_테스트`(count : Int, has : Boolean, result : Long) {
        val assertValue = Prize.find(count, has).amount.amount
        assertThat(assertValue).isEqualTo(result)
    }
}