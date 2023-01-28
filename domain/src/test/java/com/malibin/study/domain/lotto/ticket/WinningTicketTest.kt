package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Test

class WinningTicketTest {

    @Test
    fun `보너스_번호와_당첨_번호_중복_테스트`() {
        val a = intArrayOf(1,2,3,4,5,6)
        val b = 5
        val assertException = kotlin.runCatching { WinningTicket(LottoTicket(*a), LottoNumber.of(b)) }.exceptionOrNull()
        assertThat(assertException).hasMessageThat().contains("보너스 번호 ($b)는 당첨번호(${a.joinToString(", ")})와 중복될 수 없습니다.")
    }

    @Test
    fun `로또번호들_중_당첨등수와_갯수를_잘_반환하는지?`() {
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