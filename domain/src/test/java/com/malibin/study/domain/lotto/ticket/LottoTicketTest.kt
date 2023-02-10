package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
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
        val lottoNums = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )
        // when
        val actualLottoTicket = LottoTicket(lottoNums)
        // then
        assertThat(actualLottoTicket.lottoNumbers.size).isEqualTo(lottoNums.size)
    }

    @Test
    fun `LottoTicket은 크기가 6개의 LottoNumber List로 만들 수 있다`() {
        // given
        val lottoNumbers = listOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )
        // when
        val actualLottoTicket = LottoTicket(lottoNumbers)
        // then
        // 이렇게 당위성을 체크하는게 맞을까요..?
        assertThat(actualLottoTicket.lottoNumbers.size).isEqualTo(lottoNumbers.size)
    }

    @Test
    fun `LottoNumber가 6개가 아니면 LottoTicket을 만들 수 없다`() {
        // given
        val invalidLottoNumbers = listOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6),
            LottoNumber.of(7)
        )
        // when
        val exception = runCatching { LottoTicket(invalidLottoNumbers) }.exceptionOrNull()
        // then
        assertAll(
            { assertThat(exception).isInstanceOf(IllegalArgumentException::class.java) },
            { assertThat(exception).hasMessageThat().isEqualTo("로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : ${invalidLottoNumbers.toSet()}") }
        )
    }

    @Test
    fun `중복된 LottoNumber가 포함된 크기가 6개의 LottoNumber 리스트로는 LottoTicket을 만들 수 없다`() {
        // given
        val lottoNumbers = listOf(
            LottoNumber.of(1),
            LottoNumber.of(3),
            LottoNumber.of(3),
            LottoNumber.of(2),
            LottoNumber.of(21),
            LottoNumber.of(5)
        )
        // when
        val exception = runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()
        // then
        assertAll(
            { assertThat(exception).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(exception).hasMessageThat().isEqualTo(
                    "로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : ${lottoNumbers.toSet()}"
                )
            }
        )
    }

    @Test
    fun `countMatchingNumbers() 는 2개의 LottoTicket의 공통된 숫자 개수를 반환한다`() {
        // given
        val lottoNumbers1 = setOf(
            LottoNumber.of(1),
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6),
        )
        val lottoNumbers2 = setOf(
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6),
            LottoNumber.of(7),
            LottoNumber.of(8),
        )
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
        val lottoNumbers = setOf(
            LottoNumber.of(1),
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6),
        )
        val lottoTicket = LottoTicket(lottoNumbers)
        // when
        val actualIsInclude = lottoTicket.has(LottoNumber.of(num))
        // then
        assertThat(actualIsInclude).isEqualTo(expected)
    }
}
