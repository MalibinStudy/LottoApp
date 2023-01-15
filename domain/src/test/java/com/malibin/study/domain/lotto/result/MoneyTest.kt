package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MoneyTest {

    @Test
    fun `음수인 돈은 존재할 수 없다`() {
        // when
        val actualException = runCatching { Money(-1) }.exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : -1")
            }
        )
    }

    @Test
    fun `Money를 + 연산자로 더할 수 있다`() {
        // when
        val actualMoney = Money(1_000) + Money(2_000)

        // then
        assertThat(actualMoney).isEqualTo(Money(3_000))
    }

    @Test
    fun `Money를 - 연산자로 더할 수 있다`() {
        // when
        val actualMoney = Money(2_000) - Money(1_000)

        // then
        assertThat(actualMoney).isEqualTo(Money(1_000))
    }
}
