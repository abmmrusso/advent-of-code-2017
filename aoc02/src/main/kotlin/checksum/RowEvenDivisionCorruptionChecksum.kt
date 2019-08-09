package net.aoc.checksum

class RowEvenDivisionCorruptionChecksum: (List<Long>) -> Long {

    override fun invoke(row: List<Long>): Long = getFirstEvenDivision(row.sorted())

    private fun getFirstEvenDivision(sortedRow: List<Long>): Long = when(sortedRow.size){
        0, 1 -> 0L
        else -> {
            val divisibleList = sortedRow.slice(1.until(sortedRow.size))
            divisibleList.reversed()
                    .asSequence()
                    .find { it % sortedRow[0] == 0L } ?.div(sortedRow[0])
                    ?:getFirstEvenDivision(divisibleList)
        }
    }
}