package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

internal class MoneyTest {
    @Test
    fun `돈의 액수는 음수가 될 수 없다`() {
        // given
        val money = Money(-1)

        // when
        val exception = kotlin.runCatching {
            money
        }.exceptionOrNull()

        // then
        Truth.assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `돈을 더할 수 있다`() {
        // given
        val money1 = Money(1000)
        val money2 = Money(2000)
        val plusResult = money1 + money2

        // when
        val actualPlusResult = money1.plus(money2)

        // then
        Truth.assertThat(actualPlusResult).isEqualTo(plusResult)
    }
}
