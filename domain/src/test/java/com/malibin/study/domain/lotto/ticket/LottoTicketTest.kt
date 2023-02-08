package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class LottoTicketTest {
    @Test
    fun `로또 번호가 여섯개가 아닐 때 에러 메시지 확인 - 기본 생성자`() {
        // given
        val lottoNumbers = setOf<LottoNumber>(LottoNumber.of(1), LottoNumber.of(2))
        val errorMessage: String = "로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : [1, 2]"

        // when
        // val e: IllegalArgumentException = assertThrows { LottoTicket(lottoNumbers) }
        val exception = kotlin.runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(exception).hasMessageThat().contains(errorMessage)
        assertThat(exception).isInstanceOf(Exception::class.java)
    }

    @Test
    fun `로또 번호가 여섯 개일 때 잘 생성되는지 확인 - 기본 생성자`() {
        // given
        val lottoNumbers = setOf<LottoNumber>(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )

        // when
        val lottoTicket = LottoTicket(lottoNumbers)

        // then
        assertThat(lottoTicket).isInstanceOf(LottoTicket::class.java)
    }

    @Test
    fun `로또 번호가 여섯개가 아닐 때 에러 메시지 확인 - 보조 생성자1`() {
        // given
        val n1 = 1
        val n2 = 2
        val n3 = 3
        val n4 = 4
        val errorMessage: String = "로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : [1, 2, 3, 4]"

        // when
        val exception = kotlin.runCatching { LottoTicket(n1, n2, n3, n4) }.exceptionOrNull()

        // then
        assertThat(exception).hasMessageThat().contains(errorMessage)
        assertThat(exception).isInstanceOf(Exception::class.java)
    }

    @Test
    fun `로또 번호가 여섯 개일 때 잘 생성되는지 확인 - 보조 생성자1`() {
        // given
        val n1 = 1
        val n2 = 2
        val n3 = 3
        val n4 = 4
        val n5 = 5
        val n6 = 6

        // when
        val lottoTicket = LottoTicket(n1, n2, n3, n4, n5, n6)

        // then
        assertThat(lottoTicket).isInstanceOf(LottoTicket::class.java)
    }

    @Test
    fun `로또 번호가 여섯개가 아닐 때 에러 메시지 확인 - 보조 생성자2`() {
        // given
        val lottoNumbers = listOf<LottoNumber>(LottoNumber.of(1), LottoNumber.of(2))
        val errorMessage: String = "로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : [1, 2]"

        // when
        val exception = kotlin.runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(exception).hasMessageThat().contains(errorMessage)
        assertThat(exception).isInstanceOf(Exception::class.java)
    }

    @Test
    fun `로또 번호가 여섯 개일 때 잘 생성되는지 확인 - 보조 생성자2`() {
        // given
        val lottoNumbers = listOf<LottoNumber>(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )

        // when
        val lottoTicket = LottoTicket(lottoNumbers)

        // then
        assertThat(lottoTicket).isInstanceOf(LottoTicket::class.java)
    }

    /*@MethodSource("providesLottoNumbers")
    @ParameterizedTest
    fun `has 함수 작동 테스트`(lottoValue: LottoNumber, expectedResult: Boolean) {
        // given
        val lottoNumbers = listOf<Int>(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }
        val lottoTicket = LottoTicket(lottoNumbers)

        // when
        val actualResult = lottoTicket.has(lottoValue)

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }*/

    @MethodSource("providesLottoTickets")
    @ParameterizedTest
    fun `countMatchingNumbers 함수 작동 테스트`(otherLottoTickets: LottoTicket, expectedResult: Int) {
        // given
        val lottoNumbers = listOf<Int>(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }
        val lottoTicket = LottoTicket(lottoNumbers)

        // when
        val actualResult = lottoTicket.countMatchingNumbers(otherLottoTickets)

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `toString 함수 작동 테스트`() {
        // given
        val lottoNumbers = listOf<Int>(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }
        val lottoTicket = LottoTicket(lottoNumbers)
        val expectedResult = "1, 2, 3, 4, 5, 6"

        // when
        val actualResult = lottoTicket.toString()

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun providesLottoNumbers(): List<Arguments> {
            return listOf(
                Arguments.of(LottoNumber.of(1), true),
                Arguments.of(LottoNumber.of(2), true),
                Arguments.of(LottoNumber.of(3), true),
                Arguments.of(LottoNumber.of(4), true),
                Arguments.of(LottoNumber.of(5), true),
                Arguments.of(LottoNumber.of(6), true),
                Arguments.of(LottoNumber.of(7), false)
            )
        }

        @JvmStatic
        fun providesLottoTickets(): List<Arguments> {
            val lottoNumbers1 = listOf<Int>(1, 2, 3, 4, 5, 6).map { LottoNumber.of(it) }
            val lottoNumbers2 = listOf<Int>(2, 3, 4, 5, 6, 7).map { LottoNumber.of(it) }
            val lottoNumbers3 = listOf<Int>(3, 4, 5, 6, 7, 8).map { LottoNumber.of(it) }
            val lottoNumbers4 = listOf<Int>(4, 5, 6, 7, 8, 9).map { LottoNumber.of(it) }

            val lottoTicket1 = LottoTicket(lottoNumbers1)
            val lottoTicket2 = LottoTicket(lottoNumbers2)
            val lottoTicket3 = LottoTicket(lottoNumbers3)
            val lottoTicket4 = LottoTicket(lottoNumbers4)

            return listOf(
                Arguments.of(lottoTicket1, 6),
                Arguments.of(lottoTicket2, 5),
                Arguments.of(lottoTicket3, 4),
                Arguments.of(lottoTicket4, 3)
            )
        }
    }
}
