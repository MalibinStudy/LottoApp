package com.malibin.study.lotto.cal

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.cal.AverageCalculator

import org.junit.jupiter.api.Test

class AverageCalculatorTest {
    @Test
    fun `평균 구하기`() {
        val integers = listOf<Int>(1,2,3,4,5,6)

        val averageCalculator = AverageCalculator()

        val actualAverage = averageCalculator.calculate(integers)

        assertThat(actualAverage).isEqualTo(3)
    }
}