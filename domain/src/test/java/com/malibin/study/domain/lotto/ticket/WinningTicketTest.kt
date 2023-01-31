package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class WinningTicketTest {
    @Test
    fun `보너스 번호가 당첨번호와 중복되면 예외를 발생시킨다`() {
        //given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(6)
        //when
        val actualException =
            runCatching { WinningTicket(winningNumbers, bonusNumber) }.exceptionOrNull()
        //then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException()::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .isEqualTo("보너스 번호 ($bonusNumber)는 당첨번호($winningNumbers)와 중복될 수 없습니다.")
            }
        )
    }

    @MethodSource("provideLottoTickets")
    @ParameterizedTest
    fun `티켓과 당첨번호를 비교해 결과를 출력한다`(otherTicket: LottoTicket) {
        //given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(20)
        val winningTicket = WinningTicket(winningNumbers, bonusNumber)
        //when
        val actualPrize = winningTicket.compareWith(otherTicket)
        val actualMatch = winningNumbers.countMatchingNumbers(otherTicket)
        val hasBonus = otherTicket.has(bonusNumber)
        //then
        assertThat(actualPrize).isEqualTo(Prize.find(actualMatch, hasBonus))
    }

    companion object {
        @JvmStatic
        fun provideLottoTickets(): List<LottoTicket> {
            return listOf(
                LottoTicket(1, 2, 3, 4, 5, 6), //1등
                LottoTicket(1, 2, 3, 4, 5, 20), //2등
                LottoTicket(1, 2, 3, 4, 5, 7), //3등
                LottoTicket(1, 2, 3, 4, 7, 8), //4등
                LottoTicket(1, 2, 3, 7, 8, 9), //5등
                LottoTicket(1, 2, 7, 8, 9, 10), //Lose
            )
        }

    }
}

