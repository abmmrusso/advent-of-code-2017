package net.aoc.checksum

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RowDifferenceCorruptionChecksumTest {

    val checksumFunction = RowDifferenceCorruptionChecksum()

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
    fun givenAListOfMultipleNumbersShouldReturnRowChecksumAsDifferenceBetweenMaxAndMinValues() {
        Assertions.assertThat(checksumFunction(listOf(0, 0))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(9, 9))).isEqualTo(0)
        Assertions.assertThat(checksumFunction(listOf(1, 9))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(1, 5, 9))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(1, 2))).isEqualTo(1)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3))).isEqualTo(2)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4))).isEqualTo(3)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5))).isEqualTo(4)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5, 6))).isEqualTo(5)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5, 6, 7))).isEqualTo(6)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5, 6, 7, 8))).isEqualTo(7)
        Assertions.assertThat(checksumFunction(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(9, 1, 8, 2, 7, 3, 6, 4, 5))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(7, 3, 2, 4, 6, 8, 5, 1, 9))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1))).isEqualTo(8)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6, 5, 4, 3, 2))).isEqualTo(7)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6, 5, 4, 3))).isEqualTo(6)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6, 5, 4))).isEqualTo(5)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6, 5))).isEqualTo(4)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7, 6))).isEqualTo(3)
        Assertions.assertThat(checksumFunction(listOf(9, 8, 7))).isEqualTo(2)
        Assertions.assertThat(checksumFunction(listOf(9, 8))).isEqualTo(1)
        Assertions.assertThat(checksumFunction(listOf(1640, 590, 93, 958, 73, 1263, 1405, 1363, 737, 712, 1501, 390, 68, 1554, 959, 79))).isEqualTo(1572)
    }
}