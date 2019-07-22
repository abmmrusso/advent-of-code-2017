package net.aoc

import java.io.File

fun getRowNumbers(numberRow: String): List<Long> {
    return numberRow.split("\t").filter { numberString -> numberString.isNotBlank() }.map { numberString -> numberString.toLong() }
}

fun calculateRowChecksum(row: List<Long>): Long = (row.max() ?: 0) - (row.min() ?: 0)

fun calculateSequenceChecksum(rowSequence: Sequence<String>): Long = rowSequence
        .map { getRowNumbers(it) }
        .map { calculateRowChecksum(it) }
        .fold(0L) { totalChecksum, rowChecksum -> totalChecksum + rowChecksum }

fun main(args: Array<String>) {
    val reader = File(args[0]).bufferedReader()
    try {
        println(calculateSequenceChecksum(reader.lineSequence()))
    } finally {
        reader.close()
    }
}