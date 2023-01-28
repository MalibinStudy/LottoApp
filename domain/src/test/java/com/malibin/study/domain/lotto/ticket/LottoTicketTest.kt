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

}