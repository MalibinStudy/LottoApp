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
    fun `LottoTicket에_빈_배열이_들어오는_경우?`(lottoNumbers: Set<LottoNumber>) {
        val exception = kotlin.runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()
        assertThat(exception).hasMessageThat()
            .contains("로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : $lottoNumbers")
    }

    /** 왜 Exception 으로 걸리지 않을까..? */
    @NullSource
    @ParameterizedTest
    fun `LottoTicket에_Null이_들어오는_경우?`(lottoNumbers: Set<LottoNumber>) {
        val exception = kotlin.runCatching { LottoTicket(lottoNumbers) }.exceptionOrNull()
        assertThat(exception).isEqualTo(Exception::class.java)
    }

    @Test
    fun `LottoTicket이_길이를_초과해도_잘_동작하는지?`() {
        val list = intArrayOf(1,2,3,4,45,8,7)
        val actualException = runCatching { LottoTicket(*list) }.exceptionOrNull()
        assertThat(actualException).hasMessageThat().contains("로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : ${list.toList()}")
    }

    @CsvSource(value = ["1:true", "9:false", "10:false", "3:true"], delimiter = ':')
    @ParameterizedTest
    fun `LottoTicket에_원하는_번호가_있는지?`(target : Int, result : Boolean) {
        val lottoNumbers = intArrayOf(1,2,3,4,5,6)
        val actualValue = LottoTicket(*lottoNumbers).has(LottoNumber.of(target))
        assertThat(actualValue).isEqualTo(result)
    }

    @Test
    fun `두_LottoTicket_간에 _공통된_숫자가_몇개인지?`() {
        val correctLottoNumber = intArrayOf(1,3,5,7,9,11)
        val testLottoNumber = intArrayOf(1,2,3,4,5,6)
        val expectAnswer = 3
        val actualValue = LottoTicket(*testLottoNumber).countMatchingNumbers(LottoTicket(*correctLottoNumber))
        assertThat(actualValue).isEqualTo(expectAnswer)
    }
}

