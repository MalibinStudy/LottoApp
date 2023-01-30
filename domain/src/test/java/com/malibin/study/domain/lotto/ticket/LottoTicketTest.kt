package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class LottoTicketTest {
    @EmptySource
    @ParameterizedTest
    fun `LottoTicket에 빈 배열이 들어오는 경우(지정된 길이보다 모자랄 경우) 에러를 반환한다`(lottoNumbers: Set<LottoNumber>) {
        val exception = kotlin.runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()
        assertThat(exception).hasMessageThat()
            .isEqualTo("로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : $lottoNumbers")
    }

    @Test
    fun `LottoTicket이 허용된 길이를 초과할 경우 에러를 반환한다`() {
        val list = intArrayOf(1,2,3,4,45,8,7)
        val actualException = runCatching { LottoTicket(*list) }.exceptionOrNull()
        assertThat(actualException).hasMessageThat().isEqualTo("로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : ${list.toList()}")
    }

    @CsvSource(value = ["1:true", "9:false"], delimiter = ':')
    @ParameterizedTest
    fun `LottoTicket에 원하는 번호가 있을 경우 true, 없을 경우 false 를 반환한다`(target : Int, result : Boolean) {
        val lottoNumbers = intArrayOf(1,2,3,4,5,6)
        val actualValue = LottoTicket(*lottoNumbers).has(LottoNumber.of(target))
        assertThat(actualValue).isEqualTo(result)
    }

    @Test
    fun `두 LottoTicket 간에 공통된 숫자의 갯수를 반환한다`() {
        val correctLottoNumber = intArrayOf(1,3,5,7,9,11)
        val testLottoNumber = intArrayOf(1,2,3,4,5,6)
        val expectAnswer = 3
        val actualValue = LottoTicket(*testLottoNumber).countMatchingNumbers(LottoTicket(*correctLottoNumber))
        assertThat(actualValue).isEqualTo(expectAnswer)
    }
}

