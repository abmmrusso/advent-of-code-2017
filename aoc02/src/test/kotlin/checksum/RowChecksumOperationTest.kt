package net.aoc

import net.aoc.checksum.RowDifferenceCorruptionChecksum
import net.aoc.checksum.RowEvenDivisionCorruptionChecksum
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RowChecksumOperationTest {

    @Test
    fun shouldReturnRowDifferenceCorruptionChecksumOperationByDefault() {
        Assertions.assertThat(RowChecksumOperation.create()).isInstanceOf(RowDifferenceCorruptionChecksum::class.java)
    }

    @Test
    fun shouldReturnRowDifferenceCorruptionChecksumOperation() {
        Assertions.assertThat(RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.DIFF)).isInstanceOf(RowDifferenceCorruptionChecksum::class.java)
    }

    @Test
    fun shouldReturnRowEvenDivisionCorruptionChecksumOperation() {
        Assertions.assertThat(RowChecksumOperation.create(RowChecksumOperation.CorruptionChecksumType.EVEN_DIV)).isInstanceOf(RowEvenDivisionCorruptionChecksum::class.java)
    }
}