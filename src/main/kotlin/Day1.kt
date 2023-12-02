package cberg.aoc2023

class Day1(private val input: List<String>) {
    constructor(input: Input) : this(input.lines())

    fun part1() = input.sumOf { line ->
        val first = line.first { it.isDigit() }.digitToInt()
        val last = line.last { it.isDigit() }.digitToInt()
        first * 10 + last
    }

    fun part2() = input.sumOf { line ->
        val first = line.indices.firstNotNullOf { i -> line.toIntOrNullAt(i) }
        val last = line.indices.reversed().firstNotNullOf { i -> line.toIntOrNullAt(i) }
        first * 10 + last
    }

    private fun String.toIntOrNullAt(i: Int) =
        this[i].digitToIntOrNull()
            ?: map.firstNotNullOfOrNull { (s, n) -> if (substring(i).startsWith(s)) n else null }

    private val map = mapOf(
        "one" to 1, "two" to 2, "three" to 3,
        "four" to 4, "five" to 5, "six" to 6,
        "seven" to 7, "eight" to 8, "nine" to 9
    )
}