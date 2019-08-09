package net.aoc.checksum

class RowDifferenceCorruptionChecksum: (List<Long>) -> Long {

    override fun invoke(row: List<Long>) =  (row.max() ?: 0) - (row.min() ?: 0)
}