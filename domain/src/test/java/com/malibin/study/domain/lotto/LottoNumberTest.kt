package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoNumberTest {

    @ValueSource(ints = [1, 10, 23, 34, 45])
    @ParameterizedTest
    fun `로또 번호는 1과 45 사이의 수가 주어 진다`(validLottoNumber: Int) {
        // then
        assertThat(validLottoNumber).isIn(IntRange(1, 45))
    }

}