package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

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

    @MethodSource("providesTicketsAndResult")
    @ParameterizedTest
    fun `로또 티켓의 당첨 결과를 알 수 있다`(lottoTicket: LottoTicket, expectedPrize: Prize) {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(10),
        )
        // when
        val actualPrize = winningTicket.compareWith(lottoTicket)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    companion object {
        @JvmStatic
        private fun providesTicketsAndResult() = arrayOf(
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), Prize.First),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 10), Prize.Second),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 15), Prize.Third),
            Arguments.of(LottoTicket(1, 2, 3, 4, 15, 20), Prize.Fourth),
            Arguments.of(LottoTicket(1, 2, 3, 15, 20, 25), Prize.Fifth),
            Arguments.of(LottoTicket(1, 2, 15, 20, 25, 30), Prize.Lose),
            Arguments.of(LottoTicket(1, 15, 20, 25, 30, 35), Prize.Lose),
            Arguments.of(LottoTicket(15, 20, 25, 30, 35, 40), Prize.Lose),
        )
    }


}