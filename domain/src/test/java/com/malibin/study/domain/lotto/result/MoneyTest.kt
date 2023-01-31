package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MoneyTest {
    @Test
    fun `amount에 음수가 들어오면 안된다`() {
        //given
        val amount = -1L
        //when
        val actualException = runCatching { Money(amount) }.exceptionOrNull()
        //then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : $amount")
            }
        )
    }

    @Test
    fun `plus 연산이 올바르게 동작해야 한다`() {
        //when
        val actualPlus = Money(3L) + Money(2L)
        //then
        assertThat(actualPlus).isEqualTo(Money(5L))
    }

    @Test
    fun `minus 연산이 올바르게 동작해야 한다`() {
        //when
        val actualMinus = Money(3L) - Money(2L)
        //then
        assertThat(actualMinus).isEqualTo(Money(1L))
    }
}