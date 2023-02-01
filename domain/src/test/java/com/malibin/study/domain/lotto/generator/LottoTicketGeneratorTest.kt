package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LottoTicketGeneratorTest {

    @Test
    fun `자동 티켓 생성 시 6개의 랜덤 번호가 오름차순으로 생성된다`() {
        // given
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

        val expectedLottoTicket = LottoTicketGenerator(
            generatingLottoNumbersStrategy
        ).createAutoTicket()

        // when
        val actualLottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)

        // then
        Truth.assertThat(actualLottoTicket).isEqualTo(expectedLottoTicket)
    }

    @Test

}