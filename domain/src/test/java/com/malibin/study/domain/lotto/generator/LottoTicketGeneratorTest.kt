package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LottoTicketGeneratorTest {

    @Test
    fun `랜덤으로 만들어진 티켓이 직전 결과와 다른 지를 비교한다`() {
        //given
        var before = LottoTicket(1, 2, 3, 4, 5, 6)
        //when
        val actualLottoTicket = LottoTicketGenerator().createAutoTicket()
        //then
        assertAll(
            { assertThat(actualLottoTicket).isNotEqualTo(before) },
            { before = actualLottoTicket }
        )
    }

    @Test
    fun `수동으로 발급한 티켓과 직접 만든 티켓이 일치하는 지를 비교한다`() {
        //given
        val numbers = setOf(1, 2, 3, 4, 5, 6)
        val lottoTicket = LottoTicket(*(numbers.toIntArray()))
        //when
        val actualLottoTicket = LottoTicketGenerator().createManualTicket(numbers)
        //then
        assertThat(actualLottoTicket).isEqualTo(lottoTicket)
    }
}