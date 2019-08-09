package net.aoc

import net.aoc.checksum.RowDifferenceCorruptionChecksum
import net.aoc.checksum.RowEvenDivisionCorruptionChecksum

abstract class RowChecksumOperation {

    companion object Factory {
        fun create(checksumType: CorruptionChecksumType=CorruptionChecksumType.DIFF) = when(checksumType) {
            CorruptionChecksumType.DIFF -> RowDifferenceCorruptionChecksum()
            CorruptionChecksumType.EVEN_DIV -> RowEvenDivisionCorruptionChecksum()
        }
    }

    enum class CorruptionChecksumType {
        DIFF,
        EVEN_DIV
    }
}