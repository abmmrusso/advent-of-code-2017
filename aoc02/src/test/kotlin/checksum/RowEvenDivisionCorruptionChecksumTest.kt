package net.aoc.checksum

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RowEvenDivisionCorruptionChecksumTest {

    val checksumFunction = RowEvenDivisionCorruptionChecksum()

    @Test
    fun givenAnEmptyListOfNumbersShouldReturnZeroAsRowChecksum() {
        Assertions.assertThat(checksumFunction(listOf())).isEqualTo(0)
    }

    @Test
    fun givenASingleNumberListShouldReturnZeroAsRowChecksum() {
        Assertions.assertThat(checksumFunction(listOf(1))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(9))).isEqualTo(0)
    }

    @Test
    fun givenAListOfMultipleNumbersWhereNoneAreEvenlyDivisibleByTheOthersShouldReturnZeroAsRowChecksum() {
        Assertions.assertThat(checksumFunction(listOf(2, 9))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(9, 2))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(3, 5, 7, 11))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(11, 7, 5, 3))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(115471, 1021, 76579, 88799, 53))).isEqualTo(0)
    }

    @Test
    fun givenAListOfMultipleNumbersShouldReturnRowChecksumAsFirstEvenDivisableValueSetOutcome() {
        Assertions.assertThat(checksumFunction(listOf(9, 9))).isEqualTo(1)
        Assertions.assertThat(checksumFunction(listOf(1, 1))).isEqualTo(1)
        Assertions.assertThat(checksumFunction(listOf(2, 8))).isEqualTo(4)
        Assertions.assertThat(checksumFunction(listOf(8, 2))).isEqualTo(4)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))).isEqualTo(9)
        Assertions.assertThat(checksumFunction(listOf(2, 3, 4, 5, 6, 7, 8, 9))).isEqualTo(4)
        Assertions.assertThat(checksumFunction(listOf(3, 4, 5, 6, 7, 8, 9))).isEqualTo(3)
        Assertions.assertThat(checksumFunction(listOf(4, 5, 6, 7, 8, 9))).isEqualTo(2)
        Assertions.assertThat(checksumFunction(listOf(2, 3, 4, 8, 9, 10, 17, 18, 19))).isEqualTo(9)
        Assertions.assertThat(checksumFunction(listOf(206, 1951, 2502, 2697, 2997, 74, 76, 78, 1534, 81, 2775, 2059, 3026, 77, 2600, 3067))).isEqualTo(37)
    }
}