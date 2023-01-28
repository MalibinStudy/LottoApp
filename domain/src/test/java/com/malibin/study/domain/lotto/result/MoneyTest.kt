package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class MoneyTest {

    @Test
    fun `amount에 음수가 들어오면 예외를 던진다`() {

        //when
        val actualAmount = -1L

        // given
        val actualException = runCatching { Money(actualAmount).amount }.exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : $actualAmount")
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