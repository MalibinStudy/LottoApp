package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Test

class WinningTicketTest {

    @Test
    fun `보너스 번호와 당첨 번호는 중복 될 수 없다`() {
        val a = intArrayOf(1,2,3,4,5,6)
        val b = 5
        val assertException = kotlin.runCatching { WinningTicket(LottoTicket(*a), LottoNumber.of(b)) }.exceptionOrNull()
        assertThat(assertException).hasMessageThat().contains("보너스 번호 ($b)는 당첨번호(${a.joinToString(", ")})와 중복될 수 없습니다.")
    }

    @Test
    fun `한 개의 로또 티켓의 당첨 등수를 잘 반환한다`() {
        val winningTicket = intArrayOf(1,2,3,4,5,6)
        val bonus = 7
        val otherTicket = intArrayOf(1,2,3,4,5,6)
        val assertValue = WinningTicket(LottoTicket(*winningTicket), LottoNumber.of(bonus)).compareWith(
            LottoTicket(*otherTicket))
        println(assertValue)
        assertThat(assertValue).isEqualTo(Prize.First)
    }

    @Test
    fun `여러 개의 로또 티켓들의 당첨 등수와 당첨된 티켓의 갯수를 잘 반환한다`() {
        val winningTicket = intArrayOf(1,2,3,4,5,6)
        val ticket1 = intArrayOf(1,3,5,7,9,11)
        val ticket2 = intArrayOf(1,2,3,4,5,6)
        val bonus = 7
        val assertValue = WinningTicket(LottoTicket(*winningTicket), LottoNumber.of(bonus)).compareWith(
            listOf(LottoTicket(*ticket1), LottoTicket(*ticket2))
        )

        assertThat(assertValue.toString()).isEqualTo("{Fifth=1, First=1}")

    }
}