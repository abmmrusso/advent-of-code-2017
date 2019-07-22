package net.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CorruptionChecksumTest {

    @Test
    fun givenEmptyRowStringShouldReturnEmptyNumberList() {
        assertThat(getRowNumbers("")).isEmpty();
    }

    @Test
    fun givenBlankRowStringShouldReturnEmptyNumberList() {
        assertThat(getRowNumbers(" ")).isEmpty();
        assertThat(getRowNumbers("\t")).isEmpty();
    }

    @Test
    fun givenSingleNumberRowStringShouldReturnListWithGivenNumber() {
        assertThat(getRowNumbers("1")).hasSize(1).contains(1)
        assertThat(getRowNumbers("14")).hasSize(1).contains(14)
        assertThat(getRowNumbers("876")).hasSize(1).contains(876)
        assertThat(getRowNumbers("1453")).hasSize(1).contains(1453)
    }

    @Test
    fun givenMultipleNumberRowStringShoulReturnListWithGivenNumbers() {
        assertThat(getRowNumbers("1\t2\t3")).hasSize(3).contains(1, 2, 3)
        assertThat(getRowNumbers("4\t5")).hasSize(2).contains(4, 5)
        assertThat(getRowNumbers("6\t7\t8\t9\t10")).hasSize(5).contains(6, 7, 8, 9, 10)
        assertThat(getRowNumbers("\t11\t12\t13\t")).hasSize(3).contains(11, 12, 13)
    }

    @Test
    fun givenAnEmptyListOfNumbersShouldReturnZeroAsRowChecksum() {
        assertThat(calculateRowChecksum(listOf())).isEqualTo(0)
    }

    @Test
    fun givenASingleNumberListShouldReturnZeroAsRowChecksum() {
        assertThat(calculateRowChecksum(listOf(1))).isEqualTo(0)
        assertThat(calculateRowChecksum(listOf(9))).isEqualTo(0)
    }

    @Test
    fun givenAListOfMultipleNumbersShouldReturnExpectedRowChecksum() {
        assertThat(calculateRowChecksum(listOf(0, 0))).isEqualTo(0)
        assertThat(calculateRowChecksum(listOf(9, 9))).isEqualTo(0)
        assertThat(calculateRowChecksum(listOf(1, 9))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(1, 5, 9))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(1, 2))).isEqualTo(1)
        assertThat(calculateRowChecksum(listOf(1, 2, 3))).isEqualTo(2)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4))).isEqualTo(3)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4, 5))).isEqualTo(4)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4, 5, 6))).isEqualTo(5)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4, 5, 6, 7))).isEqualTo(6)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4, 5, 6, 7, 8))).isEqualTo(7)
        assertThat(calculateRowChecksum(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(9, 1, 8, 2, 7, 3, 6, 4, 5))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(7, 3, 2, 4, 6, 8, 5, 1, 9))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1))).isEqualTo(8)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6, 5, 4, 3, 2))).isEqualTo(7)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6, 5, 4, 3))).isEqualTo(6)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6, 5, 4))).isEqualTo(5)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6, 5))).isEqualTo(4)
        assertThat(calculateRowChecksum(listOf(9, 8, 7, 6))).isEqualTo(3)
        assertThat(calculateRowChecksum(listOf(9, 8, 7))).isEqualTo(2)
        assertThat(calculateRowChecksum(listOf(9, 8))).isEqualTo(1)
        assertThat(calculateRowChecksum(listOf(1640, 590, 93, 958, 73, 1263, 1405, 1363, 737, 712, 1501, 390, 68, 1554, 959, 79))).isEqualTo(1572)
    }

    @Test
    fun givenAnEmptySequenceOfRowsShouldCalculateItsChecksum() {
        assertThat(calculateSequenceChecksum(listOf<String>().asSequence())).isEqualTo(0)
    }

    @Test
    fun givenSequenceOfRowsShouldCalculateItsChecksum() {
        assertThat(calculateSequenceChecksum(listOf("").asSequence())).isEqualTo(0)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1))
                .asSequence())).isEqualTo(0)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1),
                createRowString(1))
                .asSequence())).isEqualTo(0)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1),
                createRowString(1),
                createRowString(1))
                .asSequence())).isEqualTo(0)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1, 1),
                createRowString(1, 1),
                createRowString(1, 1))
                .asSequence())).isEqualTo(0)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1, 9),
                createRowString(1, 1),
                createRowString(1, 1))
                .asSequence())).isEqualTo(8)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1, 1),
                createRowString(1, 9),
                createRowString(1, 1))
                .asSequence())).isEqualTo(8)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1, 1),
                createRowString(1, 1),
                createRowString(1, 9))
                .asSequence())).isEqualTo(8)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1, 9),
                createRowString(1, 9),
                createRowString(1, 9))
                .asSequence())).isEqualTo(24)
        assertThat(calculateSequenceChecksum(listOf(
                createRowString(1),
                createRowString(1, 2),
                createRowString(1, 2, 3),
                createRowString(1, 2, 3, 4),
                createRowString(1, 2, 3, 4, 5),
                createRowString(1, 2, 3, 4, 5, 6),
                createRowString(1, 2, 3, 4, 5, 6, 7),
                createRowString(1, 2, 3, 4, 5, 6, 7, 8),
                createRowString(1, 2, 3, 4, 5, 6, 7, 8, 9))
                .asSequence())).isEqualTo(36)
    }

    fun createRowString(vararg rowNumbers: Long) = rowNumbers.joinToString("\t")
}