package cberg.aoc2023

class Day13(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.split(String::isEmpty).map(::Pattern).sumOf { pattern -> pattern.reflectionScores().single() }

    fun part2() = input.split(String::isEmpty).map(::Pattern).sumOf { pattern -> pattern.otherReflectionScore() }

    private fun Pattern.reflectionScores() =
        reflections().map { it * 100 } + transposed().reflections()

    private fun Pattern.reflections() = rows.drop(1).filter { row0 ->
        var row1 = row0 - 1
        var row2 = row0
        var success = true
        while (success && row1 >= 0 && row2 <= rows.last) {
            success = cols.all { col -> this[row1, col] == this[row2, col] }
            row1--
            row2++
        }
        success
    }

    private fun Pattern.otherReflectionScore(): Int {
        val originalScore = reflectionScores().single()
        return rows.asSequence().flatMap { row -> cols.asSequence().map { col -> row to col } }
            .flatMap { (row, col) -> switch(row, col).reflectionScores() }
            .first { score -> score != originalScore }
    }

    private interface Pattern {
        val rows: IntRange
        val cols: IntRange
        operator fun get(row: Int, col: Int): Char
    }

    private fun Pattern(strings: List<String>) = object : Pattern {
        override val rows: IntRange get() = strings.indices
        override val cols: IntRange get() = strings.first().indices
        override fun get(row: Int, col: Int) = strings[row][col]
    }

    private fun Pattern.transposed() = object : Pattern {
        override val rows get() = this@transposed.cols
        override val cols get() = this@transposed.rows
        override fun get(row: Int, col: Int) = this@transposed[col, row]
    }

    private fun Pattern.switch(switchRow: Int, switchCol: Int) = object : Pattern {
        override val rows get() = this@switch.rows
        override val cols get() = this@switch.cols
        override fun get(row: Int, col: Int): Char {
            val c = this@switch[row, col]
            return if (row == switchRow && col == switchCol) {
                if (c == '.') '#' else '.'
            } else {
                c
            }
        }
    }
}
