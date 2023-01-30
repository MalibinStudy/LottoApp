package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ValueSource(ints = [1, 5, 3, 35, 0])
    @ParameterizedTest
    fun `Money의 값이 정상일 때 잘 동작한다`(input: Int) {
        val actualMoney = Money(input.toLong())
        assertThat(actualMoney.amount).isEqualTo(input)
    }


    @Test
    fun `Money의 값이 음수일 때 잘 에러를 반환한다`() {
        val input = -20
        val exception = kotlin.runCatching { Money(input.toLong()) }.exceptionOrNull()
        assertThat(exception).hasMessageThat().isEqualTo("돈의 액수는 음수가 될 수 없습니다. 입력 값 : $input")
    }

    @CsvSource(value = ["0,0,0", "1,5,6", "2,7,9", "19, 9, 28", "20, 1, 20"])
    @ParameterizedTest
    fun `PLUS 함수가 덧셈 연산을 올바르게 수행한다`(input1 : Long, input2: Long, result : Long) {
        val actualResult = Money(input1) + Money(input2)
        assertThat(actualResult).isEqualTo(Money(amount = result))
    }

    @CsvSource(value = ["0,0,0", "7,2,5", "19, 9, 10", "19, 8, 12"])
    @ParameterizedTest
    fun `MINUS 함수가 뺄셈 연산을 올바르게 수행한다`(input1 : Long, input2: Long, result : Long) {
        val actualResult = Money(input1) - Money(input2)
        assertThat(actualResult).isEqualTo(Money(amount = result))
    }


}