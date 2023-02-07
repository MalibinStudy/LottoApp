package com.malibin.study.domain.lotto.result

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `양수만 받는지 테스트`() {
        // when
        val exception = kotlin.runCatching { Money(-1) }.exceptionOrNull()

        // given
        assertThat(exception).isInstanceOf(Exception::class.java)
        assertThat(exception).hasMessageThat().contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : -1")
    }

    @Test
    fun `plus 함수가 제대로 작동하는지 테스트`() {
        // given
        val money1 = Money(1)
        val money2 = Money(2)
        val money3 = Money(3)

        // when
        val resultMoney = money1 + money2

        // then
        assertThat(resultMoney.amount).isEqualTo(money3.amount)
    }

    @Test
    fun `minus 함수가 제대로 작동하는지 테스트`() {
        // given
        val money1 = Money(1)
        val money2 = Money(2)
        val money3 = Money(3)

        // when
        val resultMoney = money3 - money2

        // then
        assertThat(resultMoney.amount).isEqualTo(money1.amount)
    }
}
