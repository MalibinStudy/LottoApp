package com.malibin.study.domain.lotto.cal

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class AverageCalculatorTest {
    @Test
    fun `평균을 구할 수 있다`() {
        // given
        val integers = listOf<Int>(1, 2, 3, 4, 5)
        val averageCalculator = AverageCalculator()

        // when
        val actualAverage = averageCalculator.calculateAverage(integers)

        // then
        assertThat(actualAverage).isEqualTo(3)
    }

    @ValueSource(strings = ["Heart", "Diamond", "Clover", "Spade"])
    @ParameterizedTest
    fun `value에 맞는 올바른 Suit를 찾을 수 있다`(suitValue: String) {
        // given

        // when
        val actualSuit = Suit.find(suitValue)

        // then
        assertThat(actualSuit.value).isEqualTo(suitValue)
    }

    @CsvSource(value = ["Heart,HEART", "Diamond,DIAMOND", "Clover,CLOVER", "Spade,SPADE"])
    @ParameterizedTest
    fun `Enum클래스로 직접 비교하여 value에 맞는 올바른 Suit를 찾을 수 있다`(suitValue: String, expectedSuit: Suit) {
        // given

        // when
        val actualSuit = Suit.find(suitValue)

        // then
        assertThat(actualSuit).isEqualTo(expectedSuit)
    }

    @MethodSource("providesLottoTickets")
    @ParameterizedTest
    fun `좀 더 어려운 인자를 넣어주는 테스트`() {
        // given
        val ticket = LottoTicket(1, 2, 3, 4, 5, 6)

        // when

        // then
    }

    companion object {
        // 여러 자료형들을 아래처럼 인자로 넘길 수 있다!!
        // 위의 MethodSource를 이용해서 넣어준다~!
        // 하지만 가독성이 최악이니 최후의 수단이라고 생각하자
        fun providesLottoTickets(): List<Arguments> = listOf(
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, ""),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, ""),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, "")
        )
    }

    enum class Suit(val id: Int, val value: String) {
        HEART(0, "Heart"),
        DIAMOND(1, "Diamond"),
        CLOVER(2, "Clover"),
        SPADE(3, "Spade");

        companion object {
            fun find(value: String): Suit = values().first() { it.value == value }
        }
    }
}
