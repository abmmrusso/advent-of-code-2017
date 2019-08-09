package net.aoc

import java.io.File

class CorruptionChecksum {
    private fun getRowNumbers(numberRow: String): List<Long> {
        return numberRow.split("\t").filter { numberString -> numberString.isNotBlank() }.map { numberString -> numberString.toLong() }
    }

    fun calculateSequenceChecksum(rowSequence: Sequence<String>, rowChecksumOperationType: RowChecksumOperation.CorruptionChecksumType = RowChecksumOperation.CorruptionChecksumType.DIFF): Long = rowSequence
            .map { getRowNumbers(it) }
            .map { RowChecksumOperation.create(rowChecksumOperationType)(it) }
            .fold(0L) { totalChecksum, rowChecksum -> totalChecksum + rowChecksum }
}

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()
    try {
        var rowChecksumOpType = RowChecksumOperation.CorruptionChecksumType.DIFF
        if(args.size > 1) {
            rowChecksumOpType = RowChecksumOperation.CorruptionChecksumType.valueOf(args[1])
        }
        println(CorruptionChecksum().calculateSequenceChecksum(reader.lineSequence(), rowChecksumOpType))
    } finally {
        reader.close()
    }
}