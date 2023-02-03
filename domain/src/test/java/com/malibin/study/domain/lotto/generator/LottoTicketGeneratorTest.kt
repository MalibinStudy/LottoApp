package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Test

internal class LottoTicketGeneratorTest {

    @Test
    fun `자동 티켓 생성 시 6개의 랜덤 번호가 생성된다`() {
        // given
        val expectedLottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)

        // when
        val generatingLottoNumbersStrategy = GeneratingLottoNumbersStrategy {
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(3),
                LottoNumber.of(2),
                LottoNumber.of(6),
                LottoNumber.of(5),
                LottoNumber.of(4)
            )
        }

        val actualLottoTicket = LottoTicketGenerator(
            generatingLottoNumbersStrategy
        ).createAutoTicket()


        // then
        assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket)
    }

    @Test
    fun `수동 티켓 생성 시 6개의 번호가 생성 됨을 확인할 수 있다`() {
        // given
        val expectedLottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)

        // when
        val actualLottoTicket = LottoTicketGenerator().createManualTicket(setOf(1, 2, 3, 4, 5, 6))

        // then
        assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket)
    }

}