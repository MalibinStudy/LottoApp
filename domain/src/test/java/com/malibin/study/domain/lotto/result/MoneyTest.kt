package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException
import java.util.IllegalFormatException

class MoneyTest {

    @ValueSource(ints = [1, 5, 3, 35, 0])
    @ParameterizedTest
    fun `Money의_값이_정상일_때_잘_동작하는지?`(input: Int) {
        val actualMoney = Money(input.toLong())
        assertThat(actualMoney.amount).isAtLeast(0)
    }

    @ValueSource(ints = [-1, -5, -3, -35, -10])
    @ParameterizedTest
    fun `Money의_값이_음수일_때_잘_동작하는지?`(input: Int) {
        val exception = kotlin.runCatching { Money(input.toLong()) }.exceptionOrNull()
        assertThat(exception).hasMessageThat().contains(input.toString())
    }

    /** 왜 Null은 Exception으로 걸러낼 수 없을까?
     * 별개의 try catch 처리를 해 주어야 하나? */
    @NullSource
    @ParameterizedTest
    fun `Money의_값이_Null일_때_잘_동작하는지?`(input: Int) {
        val exception = kotlin.runCatching { Money(input.toLong()) }.exceptionOrNull()
        assertThat(exception).isInstanceOf(Exception::class.java)

    }

    @ValueSource(strings = ["가", "나", "다"])
    @ParameterizedTest
    fun `Money의_값이_문자열일_때_잘_걸러지는지?`(input: String) {
        val exception = kotlin.runCatching { Money(input.toLong()) }.exceptionOrNull()
        assertThat(exception).isInstanceOf(NumberFormatException::class.java)
    }

    @CsvSource(value = ["0,0,0", "1,5,6", "2,7,9", "19, 9, 28", "20, 1, 20"])
    @ParameterizedTest
    fun `PLUS_함수가_잘_동작하는지_?`(input1 : Long, input2: Long, result : Long) {
        val actualResult = Money(input1) + Money(input2)
        assertThat(actualResult).isEqualTo(Money(amount = result))
    }

    @CsvSource(value = ["0,0,0", "7,2,5", "19, 9, 10", "19, 8, 12"])
    @ParameterizedTest
    fun `MINUS_함수가_잘_동작하는지_?`(input1 : Long, input2: Long, result : Long) {
        val actualResult = Money(input1) - Money(input2)
        assertThat(actualResult).isEqualTo(Money(amount = result))
    }


}