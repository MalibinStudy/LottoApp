package com.malibin.study.domain.lotto.cal

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.ticket.LottoTicket
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class AverageCalculatorTest {

    @ValueSource(strings = ["Heart", "Diamond", "Club", "Spade"])
    @ParameterizedTest
    fun `value에 맞는 올바른 suit를 찾을 수 있다`(suitValue: String) {
        // when
        val actualSuit = Suit.find(suitValue)

        // then
        assertThat(actualSuit.value).isEqualTo(suitValue)
    }

    @CsvSource(value = ["Heart,Heart", "Diamond,Diamond", "Club,Club", "Spade,Spade"])
    @ParameterizedTest
    fun `value에 맞는 올바른 suit를 찾을 수 있다2`(suitValue: String, expectedSuit: Suit) {
        val actualSuit = Suit.find(suitValue)
        assertThat(actualSuit).isEqualTo(expectedSuit)
    }

    @MethodSource("providesLottoTickets")
    @ParameterizedTest
    fun `호호`(arg1: LottoTicket, arg2: Int, arg3: String) {
        // given
        val ticket = LottoTicket(1, 2, 3, 4, 5, 6)
    }

    companion object {
        fun providesLottoTickets(): List<Arguments> =
            listOf(
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, "하하하하하"),
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, "하하하하하"),
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), 1, "하하하하하"),
            )
    }

    enum class Suit(
        val id: Int,
        val value: String,
    ) {
        Heart(0, "Heart"),
        Diamond(1, "Diamond"),
        Club(2, "Club"),
        Spade(3, "Spade");

        companion object {
            fun find(value: String): Suit = values().first { it.value == value }
        }
    }
}