package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LottoTicketTest {

    @Test
    fun `로또 번호는 6개가 주어 진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5, 6)

        // when
        val lottoTicket = LottoTicket(*lottoNumbers)

        // then
        assertThat(lottoTicket.lottoNumbers.count()).isEqualTo(6)
    }

    @Test
    fun `로또 번호의 개수가 6보다 크면 예외처리를 던진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5, 6, 7)

        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호의 개수가 6보다 작으면 예외처리를 던진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5)

        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }
}