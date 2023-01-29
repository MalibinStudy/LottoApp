package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class WinningTicketTest {
    @Test
    fun `당첨 번호 안에는 보너스 번호가 존재할 수 없다`() {
        // given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(6)

        // when
        val actualException = runCatching { WinningTicket(winningNumbers, bonusNumber) }
            .exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("보너스 번호 (6)는 당첨번호(1, 2, 3, 4, 5, 6)와 중복될 수 없습니다.")
            }
        )
    }
}