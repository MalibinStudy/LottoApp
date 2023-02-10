package com.malibin.study.domain.lotto.generator

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
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

    @Test
    fun `직접 로또 넘버를 입력하여 자동 로또 티켓을 만드는 방식을 바꿀 수 있다`() {
        // given
        val newGeneratingLottoNumbersStrategy = GeneratingLottoNumbersStrategy {
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
            )
        }
        val lottoTicketGenerator = LottoTicketGenerator(newGeneratingLottoNumbersStrategy)
        // when
        val actualAutoLottoTicket = lottoTicketGenerator.createAutoTicket()
        // then
        assertThat(actualAutoLottoTicket).isEqualTo(LottoTicket(1, 2, 3, 4, 5, 6))
    }
}
