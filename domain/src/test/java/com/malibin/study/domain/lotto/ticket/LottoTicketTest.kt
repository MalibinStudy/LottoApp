package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LottoTicketTest {

    @Test
    fun `LottoTicket은 6개의 숫자로 만들 수 있다`() {
        // given
        val nums = intArrayOf(11, 23, 34, 42, 12, 32)
        // when
        val actualLottoTicket = LottoTicket(*nums)
        // then
        // 이렇게 당위성을 체크하는게 맞을까요..?
        assertThat(actualLottoTicket.lottoNumbers.size).isEqualTo(nums.size)
    }

    @Test
    fun `LottoTicket은 크기가 6개의 LottoNumber Set으로 만들 수 있다`() {
        // given
        val lottoNums = (1..6).map { LottoNumber.of(it) }.toSet()
        // when
        val actualLottoTicket = LottoTicket(lottoNums)
        // then
        // 이렇게 당위성을 체크하는게 맞을까요..?
        assertThat(actualLottoTicket.lottoNumbers.size).isEqualTo(lottoNums.size)
    }

    @Test
    fun `LottoTicket은 크기가 6개의 LottoNumber List로 만들 수 있다`() {
        // given
        val lottoNums = (1..6).map { LottoNumber.of(it) }
        // when
        val actualLottoTicket = LottoTicket(lottoNums)
        // then
        // 이렇게 당위성을 체크하는게 맞을까요..?
        assertThat(actualLottoTicket.lottoNumbers.size).isEqualTo(lottoNums.size)
    }

    @Test
    fun `LottoNumber가 6개가 아니면 LottoTicket을 만들 수 없다`() {
        // given
        val lottoNumbers = (1..7).map { LottoNumber.of(it) }
        // when
        val exception = runCatching { LottoTicket(lottoNumbers) }.getOrNull()
        // then
        assertThat(exception).isNull()
    }

    @Test
    fun `중복된 LottoNumber가 포함된 크기가 6개의 LottoNumber 리스트로는 LottoTicket을 만들 수 없다`() {
        // given
        val lottoNumbers = intArrayOf(1, 3, 3, 2, 21, 5).map { LottoNumber.of(it) }
        // when
        val exception = runCatching { LottoTicket(lottoNumbers) }.getOrNull()
        // then
        assertThat(exception).isNull()
    }

    @Test
    fun `countMatchingNumbers() 는 LottoTicket의 LottoNumber와 다른 LottoTicket의 LottoNumber를 비교하여 일치하는 숫자의 개수를 반환한다`() {
        // given
        val lottoNumbers1 = (1..6).map { LottoNumber.of(it) }
        val lottoNumbers2 = intArrayOf(3, 4, 5, 6, 7, 8).map { LottoNumber.of(it) }
        val lottoTicket1 = LottoTicket(lottoNumbers1)
        val lottoTicket2 = LottoTicket(lottoNumbers2)
        // when
        val actualCount = lottoTicket1.countMatchingNumbers(lottoTicket2)
        // then
        assertThat(actualCount).isEqualTo(4)
    }
    @ParameterizedTest
    @CsvSource(value = ["3:true", "33:false"], delimiter = ':')
    fun `has() 는 LottoTicket에 특정 LottoNumber가 포함되어 있는지 확인한다`(num: Int, expected: Boolean) {
        // given
        val lottoNumbers = (1..6).map { LottoNumber.of(it) }
        val lottoTicket = LottoTicket(lottoNumbers)
        // when
        val actualIsInclude = lottoTicket.has(LottoNumber.of(num))
        // then
        assertThat(actualIsInclude).isEqualTo(expected)
    }
}
