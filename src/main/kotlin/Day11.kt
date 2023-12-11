package cberg.aoc2023

import kotlin.math.max
import kotlin.math.min

class Day11(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = solve(2)

    fun part2(f: Int) = solve(f)

    private fun solve(f: Int): Long {
        val emptyRows = input.indices.filter { row -> input[row].all { it == '.' } }
        val emptyColumns = input[0].indices.filter { col -> input.all { it[col] == '.' } }
        val galaxies = input.flatMapIndexed { row, line -> line.mapIndexed { col, c -> Pair(row, col) to c } }
            .filter { (_, c) -> c == '#' }.map { (pos, _) -> pos }
        val pairs = galaxies.flatMapIndexed { i, p1 -> galaxies.drop(i + 1).map { p2 -> p1 to p2 } }
        return pairs.sumOf { (p1, p2) ->
            val rowRange = min(p1.first, p2.first)..max(p1.first, p2.first)
            val colRange = min(p1.second, p2.second)..max(p1.second, p2.second)
            0L + rowRange.last - rowRange.first + colRange.last - colRange.first + (emptyRows.count { it in rowRange } + emptyColumns.count { it in colRange }) * (f - 1)
        }
    }
}