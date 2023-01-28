package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class MoneyTest {

    @ValueSource(longs = [0, 1, 1000, 10000])
    @ParameterizedTest
    fun `amount에 0이상의 값이 들어올 수 있다`(positiveAmount: Long) {
        // given
        val actualAmount = Money(positiveAmount).amount

        // then
        assertThat(actualAmount).isAtLeast(0)
    }

    @ValueSource(longs = [-10, -5, -1, -4])
    @ParameterizedTest
    fun `amount에 음수가 들어오면 예외를 던진다`(negativeAmount: Long) {
        // given
        val actualException = runCatching { Money(negativeAmount).amount }.exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : $negativeAmount")
            }
        )
    }

    @Test
    fun `Money를 연산자 '+'로 더할 수 있다`() {
        // when
        val actualMoney = Money(1_000) + Money(2_000)

        // then
        assertThat(actualMoney).isEqualTo(Money(3_000))
    }

    @Test
    fun `Money를 연산자 '-'로 뺄 수 있다`() {
        // when
        val actualMoney = Money(3_000) - Money(2_000)

        // then
        assertThat(actualMoney).isEqualTo(Money(1_000))
    }
}