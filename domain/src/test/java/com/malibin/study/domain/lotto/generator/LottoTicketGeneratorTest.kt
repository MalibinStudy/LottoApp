package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Test

class LottoTicketGeneratorTest {

    @Test
    fun `번호를 직접 입력해 수동 티켓을 출력할 수 있다`() {
        // given
        val lottoTicketGenerator = LottoTicketGenerator()
        val manualNumbers = setOf(1, 2, 3, 4, 5, 6)
        val expectedLottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)

        // when
        val actualLottoTicket = lottoTicketGenerator.createManualTicket(manualNumbers)

        // then
        assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket)
    }

    @Test
    fun `자동 티켓을 출력할 수 있다`() {
        // given
        val lottoTicketGenerator = LottoTicketGenerator {
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
            )
        }
        val expectedLottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)

        // when
        val actualLottoTicket = lottoTicketGenerator.createAutoTicket()

        // then
        assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket)
    }
}
