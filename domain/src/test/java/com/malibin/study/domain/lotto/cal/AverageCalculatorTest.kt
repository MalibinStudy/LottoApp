package com.malibin.study.domain.lotto.cal

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class AverageCalculatorTest {

    @Test
    fun calculate() {

    }

    @ValueSource(strings = ["Heart", "Diamond", "Club", "Spade"])
    @ParameterizedTest
    fun `value에 맞는 올바른 suit를 찾을 수 있다` (suitValue : String) {

        val actualSuit = Suit.find(suitValue)
        assertThat(actualSuit.value).isEqualTo(suitValue)
    }

    @CsvSource(value = ["Heart,Heart", "Diamond,Diamond", "Club,Club", "Spade,Spade"])
    @ParameterizedTest
    fun `value에 맞는 올바른 suit를 찾을 수 있다 2`(suitValue : String, suit : Suit) {
        val actualSuit = Suit.find(suitValue)
        assertThat(actualSuit.value).isEqualTo(suitValue)
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