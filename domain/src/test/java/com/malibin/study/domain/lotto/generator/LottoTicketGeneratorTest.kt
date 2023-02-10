package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Test

internal class LottoTicketGeneratorTest {

    @Test
    fun `수동으로 로또 티켓을 만들 수 있다`() {
        // given
        val lottoTicketGenerator = LottoTicketGenerator()
        val nums = setOf(1, 2, 3, 4, 4, 5, 6)

        // when
        val actualLottoTicket = lottoTicketGenerator.createManualTicket(nums)
        // then
        assertThat(actualLottoTicket).isEqualTo(LottoTicket(1, 2, 3, 4, 5, 6))
    }
}
