package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

internal class MoneyTest {
    @Test
    fun `Money객체에는 음수가 들어갈 수 없다`() {
        // given
        val negativeValue = -1000L

        // when
        val exception = runCatching { Money(negativeValue) }

        // then
        assertThat(exception.isFailure).isTrue()
    }

    @Test
    fun `Money객체는 0원을 허용한다`() {
        // given
        val zeroValue = 0
        // when
        val money = Money(0)
        // then
        assertThat(money.amount).isEqualTo(0)
    }

    @Test
    fun `Money객체끼리 합이 가능하다 `() {
        // given
        val money1 = Money(1000)
        val money2 = Money(2000)
        // when
        val actualSum = money1 + money2
        // then
        assertThat(actualSum).isEqualTo(Money(3000))
    }

    @Test
    fun `Money객체는 뺄셈이 가능하다`() {
        // given
        val money1 = Money(1000)
        val money2 = Money(2000)
        // when
        val actualDifference = money2 - money1
        // then
        assertThat(actualDifference).isEqualTo(Money(1000))
    }

    @Test
    fun `Money객체는 작은 수에서 큰 수를 뺄 수 없다`() {
        // given
        val money1 = Money(1000)
        val money2 = Money(2000)
        // when
        val exception = runCatching { money1 - money2 }.getOrNull()
        // then
        assertThat(exception).isNull()
    }

    @Test
    fun `Money객체의 amount가 같으면 Money객체는 같다`() {
        // given
        val money1 = Money(1000)
        val money2 = Money(1000)
        // when
        val isEqual = money1 == money2
        // then
        assertThat(isEqual).isTrue()
    }
}
